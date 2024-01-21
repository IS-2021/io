import Game from "./Game.mjs";
import GameApiClient from "./GameApiClient.mjs";
import GameOverMenu from "./GameOverMenu.mjs";
import {updateRanking, loadGameData, saveGameData} from "https://unpkg.com/greengame-api-client@latest";
import game from "./Game.mjs";
let gameData =  await loadGameData({gameId: 5});
class Menu {
  #canvas = document.getElementById("board");
  #context = this.#canvas.getContext("2d");
  #levels = ["Level 1", "Level 2", "Level 3"];
  #selectedLevel = null;
  #game;
  #gameOverMenu;
  #img = new Image();
  #menuIsOnTop = false;
  #gameApi = new GameApiClient();


  constructor() {
    this.drawMenu();
    this.#img.src = "./img/uwaga.jpg";
    this.#gameOverMenu = new GameOverMenu(this.#canvas, this.#context);
    this.#game = new Game(this.#canvas, this.#context);
  this.#game.getCanvas().focus();
    this.#game.getCanvas().addEventListener("keydown", (event) => {
      if (this.#game.gameIsRunning) {
        switch (event.key) {
          case "w":
            this.#game.getTram().moveUp();
            break;
          case "s":
            this.#game.getTram().moveDown();
            break;
          case "a":
            this.#game.pickPassengers();
            break;
          case "d":
            this.#game.dropOffPassengers();
            break;
        }
      }
    });
    this.#game.onGameOver(() => {

      if(this.#game.getScore() > gameData.userScore){
        this.#gameApi.saveScore(this.#game.getScore());
        gameData.userScore = this.#game.getScore();
      }
      this.#gameOverMenu.draw(this.#game.getScore(), gameData.userScore);
    });
    this.#gameOverMenu.onMenuClickedCallback(() => {
      this.drawMenu();
    });
    this.#gameOverMenu.onRetryClickedCallback(() => {
      this.startGame();
    });
  }



  drawMenu() {
    this.#menuIsOnTop = true;
    this.#context.clearRect(0, 0, this.#canvas.width, this.#canvas.height);



    this.#context.fillStyle = "rgb(96, 136, 75)";
    this.#context.fillRect(0, 0, this.#canvas.width, this.#canvas.height);
    if (this.#selectedLevel == 2) {
      this.#context.drawImage(this.#img, 180, 180, 256, 144);
    }
    this.#context.fillStyle = "rgb(20,20,20)";
    this.#context.fillRect(
        50,
        590,
        400,
        120
    );
    this.#context.fillStyle = "white";
    this.#context.font = "40px Arial";
    this.#context.fillText("Personal high score:", 60, 640);
    this.#context.fillStyle = "yellow";

    this.#context.fillText(gameData.userScore, 60, 690);
    this.#context.fillStyle = "black";
    this.#context.font = "70px Arial";
    this.#context.fillText("Tram Game", this.#canvas.width / 2 - 190, 100);

    this.#context.font = "50px Arial";

    for (let i = 0; i < this.#levels.length; i++) {
      const buttonY = 200 + i * 100;
      this.#context.fillStyle =
        this.#selectedLevel === i ? "rgb(155,100,32)" : "rgb(20,20,20)";
      this.#context.fillRect(
        this.#canvas.width / 2 - 100,
        buttonY - 55,
        200,
        80
      );
      this.#context.fillStyle = "white";
      this.#context.fillText(
        this.#levels[i],
        this.#canvas.width / 2 - 75,
        buttonY
      );
    }

    if(gameData.userScore < 500){
      this.#context.fillStyle = "black";
      this.#context.fillRect(
          this.#canvas.width / 2 - 100,
          300 - 55,
          200,
          80
      );
      this.#context.fillStyle = "red";
      this.#context.font = "30px Arial";
      this.#context.fillText(
          "500 to unlock",
          this.#canvas.width / 2 - 86,
          300
      );

    }
    if(gameData.userScore < 1000){
      this.#context.fillStyle = "black";
      this.#context.fillRect(
          this.#canvas.width / 2 - 100,
          400 - 55,
          200,
          80
      );
      this.#context.fillStyle = "red";
      this.#context.font = "30px Arial";
      this.#context.fillText(
          "1000 to unlock",
          this.#canvas.width / 2 - 97,
          400
      );
    }
    const startButtonY = 200 + this.#levels.length * 100 + 20;
    this.#context.fillStyle = "rgb(70,0,80)";
    this.#context.fillRect(
      this.#canvas.width / 2 - 150,
      startButtonY - 55,
      300,
      80
    );

    this.#context.font = "50px Arial";

    this.#context.fillStyle = "white";
    this.#context.fillText(
      "Start Game",
      this.#canvas.width / 2 - 125,
      startButtonY
    );

    this.#context.font = "40px Arial";

    this.#context.fillStyle = "rgb(20,20,20)";
    this.#context.fillRect(this.#canvas.width - 250, 40, 200, 50);
    this.#context.fillStyle = "white";
    this.#context.fillText("Tutorial", this.#canvas.width - 210, 80);
  }

  handleMouseClick(event) {
    if (this.#menuIsOnTop) {
      const rect = this.#canvas.getBoundingClientRect();
      const mouseX = event.clientX - rect.left;
      const mouseY = event.clientY - rect.top;
      let it = this.#levels.length;
      if(gameData.userScore < 1000){it = 2};
      if (gameData.userScore < 500){ it = 1};
      for (let i = 0; i < it; i++) {
        const buttonY = 200 + i * 100;
        if (
          mouseX >= this.#canvas.width / 2 - 100 &&
          mouseX <= this.#canvas.width / 2 + 100 &&
          mouseY >= buttonY - 55 &&
          mouseY <= buttonY + 25
        ) {
          this.#selectedLevel = i;
          this.drawMenu();
          break;
        }
      }
      const startButtonY = 200 + this.#levels.length * 100 + 20;
      if (
        mouseX >= this.#canvas.width / 2 - 150 &&
        mouseX <= this.#canvas.width / 2 + 150 &&
        mouseY >= startButtonY - 55 &&
        mouseY <= startButtonY + 25 &&
        this.#selectedLevel !== null
      ) {
        this.startGame();
      }
      if (
        mouseX >= this.#canvas.width - 250 &&
        mouseX <= this.#canvas.width - 50 &&
        mouseY >= 40 &&
        mouseY <= 90
      ) {
        this.drawTutorial();
      }
    }
  }

  startGame() {
    this.#menuIsOnTop = false;
    this.#game.start(this.#selectedLevel);
  }
  drawTutorial(){
    this.#context.fillStyle = "rgb(60,60,60)";
    this.#context.fillRect(930, 90, 300, 600);

    this.#context.fillStyle = "white";
    this.#context.font = "20px Arial";
    this.#context.fillText("Change tracks: W / S", 950, 150);
    this.#context.fillText("Pick passengers: A", 950, 190);
    this.#context.fillText("Drop off passengers: D", 950, 230);
    this.#context.fillText("Goal: Gain as many points ", 950, 270);
    this.#context.fillText("as possible", 950, 295);
    this.#context.fillText("Earn extra points for a full tram", 950, 335);
    this.#context.fillText("Avoid obstacles!", 950, 375);
    this.#context.fillText("Unlock the next level ", 950, 415);
    this.#context.fillText("by reaching 1000 points", 950, 440);
    this.#context.fillText("Select level and press", 950, 480);
    this.#context.fillText("'Start Game' to begin", 950, 505);

  }
  

  getCanvas() {
    return this.#canvas;
  }
}
export default Menu;
