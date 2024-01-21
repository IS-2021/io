import Obstacle from "./Obstacle.mjs";
import Tram from "./Tram.mjs";
import TramStop from "./TramStop.mjs";

class Game {
  #canvas;
  #context;
  #obstaclesList = [];
  #tramStopList = [];
  #currentTramStop;
  #score = 0;
  #level = 1;
  #speed = 5;
  #tram;
  #isPaused = false;
  #backgroundMusic = new Audio();
  gameIsRunning = false;

  constructor(canvas, context) {
    this.#canvas = canvas;
    this.#context = context;
  }

  drawInfos() {
    const infoY = this.#canvas.height - 120;
    const squareWidth = 200;
    const squareHeight = 100;
    const padding = 30;
    const startX = 640 - 1.5 * (squareWidth + padding);

    this.drawSquare(startX, infoY, squareWidth, squareHeight, "#00BFFF");
    this.drawTextInSquare(
      "Score:",
      this.#score.toString(),
      startX,
      infoY,
      squareWidth,
      squareHeight
    );

    this.drawSquare(
      startX + (squareWidth + padding),
      infoY,
      squareWidth,
      squareHeight,
      "#2E8B57"
    );
    this.drawTextInSquare(
      "Tram Capacity:",
        (this.#tram.getPassengersToPickup().toString()),
      startX + (squareWidth + padding),
      infoY,
      squareWidth,
      squareHeight
    );

    this.drawSquare(
      startX + 2 * (squareWidth + padding),
      infoY,
      squareWidth,
      squareHeight,
      "#FFA500"
    );
    this.drawTextInSquare(
      "Picked Passengers:",
      this.#tram.getPickedPassengers().toString(),
      startX + 2 * (squareWidth + padding),
      infoY,
      squareWidth,
      squareHeight
    );
  }
  drawSquare(x, y, width, height, color) {
    this.#context.fillStyle = color;
    this.#context.fillRect(x, y, width, height);
  }

  drawTextInSquare(label, text, x, y, width, height) {
    this.#context.fillStyle = "white";
    this.#context.font = "bold 25px Calibri";
    this.#context.textAlign = "center";
    this.#context.textBaseline = "middle";
    this.#context.fillText(label, x + width / 2, y + height / 4);
    this.#context.fillText(text, x + width / 2, y + height / 1.5);
    this.#context.textAlign = "start";
    this.#context.textBaseline = "alphabetic";
  }

  dropOffPassengers() {
    try {
      this.#currentTramStop.pickPassengers();
      if (
        this.#tram.getPassengersToPickup() === this.#tram.getPickedPassengers()
      ) {
        this.#score += this.#tram.getPickedPassengers() * 1.5;
        this.#tram.dropOffPassengers();
      } else {
        this.#score += this.#tram.getPickedPassengers();
        this.#tram.dropOffPassengers();
      }
    } catch {}
  }

  pickPassengers() {
    try {
      const passengers = this.#currentTramStop.pickPassengers();
      this.#tram.pickupPassengers(passengers);
    } catch {}
  }
  getCanvas() {
    return this.#canvas;
  }
  getTram() {
    return this.#tram;
  }
  drawBackground() {
    this.#context.clearRect(0, 0, this.#canvas.width, this.#canvas.height);
    this.#context.fillStyle = "rgb(96, 136, 75)";
    this.#context.fillRect(0, 0, this.#canvas.width, 550);

    this.#context.fillStyle = "white";
    this.#context.fillRect(0, 550, this.#canvas.width, 10);

    this.#context.fillStyle = "#222"; //
    this.#context.fillRect(
      0,
      560,
      this.#canvas.width,
      this.#canvas.height - 560
    );

    for (let i = 0; i <= this.#level + 2; i++) {
      this.#context.fillStyle = "rgba(50, 50, 50, 0.8)";
      this.#context.fillRect(0, i * 100 + 60, this.#canvas.width, 50);
      this.#context.fillStyle = "rgb(50, 30, 30)";
      this.#context.fillRect(0, i * 100 + 86, this.#canvas.width, 3);
      this.#context.fillRect(0, i * 100 + 94, this.#canvas.width, 3);

    }
  }

  drawAll() {
    this.drawBackground();
    this.drawInfos();
    this.#tramStopList.forEach((tramStop) => {
      tramStop.draw();
    });

    this.#obstaclesList.forEach((obst) => {
      obst.draw();
    });
    this.#tram.draw();
  }

  generateTramStop() {
    const track = Math.floor(Math.random() * (this.#level + 2));
    const passengersWaiting = Math.floor(Math.random() * 50);
    const minDistance = 800;
    const lastTramStop = this.#tramStopList[this.#tramStopList.length - 1];
    const distance = lastTramStop
      ? 800 - lastTramStop.getX() - lastTramStop.getWidth()
      : Infinity;

    if (distance >= minDistance) {
      const newTramStop = new TramStop(
        passengersWaiting,
        this.#context,
        track,
        this.#speed,
        200
      );
      this.#tramStopList.push(newTramStop);
    }
  }
  generateObstacle() {
    const track = Math.floor(Math.random() * (this.#level + 3));
    const minDistance = 0;

    const lastObstacle = this.#obstaclesList[this.#obstaclesList.length - 1];
    const distance = lastObstacle
      ? 800 - lastObstacle.getX() - lastObstacle.getWidth()
      : Infinity;

    if (distance >= minDistance) {
      const newObstacle = new Obstacle(track, this.#context, this.#speed, 50);
      this.#obstaclesList.push(newObstacle);
    }
  }
  update() {
    if (Math.random() < 0.03) {
      this.generateObstacle();
    } else if (Math.random() > 0.99) {
      this.generateTramStop();
    }
    this.#currentTramStop = null;
    for (let i = this.#tramStopList.length - 1; i >= 0; i--) {
      this.#tramStopList[i].update();

      if (this.#tramStopList[i].checkCloseness(this.#tram)) {
        this.#currentTramStop = this.#tramStopList[i];
      }

      if (this.#tramStopList[i].getX() + this.#tramStopList[i].getWidth() < 0) {
        this.#tramStopList.splice(i, 1);
      }
    }

    for (let i = this.#obstaclesList.length - 1; i >= 0; i--) {
      this.#obstaclesList[i].update();

      if (this.checkCollision(this.#tram, this.#obstaclesList[i])) {
        this.endGame();
      }

      if (
        this.#obstaclesList[i].getX() + this.#obstaclesList[i].getWidth() <
        0
      ) {
        this.#obstaclesList.splice(i, 1);
      }
    }
    this.drawAll();
  }

  checkCollision(tram, obstacle) {
    return (
      tram.getX() < obstacle.getX() + obstacle.getWidth() &&
      tram.getX() + tram.getWidth() > obstacle.getX() &&
      tram.getTrack() === obstacle.getTrack()
    );
  }
  endGame() {
    this.#backgroundMusic.pause();

    this.#tramStopList = [];
    this.#tram = null;
    this.#obstaclesList = [];
    this.gameIsRunning = false;
    if (this.onGameOverCallback) {
      this.onGameOverCallback();
    }
  }
  onGameOver(callback) {
    this.onGameOverCallback = callback;
  }

  getScore() {
    return this.#score;
  }
  start(level) {
    this.#score = 0;
    this.#level = level;
    if (level == 1) {
      this.#speed = 13;
    }
    if (level == 2) {
      this.#speed = 20;
    }
    this.#tram = new Tram(this.#context, Math.floor(level / 2), level);

    this.#backgroundMusic.src = "./audio/miodowe.mp3";
    this.#backgroundMusic.loop = true;
    this.#backgroundMusic.play();
    this.gameIsRunning = true;
    this.gameLoop();
  }
  gameLoop() {
    if (!this.#isPaused) {
      this.update();
      this.animationFrameId = requestAnimationFrame(() => this.gameLoop());
    }
  }

  changePause() {
    this.#isPaused = !this.#isPaused;
  }
}

export default Game;
