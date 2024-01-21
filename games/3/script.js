import {getUserData, updateRanking, updateUserCoins, saveGameData, loadGameData} from "greengame-api-client";

class Score {
    score = 0;
    username;

    constructor(score, username) {
        this.score = score;
        this.username = username;
    }

    getScore() {
        return this.score;
    }

    setScore(score) {
        this.score = score;
    }
}


function getList(text) {
    let input = text.charAt(0)
    let bitwise = 255
    switch (input) {
        case 'u':
            bitwise = bitwise & (0 + 2 + 4 + 0 + 16 + 32 + 64 + 0);
            break;
        case 'd':
            bitwise = bitwise & (1 + 0 + 0 + 8 + 16 + 0 + 64 + 128);
            break;
        case 'l':
            bitwise = bitwise & (0 + 0 + 4 + 8 + 0 + 32 + 64 + 128);
            break;
        case 'r':
            bitwise = bitwise & (1 + 2 + 0 + 0 + 16 + 32 + 0 + 128);
            break;
        case "a":
            return [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
    let output = text.charAt(1)
    switch (output) {
        case "u":
            bitwise = bitwise & (0 + 2 + 4 + 0 + 16 + 32 + 64 + 0);
            break;
        case "d":
            bitwise = bitwise & (1 + 0 + 0 + 8 + 16 + 0 + 64 + 128);
            break;
        case "l":
            bitwise = bitwise & (0 + 0 + 4 + 8 + 0 + 32 + 64 + 128);
            break;
        case "r":
            bitwise = bitwise & (1 + 2 + 0 + 0 + 16 + 32 + 0 + 128);
            break;
    }
    let start = 0;
    output = [8]
    while (bitwise > 0) {
        if (bitwise & 1) {
            output.push(start)
        }
        start = start + 1
        bitwise = bitwise >>> 1
    }
    return output
}
// ------------------------------------------------------------------------------------------------------------------------------------

class Point {
    constructor(x, y) {
        this.x = x;
        this.y = y;
        this.imageElement = null
    }

    removeImage() {
        if (this.imageElement)
            this.imageElement.remove();
        this.imageElement = null;
    }
}

class WireIntersection extends Point {
    constructor(x, y) {
        super(x, y);
        this.number = 0;
    }

    setNumber() {
        this.number++;
        if (this.number > 8)
            this.number = 0;

    }

    getNumber() {
        return this.number;
    }

    displayImage() {
        const image = document.createElement("img");
        image.setAttribute("style", "cursor: pointer;");

        const src = "images/thirdGameImages/" + this.number.toString() + ".png";
        image.setAttribute("src", src)

        image.style.position = "absolute";
        image.style.left = this.x + "px";
        image.style.top = this.y + "px";

        image.addEventListener("click", () => {
            this.setNumber();
            this.displayImage();
        });
        this.removeImage()
        this.imageElement = image;
        document.body.appendChild(image);
    }
}

class Switch extends Point {

    constructor(x, y) {
        super(x, y);
        this.isSwitched = false;
    }

    switch() {
        this.isSwitched = !this.isSwitched;
    }

    getIsSwitched() {
        return this.isSwitched;
    }
}

class Device {
    constructor(listOfWireIntersections, shouldntBeLaunched, listOfStates) {
        this.listOfWireIntersections = listOfWireIntersections;
        this.shouldntBeLaunched = shouldntBeLaunched;
        this.listOfStates = listOfStates;
    }

    getIsPowered() {
        for (let i = 0; i < this.listOfWireIntersections.length; i++) {
            if (!this.listOfStates[i].includes(this.listOfWireIntersections[i].getNumber())) {
                return false;
            }
        }
        return true;
    }

    getShouldntBeLaunched() {
        return this.shouldntBeLaunched;
    }
}


class Quiz {
    quizId;
    levelId;
    displayTime;
    wasDisplayed;

    constructor(levelId, quizId) {
        this.levelId = levelId
        this.quizId = quizId;
        this.wasDisplayed = false;
        
        // Get when quiz should be displayed from html div element class display-ss
        this.displayTime = Number(document.querySelector(`#quiz-${this.levelId}-${this.quizId}`).classList[3].substring(8));
    }

    getWasDisplayed() {
        return this.wasDisplayed;
    }

    getQuizId() {
        return this.quizId;
    }

    // Show html modal window div with quiz Question
    showQuestion() {
        // hide pasue button
        const pasueBtn = document.getElementById("pause-resume-btn");
        pasueBtn.setAttribute("hidden", "");
        
        // show modal
        const quizModal = new bootstrap.Modal(document.getElementById(`quiz-${this.levelId}-${this.quizId}`));
        quizModal.show();
        this.wasDisplayed = true;
    }

    // Check if answer html button element class list contains 'correct' value
    checkAnswer(event) {
        const isCorrect = event.target.classList.value.includes('correct');
        this.displayQuizMessage(isCorrect);
        return isCorrect;
    }

    // Display correct/incorrect answer toast 
    displayQuizMessage(isCorrect) {
        if (isCorrect) {
            const correctAnswerToast = document.getElementById('correctAnswerToast')
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(correctAnswerToast);
            toastBootstrap.show();
            const quizModal = new bootstrap.Modal(document.getElementById(`quiz-${this.levelId}-${this.quizId}`));
            quizModal.hide();

            // show pause button
            const pasueBtn = document.getElementById("pause-resume-btn");
            pasueBtn.removeAttribute("hidden");
        } else {
            const wrongAnswerToast = document.getElementById('wrongAnswerToast');
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(wrongAnswerToast);
            toastBootstrap.show();
        }
    }
}

// ----------------------------------------------------------------------------------------------------------------------------------

class Level {
    levelId;
    availableTime;
    isRunning = false;
    quizes = [];
    listOfWireIntersections = [];
    listOfDevices = [];
    mistakesCount;
    switch;
    bg;

    constructor(levelId, levelTime, amountOfIntersections, listOfPositions, bg, listOfStates, listOfShouldntBeLaunched, swPos) {
        this.levelId = levelId;
        this.availableTime = levelTime;
        this.mistakesCount = 0;
        this.switch = new Switch(swPos[0], swPos[1] + 60);
        this.bg = bg;


        // Initialize all quizzes that belongs to level
        const levelQuizzes = document.querySelectorAll(`.quiz-${this.levelId}`);
        levelQuizzes.forEach((quiz, index) => {
            this.quizes.push(new Quiz(this.levelId, index));

            // Assign event listener on click for every question answer button
            const quizAnswers = document.querySelectorAll(`.quiz-${this.levelId}-${this.quizes[this.quizes.length - 1].getQuizId()}-answer`);
            quizAnswers.forEach((answer) => {
                answer.addEventListener('click', () => {
                    const isCorrectAnswer = this.quizes[this.quizes.length - 1].checkAnswer(event);
                    if (!isCorrectAnswer) {
                        this.availableTime -= 10;
                    }
                })
            })
        });


        for (let i = 0; i < amountOfIntersections; i++) {
            this.listOfWireIntersections.push(new WireIntersection(listOfPositions[i][0], listOfPositions[i][1] + 60));
        }

        for (let i = 0; i < listOfStates.length; i++) {
            this.listOfDevices.push(new Device(this.listOfWireIntersections, listOfShouldntBeLaunched[i], listOfStates[i]));
        }        
    }

    playLevel() {
        // Timer logic
        const gameTimeInterval = setInterval(() => {
            if(this.isRunning) {
                this.availableTime--;
 
                // Count time to display on timer
                let min = Math.floor(this.availableTime / 60);
                let sec = this.availableTime - min * 60;
                
                // Display current time in html p element
                const time = document.querySelector(`#time`);
                time.textContent =  `${min < 10 ? '0' + min : min}:${sec < 10 ? '0' + sec : sec}`
    
                // Check if any quiz should be displayed to player
                this.quizes.forEach((quiz) => {
                    if(quiz.displayTime >= this.availableTime) {
                        if(!quiz.getWasDisplayed()) {
                            quiz.showQuestion();
                        }
                    }
                });
            }
            
            // Check if player used switch 
            if (this.switch.getIsSwitched()) {
                clearInterval(gameTimeInterval);
            }
            
            // Check if player ran out of available time
            if(this.availableTime <= 0) {
                // Set timer to 00:00
                const timer = document.querySelector(`#time`);
                timer.textContent = "00:00";
                
                clearInterval(gameTimeInterval);
            }

        }, 1000);


        const image = document.createElement("img");
        image.setAttribute("style", "cursor: pointer;");

        if (this.switch.getIsSwitched())
            image.setAttribute("src", "images/thirdGameImages/switchOn.png")
        else
            image.setAttribute("src", "images/thirdGameImages/switchOff.png")

        image.style.position = "absolute";
        image.style.left = this.switch.x + "px";
        image.style.top = this.switch.y + "px";

        image.addEventListener("click", () => {
            this.switch.switch();
            this.displayImage();
        });
        this.switch.removeImage()
        this.switch.imageElement = image;
        document.body.appendChild(image);

        // show pause btn
        const pasueBtn = document.getElementById("pause-resume-btn");
        pasueBtn.removeAttribute("hidden");

        // hide welcome div 
        const welcomeDiv = document.querySelector(`#welcome-div`);
        welcomeDiv.setAttribute("hidden", "");

        // hide level dropdown
        const levelDropdown = document.querySelector(`#level-dropdown`);
        levelDropdown.setAttribute("hidden", "");
    }

    getMadeMistakes() {
        let mistakesCount = 0;
        for(let i = 0; i < this.listOfDevices.length; i++)
        {
            if(this.listOfDevices[i].getIsPowered())
            {
                if(this.listOfDevices[i].getShouldntBeLaunched())
                {
                    mistakesCount += 1;
                }
            } else {
                if(!this.listOfDevices[i].getShouldntBeLaunched()) {
                    mistakesCount += 1;
                }
            }
        }
        return mistakesCount;
    }

    displayImage() {
        const image = document.createElement("img");

        const src = "images/thirdGameImages/" + this.bg + ".svg";
        image.setAttribute("src", src);

        image.style.position = "absolute";
        image.style.left = 0 + "px";
        image.style.top = 60 + "px";
        document.body.appendChild(image);

        for (let i = 0; i < this.listOfWireIntersections.length; i++) {
            this.listOfWireIntersections[i].displayImage();
        }
        this.playLevel();
    }

    isGameEnded() {
        return this.availableTime <= 0 || this.switch.getIsSwitched();
    }

    getAvailableTime() {
        return this.availableTime;
    }

    setIsRunning(isRunning) {
        this.isRunning = isRunning;
    }

    getIsRunning() {
        return this.isRunning;
    }
}

// ------------------------------------------------------------------------------------------------------------------------------------

class Game {
    gameID = 3;
    isRunning = true;
    score;
    level;
    maxLevelUnlocked = 1;

    constructor(username, maxLevelUnlocked, userScore) {
        this.maxLevelUnlocked = maxLevelUnlocked;
        this.score = new Score(userScore, username);

        // lock unavaiable for current user level
        const levelBtn = document.querySelectorAll(`.level-btn`);
        levelBtn.forEach((level, index) => {
            if (index + 1 > this.maxLevelUnlocked) {
                level.setAttribute("disabled", "");
            }
        });

        // Add event listeners for click on button for level choose
        const levelBtns = document.querySelectorAll(`.level-btn`);
        levelBtns.forEach((levelBtn, index) => {
            levelBtn.addEventListener('click', (event) => {
                
                if (index === 0) {
                    // Level 1
                    this.level = new Level(0, 60, 4, new Array([9.5, 13], [9.5, 306], [761.5, 306], [1241.5, 306]), "lvl1", [
                            [
                                getList("rd"), getList("ur"), getList("lr"), getList("ld")
                            ],
                            [
                                getList("a"), getList("a"), getList("ur"), getList("ld")
                            ],
                            [
                                getList("a"), getList("a"), getList("a"), getList("du")
                            ]
                        ],
                        [false, true, true],
                        [1153.9, 452]
                        )
                }
                if (index === 1) {
                    // Level 2
                    this.level = new Level(1, 120, 9, new Array([24.5, 8.8], [351.8, 8.8],
                            [24.5, 309.8], [351.8, 309.8], [755, 309.8], [1220.5, 309.8],
                            [24.5, 635.5], [351.8, 635.5], [755, 635.5]), "lvl2", [
                            [
                                getList("rd"), getList("ld"), getList("ur"), getList("ur"), getList("lr"), getList("ld"), getList("a"), getList("a"), getList("a")
                            ],
                            [
                                getList("a"), getList("a"), getList("a"), getList("a"), getList("ur"), getList("ld"), getList("a"), getList("a"), getList("a")
                            ],
                            [
                                getList("rd"), getList("ld"), getList("ud"), getList("ur"), getList("lr"), getList("ld"), getList("a"), getList("lu"), getList("a")
                            ],
                            [
                                getList("rd"), getList("ld"), getList("ud"), getList("ur"), getList("lr"), getList("ld"), getList("a"), getList("lr"), getList("lr")
                            ]
                        ],
                        [true, false, false, true],
                        [1133.8, 463.2]
                    )
                }

                if (index === 2) {
                    // Level 3
                    this.level = new Level(2, 150, 8, new Array([40.7, 66.7], [363.2, 66.7], [760.5, 66.7], [1218.9, 66.7],
                            [363.2, 301.8],
                            [40.7,623], [152.5, 623], [401.7, 623]), "lvl3", [
                            [
                                getList("a"), getList("rd"), getList("lr"), getList("ld"), getList("lu"), getList("a"), getList("a"), getList("a")
                            ],
                            [
                                getList("a"), getList("a"), getList("rd"), getList("ld"), getList("a"), getList("a"), getList("a"), getList("a")
                            ],
                            [
                                getList("rd"), getList("lr"), getList("lr"), getList("ld"), getList("a"), getList("ur"), getList("lu"), getList("a")
                            ],
                            [
                                getList("rd"), getList("lr"), getList("lr"), getList("ld"), getList("a"), getList("ur"), getList("lr"), getList("lu")
                            ],
                            [
                                getList("rd"), getList("lr"), getList("lr"), getList("ld"), getList("a"), getList("ur"), getList("lr"), getList("lr")
                            ]
                        ],
                        [false, false, false, false, true],
                        [1132.4, 450.3]
                    )
                }

                if (index === 3) {
                    // Level 4
                    this.level = new Level(3, 180, 17, new Array(
                        [853.5, 62],
                        [34.5, 224.5], [146.5, 224.5], [364.5, 224.5], [612.5, 224.5], [853.5, 224.5], [1006, 224.5], [1213.5, 224.5],
                            [1213.5, 341],
                            [34.5, 404.2], [364.5, 404.2], [612.5, 404.2], [853.5, 404.2], [1213.5, 404.2],
                            [34.5, 621.5], [364.5, 621.5], [853.5, 621.5]), "lvl4", [
                            [
                                getList("a"),
                                getList("a"), getList("ur"), getList("lr"), getList("lr"), getList("lr"), getList("lr"), getList("ld"),
                                getList("ud"),
                                getList("a"), getList("a"), getList("a"), getList("rd"), getList("lu"),
                                getList("a"), getList("a"), getList("ru"),
                            ],
                            [
                                getList("ld"),
                                getList("a"), getList("a"), getList("a"), getList("a"), getList("lu"), getList("lr"), getList("ld"),
                                getList("ud"),
                                getList("a"), getList("a"), getList("a"), getList("rd"), getList("lu"),
                                getList("a"), getList("a"), getList("ru"),
                            ],
                            [
                                getList("a"),
                                getList("a"), getList("a"), getList("a"), getList("a"), getList("a"), getList("ur"), getList("ld"),
                                getList("ud"),
                                getList("a"), getList("a"), getList("a"), getList("rd"), getList("lu"),
                                getList("a"), getList("a"), getList("ru"),
                            ],
                            [
                                getList("a"),
                                getList("rd"), getList("lr"), getList("lr"), getList("lr"), getList("lr"), getList("lr"), getList("ld"),
                                getList("ud"),
                                getList("ur"), getList("a"), getList("a"), getList("rd"), getList("lu"),
                                getList("a"), getList("a"), getList("ru"),
                            ],
                            [
                                getList("a"),
                                getList("rd"), getList("lr"), getList("lr"), getList("lr"), getList("lr"), getList("lr"), getList("ld"),
                                getList("ud"),
                                getList("ud"), getList("dr"), getList("a"), getList("rd"), getList("lu"),
                                getList("ur"), getList("lu"), getList("ru"),
                            ],
                            [
                                getList("a"),
                                getList("a"), getList("a"), getList("a"), getList("a"), getList("a"), getList("a"), getList("a"),
                                getList("a"),
                                getList("a"), getList("a"), getList("ur"), getList("ld"), getList("a"),
                                getList("a"), getList("a"), getList("ru"),
                            ],
                            [
                                getList("a"),
                                getList("a"), getList("a"), getList("a"), getList("a"), getList("a"), getList("a"), getList("a"),
                                getList("ld"),
                                getList("a"), getList("a"), getList("a"), getList("rd"), getList("lu"),
                                getList("a"), getList("a"), getList("ru"),
                            ],
                            [
                                getList("a"),
                                getList("rd"), getList("lr"), getList("lr"), getList("lr"), getList("lr"), getList("lr"), getList("ld"),
                                getList("ud"),
                                getList("ud"), getList("a"), getList("a"), getList("rd"), getList("lu"),
                                getList("ur"), getList("lr"), getList("ru"),
                            ],
                            [
                                getList("a"),
                                getList("a"), getList("a"), getList("a"), getList("a"), getList("a"), getList("a"), getList("a"),
                                getList("a"),
                                getList("a"), getList("a"), getList("a"), getList("a"), getList("a"),
                                getList("a"), getList("a"), getList("lr"),
                            ]
                        ],
                        [false, false, true,
                            true, false, false, true,
                        false, false],
                        [1127.3, 449.3]
                    )
                }

                this.level.setIsRunning(true);
                this.level.displayImage();

                // Check if level is still running
                const checkLevelRunning = setInterval(() => {
                if (this.level.isGameEnded()) {
                    this.showScoreBoard();
                    clearInterval(checkLevelRunning);
                }
                }, 500);
            });
        });
        
        // Pause mechamism in game
        const pauseResumeBtn = document.getElementById("pause-resume-btn");
        pauseResumeBtn.addEventListener("click", () => {
            const pauseIcon = document.getElementById("pause-icon");
            const resumeIcon = document.getElementById("resume-icon");
            const overlay = document.getElementById("overlay");

            if(!this.level.getIsRunning()) {
                resumeIcon.setAttribute("hidden", "");
                pauseIcon.removeAttribute("hidden");
                overlay.setAttribute("hidden", "");
                this.resumeGame();
            } else {
                resumeIcon.removeAttribute("hidden");
                pauseIcon.setAttribute("hidden", "");
                overlay.removeAttribute("hidden");
                this.stopGame();
            }
        });
    }



    showScoreBoard() {
        const scoreModal = new bootstrap.Modal(document.getElementById(`score-board`));

        let levelScore = 0;
        const levelScoreText = document.getElementById("level-score");

        if (this.level.getMadeMistakes() == 0) {
            levelScore = 2 * this.level.getAvailableTime();
            if(levelScore < 0) {
                levelScore = 0;
            }
            this.score.score += levelScore;

            if (this.maxLevelUnlocked < this.level.levelId + 2) {
                this.maxLevelUnlocked = this.level.levelId + 2;
            }

            levelScoreText.textContent = `Wynik: ${levelScore} punktów`;
       } else {
            levelScoreText.textContent = 'Niestety nie udało Ci się przejść poziomu.'
       }

        this.stopGame();
        scoreModal.show();

        // hide pasue btn
        const pasueBtn = document.getElementById("pause-resume-btn");
        pasueBtn.setAttribute("hidden", "");
    }

    resumeGame() {
        this.level.setIsRunning(true);
    }

    stopGame() {
        this.level.setIsRunning(false);
    }
}

// ----------------------------------------------------------------------------------------------------------------------------------

class GameApiClient {
    
    async getGame() {
        const userData = await getUserData();
        const gameData = await loadGameData({ gameId: 3 });

        if (gameData != null) {
            return new Game(userData.username, gameData.maxLevelUnlocked, gameData.userScore);
        } else {
            return new Game(userData.username, 1, 0);
        }
    }

    async saveGame(game) {

        const rankingData = {
            "gameID": 3,
            "score": game.score.score
        }

        const gameData = {
            "gameId": 3,
            "gameData": {
                "maxLevelUnlocked": game.maxLevelUnlocked,
                "userScore": game.score.score
            }
        }

        await updateRanking(rankingData);
        await saveGameData(gameData);
        location.reload();
    }
}

// ----------------------------------------------------------------------------------------------------------------------------------

class App {
    game;

    constructor() {
        this.startGame();

        // Add event listener on save game button
        const saveGameBtn = document.getElementById("btn-save-game");
        saveGameBtn.addEventListener("click", () => {
            this.saveGame();

            // show level dropdown
            const levelDropdown = document.querySelector(`#level-dropdown`);
            levelDropdown.removeAttribute("hidden");
        });
    }

    async startGame() {
        const gameApiClient = new GameApiClient()
        this.game = await gameApiClient.getGame(); 

        
        // Display welcome header
        const welcomeText = document.querySelector(`#header-wecome`);
        welcomeText.textContent = `Witaj ${this.game.score.username}!`;

        // Display current player score
        const scoreText = document.querySelector(`#score-text`);
        scoreText.textContent = `${this.game.score.score} punktów`;

        // show welcome div 
        const welcomeDiv = document.querySelector(`#welcome-div`);
        welcomeDiv.removeAttribute("hidden");
    }

    saveGame() {
        const gameApiClient = new GameApiClient();
        gameApiClient.saveGame(this.game);
    }
}

const app = new App();










