import {updateRanking, loadGameData, saveGameData} from "https://unpkg.com/greengame-api-client@latest";
//DECLARATIVE SECTION
const music = new Audio('assets/music.mp3');
const gameOverSound = new Audio('assets/lost.mp3');
const gameWonSound = new Audio('assets/victory.mp3');
const explosionSound1 = new Audio('assets/explosion.mp3');
const explosionSound2 = new Audio('assets/explosion.mp3');
const explosionSound3 = new Audio('assets/explosion.mp3');
const laserSound1 = new Audio('assets/laser.mp3')
const laserSound2 = new Audio('assets/laser.mp3')
const laserSound3 = new Audio('assets/laser.mp3')

const button_e = document.getElementById("button_e")
const button_m = document.getElementById("button_m")
const button_h = document.getElementById("button_h")
const button_start = document.getElementById("button_start")
const button_instruction = document.getElementById("button_instruction")
const button_instruction_ret = document.getElementById("button_instruction_ret")
const button_reset = document.getElementById("button_reset")
const body1 = document.getElementById("body1");
const body2 = document.getElementById("body2");
const body3 = document.getElementById("body3");
const instruction = document.getElementById("instruction")
const ship = document.getElementById('ship');
const laserContainer = document.getElementById("lasers");
const rubbishContainer = document.getElementById("invaders");
const enemyContainer = document.getElementById("enemies");
const score = document.getElementById("score");
const score2 = document.getElementById("score2");
const heatMeter = document.getElementById("heat-meter");
const gameContainer = document.getElementById("game");
const laserList = [];
const enemyList = [];
const rubbishList = [];
const intervalList = [];
const gameData = await loadGameData({gameId: 1})
let scoreValue = 0;
let difficulty;

//LASER SECTION
const LaserType = {
  YELLOW: 'YELLOW',
  BLUE: 'BLUE',
  GREEN: 'GREEN',
};

const  Difficulty = {
  EASY: 0.75,
  MEDIUM: 1,
  HARD: 1.5
}

class Laser {
  constructor(color, posX) {
    this.element = document.createElement('div');
    this.element.classList.add('laser');
    this.posX = posX + 22;
    this.posY = 640;
    this.color = color;
    this.element.style.left = this.posX + 'px';
    this.element.style.top = this.posY + 'px';
    this.element.style.backgroundColor = color;
    laserContainer.appendChild(this.element);
  }

  move() {
    this.posY -= 10;
    this.element.style.top = this.posY + 'px';
    if (this.posY <= 0) {
      laserContainer.removeChild(this.element);
      laserList.splice(laserList.indexOf(this), 1);
    }
  }
}

//RUBBISH SECTION
const RubbishType = {
  PLASTIC: 'PLASTIC',
  PAPER: 'PAPER',
  GLASS: 'GLASS',
};

class Rubbish {
  constructor(posX, posY, type, speed) {
    this.element = document.createElement('div');
    this.element.classList.add('invader');
    this.weight = Math.floor(Math.random() * 3) + 1;
    this.type = type
    this.posX = posX;
    this.posY = posY;
    this.speed = speed;
    this.moveCounter = 0;
    this.putTexture();

    this.element.style.left = this.posX + "px";
    this.element.style.top = this.posY + "px";
    rubbishContainer.appendChild(this.element);
  }

  move() {
    if (this.speed === Difficulty.HARD || this.speed === -Difficulty.HARD) {
      if (this.moveCounter === 171/Difficulty.HARD) {
        this.posY += 40
        this.moveCounter = 0;
        this.speed = this.speed * -1;
      }
    } else if (this.speed === Difficulty.MEDIUM || this.speed === -Difficulty.MEDIUM)
    {
      if (this.moveCounter === 171) {
        this.posY += 40
        this.moveCounter = 0;
        this.speed = this.speed * -1;
      }
    } else if (this.speed === Difficulty.EASY || this.speed === -Difficulty.EASY)
    {
      if (this.moveCounter === 171/Difficulty.EASY) {
        this.posY += 40
        this.moveCounter = 0;
        this.speed = this.speed * -1;
      }
    }
    this.posX += this.speed;
    this.moveCounter += 1;
    this.element.style.left = this.posX + "px";
    this.element.style.top = this.posY + "px";
  }

  putTexture() {
    switch (this.weight) {
      case 1:
        switch (this.type) {
          case "PLASTIC": {
            this.element.style.backgroundImage = "url(assets/plastic1.png)";
            break;
          }
          case "PAPER": {
            this.element.style.backgroundImage = "url(assets/paper1.png)";
            break;
          }
          case "GLASS": {
            this.element.style.backgroundImage = "url(assets/glass1.png)";
            break;
          }
        }
        break;
      case 2:
        switch (this.type) {
          case "PLASTIC": {
            this.element.style.backgroundImage = "url(assets/plastic2.png)";
            break;
          }
          case "PAPER": {
            this.element.style.backgroundImage = "url(assets/paper2.png)";
            break;
          }
          case "GLASS": {
            this.element.style.backgroundImage = "url(assets/glass2.png)";
            break;
          }
        }
        break;
      case 3:
        switch (this.type) {
          case "PLASTIC": {
            this.element.style.backgroundImage = "url(assets/plastic3.png)";
            break;
          }
          case "PAPER": {
            this.element.style.backgroundImage = "url(assets/paper3.png)";
            break;
          }
          case "GLASS": {
            this.element.style.backgroundImage = "url(assets/glass3.png)";
            break;
          }
        }
        break;
    }
  }

  spawnEnemy() {
    let type =  Math.floor(Math.random() * 2) + 1;
    let eType
    if (type === 1) {
      eType = EnemyType.RAT
    }
    else {
      eType = EnemyType.COCKROACH
    }
    const enemy = new Enemy(this.posX + 32, this.posY + 64, player.posX, eType)
    enemyList.push(enemy);
  }
}

const EnemyType = {
  RAT: "url(assets/rat.png)",
  COCKROACH: "url(assets/cockroach.png)"
};

class Enemy {
  constructor(posX, posY, targetX, enemyType) {
    this.element = document.createElement('div');
    this.element.classList.add('enemy');
    this.type = enemyType;
    this.posX = posX;
    this.posY = posY;

    this.element.style.backgroundImage = this.type;
    this.element.style.left = this.posX + "px";
    this.element.style.top = this.posY + "px";
    enemyContainer.appendChild(this.element);
  }

  move() {
    this.posY += 10
    this.element.style.top = this.posY + "px";
    if (this.posY >= 656) {
      enemyContainer.removeChild(this.element);
      enemyList.splice(enemyList.indexOf(this), 1);
    }
  }
}

//PLAYER SECTION
class Player {
  constructor() {
    this.posX = 500;
    this.posY = 650;
    this.heat = 0.0;
    this.shot = false;
    this.overheated = false;
    this.laserType = LaserType.YELLOW;
  }

  move(event) {
    if (event.key === "ArrowLeft" && this.posX > 0) {
      this.posX -= 5;
    } else if (event.key === "ArrowRight" && this.posX < 950) {
      this.posX += 5;
    }
    
    ship.style.left = this.posX + "px";
  }

  changeLaserType(event) {
    if (event.key === '1') {
      this.laserType = LaserType.YELLOW;
      document.getElementById("laser1").style.backgroundColor = "bisque";
      document.getElementById("laser2").style.backgroundColor = "black";
      document.getElementById("laser3").style.backgroundColor = "black";
    }
    if (event.key === '2') {
      this.laserType = LaserType.BLUE;
      document.getElementById("laser1").style.backgroundColor = "black";
      document.getElementById("laser2").style.backgroundColor = "bisque";
      document.getElementById("laser3").style.backgroundColor = "black";
    }
    if (event.key === '3') {
      this.laserType = LaserType.GREEN;
      document.getElementById("laser1").style.backgroundColor = "black";
      document.getElementById("laser2").style.backgroundColor = "black";
      document.getElementById("laser3").style.backgroundColor = "bisque";
    }
  }

  shoot(event) {
    if (event.key === ' ') {
      if (this.heat < 345 && !this.overheated && !this.shot) {
        this.heat += 15;
        heatMeter.style.height = 345 - this.heat + "px";
        const shipPositionY = this.posY;
        const laser = new Laser(this.laserType, this.posX, shipPositionY);
        laserList.push(laser);
        this.shot = true;
        if (laserSound1.paused) {
          laserSound1.play();
        } else if (laserSound2.paused) {
          laserSound2.play();
        } else if (laserSound3.paused) {
          laserSound3.play();
        }
        setTimeout(() => this.shot = false, 200)
      }
      if (this.heat === 345) {
        this.overheated = true;
      }
    }
  }

  coolDown(event) {
    if (event.key === 'r') {
        const intervalId = setInterval(() => {
          if (this.heat === 0) {
            clearInterval(intervalId);
            this.overheated = false;
            return;
          }
          this.heat -= 2;
          if (this.heat < 0)
            this.heat = 0;
          heatMeter.style.height = 345 - this.heat + "px";
        }, 10);
      }
  }
}

//FUNCTION SECTION
function convertScore(num) {
  let strNum = num.toString();
  while (strNum.length < 5) {
    strNum = "0" + strNum;
  }
  score.innerText = strNum;
  score2.innerText = strNum;
}
function spawnRow(posX, posY, keys, difficulty) {
  for (let i = 0; i < 10; i++) {
    let randomKey = keys[Math.floor(Math.random() * keys.length)];
    const rubbish = new Rubbish(posX, posY, randomKey, difficulty);
    posX += 80;
    rubbishList.push(rubbish);
  }
}
function spawnRubbish(difficulty) {
  const keys = Object.keys(RubbishType);
  let posX = 0;
  let posY = 5;

  spawnRow(posX, posY, keys, difficulty);
  posY += 80;
  posX = 40
  spawnRow(posX, posY, keys, difficulty);
  posY += 80;
  posX = 0
  spawnRow(posX, posY, keys, difficulty);
  posY += 80;
  posX = 40
  spawnRow(posX, posY, keys, difficulty);
  posY += 80;
  posX = 0
  spawnRow(posX, posY, keys, difficulty);
}

function checkCollision() {
    laserList.forEach(function(laser) {
      rubbishList.forEach(function(rubbish) {
        if (laserList.length > 0) {
          if (laser.posX >= rubbish.posX && laser.posX <= rubbish.posX + 64) {
            if (laser.posY >= rubbish.posY && laser.posY <= rubbish.posY + 64) {
              laserContainer.removeChild(laser.element);
              laserList.splice(laserList.indexOf(laser), 1);
              if ((laser.color === "YELLOW" && rubbish.type === "PLASTIC") ||
              (laser.color === "BLUE" && rubbish.type === "PAPER") ||
              (laser.color === "GREEN" && rubbish.type === "GLASS")) {
                rubbish.weight -= 1;
                switch (difficulty) {
                  case Difficulty.EASY:
                    scoreValue += 50;
                    break;
                  case Difficulty.MEDIUM:
                    scoreValue += 65;
                    break;
                  case Difficulty.HARD:
                    scoreValue += 80;
                    break;
                }
                convertScore(scoreValue);
                rubbish.putTexture();
                if (explosionSound1.paused) {
                  explosionSound1.play();
                } else if (explosionSound2.paused) {
                  explosionSound2.play();
                } else if (explosionSound3.paused) {
                  explosionSound3.play();
                }

                if (rubbish.weight === 0) {
                  rubbishContainer.removeChild(rubbish.element);
                  rubbishList.splice(rubbishList.indexOf(rubbish), 1);
                  if (rubbishList.length === 0 ) {
                    gameWon();
                  }
                }
              }
            }
          }
        }
      });
    })
}

function checkEnemyCollision() {
  enemyList.forEach(function(enemy) {
    if (enemyList.length > 0) {
      if (enemy.posX + 50 >= player.posX && enemy.posX <= player.posX + 50) {
        if (enemy.posY + 64 >= player.posY && enemy.posY <= player.posY + 30) {
          gameOver();
        }
      }
    }
  })
}

function cleanUp() {
  while (gameContainer.firstChild) {
    gameContainer.removeChild(gameContainer.firstChild);
  }
  music.pause();
  intervalList.forEach(function(interval) {
    clearInterval(interval);
  })

  document.removeEventListener('keydown', moveHandler);
  document.removeEventListener('keydown', changeLaserTypeHandler);
  document.removeEventListener('keydown', shootHandler);
  document.removeEventListener('keydown', coolDownHandler);
}

async function gameWon() {
  cleanUp();

  await gameWonSound.play();
  gameContainer.textContent = "GAME WON";
  await updateRanking({gameID: 1, score: scoreValue});
  await saveGameData({
    gameId: 1,
    gameData: {difficulty}});
  setTimeout(() => toggleBodies2(), 5000);
}

async function gameOver() {
  cleanUp();

  await gameOverSound.play();
  gameContainer.textContent = "GAME OVER";
  setTimeout(() => toggleBodies2(), 5000);
}

function toggleBodies() {
  body1.style.display = "none";
  body2.style.display = "block";
  body3.style.display = "none";

  setupGame();
  for (let i = 0; i < gameContainer.childNodes.length; i++) {
    let childNode = gameContainer.childNodes[i];
    if (childNode.nodeType === 1) {
      childNode.style.display = "none";
    }
  }

  gameContainer.firstChild.textContent = "3";
  setTimeout(function() {
    gameContainer.firstChild.textContent = "2";
    setTimeout(function() {
      gameContainer.firstChild.textContent = "1";
      setTimeout(function() {
        gameContainer.firstChild.textContent = "";
        for (let i = 0; i < gameContainer.childNodes.length; i++) {
          let childNode = gameContainer.childNodes[i];
          if (childNode.nodeType === 1) {
            childNode.style.display = "block";
          }
        }
        setTimeout(function() {
          document.addEventListener('keydown', moveHandler);
          document.addEventListener('keydown', changeLaserTypeHandler);
          document.addEventListener('keydown', shootHandler);
          document.addEventListener('keydown', coolDownHandler);
          prepareIntervalsAndListeners();
        }, 1000);
      }, 1000);
    }, 1000);
  }, 1000);
}

function toggleBodies2() {
  body1.style.display = "none";
  body2.style.display = "none";
  body3.style.display = "block";
}

function toggleDifficulty(dif) {
  switch (dif) {
    case Difficulty.EASY:
      difficulty = dif;
      button_e.style.outlineColor = "white";
      button_m.style.outlineColor = "black";
      button_h.style.outlineColor = "black";
      break;
    case Difficulty.MEDIUM:
      if (gameData != null) {
        if (gameData.difficulty >= 0.75) {
          difficulty = dif;
          button_e.style.outlineColor = "black";
          button_m.style.outlineColor = "white";
          button_h.style.outlineColor = "black";
        }
      }
      break;
    case Difficulty.HARD:
      if (gameData != null) {
        if (gameData.difficulty >= 1) {
          difficulty = dif;
          button_e.style.outlineColor = "black";
          button_m.style.outlineColor = "black";
          button_h.style.outlineColor = "white";
        }
      }
      break;
  }
  if (gameData != null) {
    switch (gameData.difficulty) {
      case 1:
        break;
      case 0.75:
        button_h.style.outlineColor = "red";
        break;
    }
  } else {
    button_m.style.outlineColor = "red";
    button_h.style.outlineColor = "red";
  }
}

function toggleInstruction() {
  if (body1.style.display === "none") {
    body1.style.display = "block";
    instruction.style.display = "none";
  } else {
    body1.style.display = "none";
    instruction.style.display = "block";
  }
}

function prepareIntervalsAndListeners() {
  const laserMoveInterval = setInterval(() => {
    for (const laser of laserList) {
      laser.move();
    }}, 50);
  intervalList.push(laserMoveInterval);

  const rubbishMoveInterval = setInterval(() => {
    for (const rubbish of rubbishList) {
      rubbish.move();
    }
  }, 100);
  intervalList.push(rubbishMoveInterval);

  const enemiesMoveInterval = setInterval(() => {
    for (const enemy of enemyList) {
      enemy.move();
    }
  }, 60);
  intervalList.push(enemiesMoveInterval);

  const gameOverInterval = setInterval(() => {
    if (rubbishList[rubbishList.length-1].posY === 605) {
      gameOver();
    }
  }, 500);
  intervalList.push(gameOverInterval);

  const spawnEnemyInterval = setInterval(() => {
    let index = Math.floor(Math.random() * rubbishList.length);
    rubbishList[index].spawnEnemy();
  }, 5000);
  intervalList.push(spawnEnemyInterval);

  const musicInterval = setInterval(() => {if (music.paused) music.play()}, 100);
  intervalList.push(musicInterval);

  const collisionInterval = setInterval(() => checkCollision(), 10);
  intervalList.push(collisionInterval);

  const enemyCollisionInterval = setInterval(() => checkEnemyCollision(), 10);
  intervalList.push(enemyCollisionInterval);
}

function setupGame() {
  document.getElementById("laser1").style.backgroundColor = "bisque";
  document.getElementById("laser2").style.backgroundColor = "black";
  document.getElementById("laser3").style.backgroundColor = "black";
  convertScore(scoreValue);
  spawnRubbish(difficulty);
}

function resetGame() {
  body1.style.display = "block";
  body2.style.display = "none";
  body3.style.display = "none";

  location.reload();
}

//LISTENER AND INTERVAL SECTION
function coolDownHandler(event) {
  player.coolDown(event);
}
function moveHandler(event) {
  player.move(event);
}
function changeLaserTypeHandler(event) {
  player.changeLaserType(event);
}
function shootHandler(event) {
  player.shoot(event);
}

function toggleEasy() {
  toggleDifficulty(Difficulty.EASY)
}
function toggleMedium() {
  toggleDifficulty(Difficulty.MEDIUM)
}
async function toggleHard() {
  toggleDifficulty(Difficulty.HARD)
}

button_e.addEventListener('click', toggleEasy);
button_m.addEventListener('click', toggleMedium);
button_h.addEventListener('click', toggleHard);
button_start.addEventListener('click', toggleBodies);
button_reset.addEventListener('click', resetGame);
button_instruction.addEventListener('click', toggleInstruction);
button_instruction_ret.addEventListener('click', toggleInstruction);

//GAME SECTION
const player = new Player();
difficulty = Difficulty.EASY;
if (gameData != null) {
  switch (gameData.difficulty) {
    case 1:
      break;
    case 0.75:
      button_h.style.outlineColor = "red";
      break;
  }
} else {
  button_m.style.outlineColor = "red";
  button_h.style.outlineColor = "red";
}