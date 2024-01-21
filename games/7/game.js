import {loadGameData, saveGameData, updateRanking, } from "greengame-api-client";


const clickSpace = document.querySelector(".click-space");
const displayText = document.querySelector(".display-text");
const scoreElements = document.querySelectorAll(".score");
const game = document.getElementById("game");


var healthyProducts = [
    "images/healthy/apple.png",
    "images/healthy/avocado.jpg",
    "images/healthy/bananas.png",
    "images/healthy/black-currant.jpg",
    "images/healthy/cashews.png",
    "images/healthy/cucumber.jpg",
    "images/healthy/garlic.jpg",
    "images/healthy/lettuce.jpg",
    "images/healthy/pear.jpg",
    "images/healthy/pumpkin.png",
    "images/healthy/red-currant.jpg",
    "images/healthy/salad.jpg",
    ];

var junkProducts = [
    "images/junk/burger.jpg",
    "images/junk/chips.jpg",
    "images/junk/chocolate.png",
    "images/junk/coke.png",
    "images/junk/corndog.jpg",
    "images/junk/donut.jpg",
    "images/junk/donut2.jpg",
    "images/junk/fries.jpg",
    "images/junk/hotdog.jpg",
    "images/junk/ice-cream.jpg",
    "images/junk/lollipop.jpg",
    "images/junk/pizza.jpg",
    "images/junk/pizza2.png",
    "images/junk/pizza3.png",
    "images/junk/pizza4.jpg",
    "images/junk/sausage.jpg",
    "images/junk/soda.png",
    "images/junk/sprite.png"
    ];

const scores = [];
const minimumChangeTime = 2000;
const rectWidth = 1302;
const rectHeight = 700;
let msAfterBgColorChange = 0;
let clicked = false;
let minClickTime = 0;
let maxClickTime = 0;
let avgClickTime = 0;
let sumClickTime = 0;
let startRect;
let newRect;
let invisRect;
let startClick = false;
let level = 0;

let unlockedLevels = {
    level1: true,
    level2: false,
    level3: false
};

const loaded_levels = await loadGameData({gameId: 7});

if (loaded_levels != null) {
    unlockedLevels = loaded_levels;
}

console.log(unlockedLevels);

chooseLevel()

async function startGame() {
    let changeTime;
    if (level === 1) {
        changeTime = Math.random() * (10000 - minimumChangeTime) + minimumChangeTime;
    } else if (level === 2) {
        changeTime = Math.random() * (7000 - minimumChangeTime) + minimumChangeTime;
    } else if (level === 3) {
        changeTime = Math.random() * (4000 - minimumChangeTime) + minimumChangeTime;
    }
    clickSpace.style.fill = null;
    displayText.style.fill = "black";
    displayText.textContent = "Wait for the background to split, then choose the healthy product by clicking LMB!";

    setTimeout(async () => {
        msAfterBgColorChange = Date.now();
        let productsToShow = 0;

        if (level === 1) {
            productsToShow = 3;
        }

        if (level === 2) {
            productsToShow = 4;
        }

        if (level === 3) {
            productsToShow = 8;
        }

        const healthy_product = Math.floor(Math.random() * productsToShow);

        if (level === 1 || level === 2) {
            for (let i = 0; i < productsToShow; i++) {
                newRect = document.createElementNS("http://www.w3.org/2000/svg", "rect");
                const newImage = document.createElementNS("http://www.w3.org/2000/svg", "image");
                newRect.setAttribute("x", i * (rectWidth / productsToShow));
                newRect.setAttribute("y", 0);
                newRect.setAttribute("width", rectWidth / productsToShow);
                newRect.setAttribute("height", rectHeight);
                newRect.setAttribute("stroke", "black");
                newRect.setAttribute("stroke-width", "2");
                newRect.setAttribute("fill", i % 2 === 0 ? "#c9bfb5" : "#F7ECE1");
    
                if (i === healthy_product) {
                    newImage.setAttribute("href", healthyProducts[Math.floor(Math.random() * healthyProducts.length)]);
                    newImage.setAttribute("x", i * (rectWidth / productsToShow) + 50);
                    newImage.setAttribute("y", 175);
                    newImage.setAttribute("width", 1050 / productsToShow);
                    newImage.setAttribute("height", 1050 / productsToShow);
                    newImage.setAttribute("stroke", "black");
                    newImage.setAttribute("stroke-width", "2");
                    newRect.classList.add("HEL");
                    newImage.classList.add("HEL");
                } else {
                    newImage.setAttribute("href", junkProducts[Math.floor(Math.random() * junkProducts.length)]);
                    newImage.setAttribute("x", level === 1 ? i * (rectWidth / productsToShow) + 50 : i * (rectWidth / productsToShow) + 33);
                    newImage.setAttribute("y", 175);
                    newImage.setAttribute("width", 1050 / productsToShow);
                    newImage.setAttribute("height", 1050 / productsToShow);
                    newImage.setAttribute("stroke", "black");
                    newImage.setAttribute("stroke-width", "2");
                    newRect.classList.add("JUN");
                    newImage.classList.add("JUN");
                }
    
                game.appendChild(newRect);
                game.appendChild(newImage);
            }
        }

        if (level === 3) {
            for (let i = 0; i < productsToShow; i++) {
                newRect = document.createElementNS("http://www.w3.org/2000/svg", "rect");
                const newImage = document.createElementNS("http://www.w3.org/2000/svg", "image");
                newRect.setAttribute("x", i <= 3 ? i * (rectWidth / 4) : (i - 4) * (rectWidth / 4));
                newRect.setAttribute("y", i <= 3 ? 0 : 350);
                newRect.setAttribute("width", rectWidth / 4);
                newRect.setAttribute("height", rectHeight / 2);
                newRect.setAttribute("stroke", "black");
                newRect.setAttribute("stroke-width", "2");
                newRect.setAttribute("fill", i % 2 === 0 ? "#c9bfb5" : "#F7ECE1");
        
                if (i === healthy_product) {
                    newImage.setAttribute("href", healthyProducts[Math.floor(Math.random() * healthyProducts.length)]);
                    newImage.setAttribute("x", i <= 3 ? i * (rectWidth / 4) + 33 : (i - 4) * (rectWidth / 4) + 33);
                    newImage.setAttribute("y", i <= 3 ? 50 : 400);
                    newImage.setAttribute("width", 1050 / 4);
                    newImage.setAttribute("height", 1050 / 4);
                    newImage.setAttribute("stroke", "black");
                    newImage.setAttribute("stroke-width", "2");
                    newRect.classList.add("HEL");
                    newImage.classList.add("HEL");
                } else {
                    newImage.setAttribute("href", junkProducts[Math.floor(Math.random() * junkProducts.length)]);
                    newImage.setAttribute("x", i <= 3 ? i * (rectWidth / 4) + 33 : (i - 4) * (rectWidth / 4) + 33);
                    newImage.setAttribute("y", i <= 3 ? 50 : 400);
                    newImage.setAttribute("width", 1050 / 4);
                    newImage.setAttribute("height", 1050 / 4);
                    newImage.setAttribute("stroke", "black");
                    newImage.setAttribute("stroke-width", "2");
                    newRect.classList.add("JUN");
                    newImage.classList.add("JUN");
                }
        
                game.appendChild(newRect);
                game.appendChild(newImage);
            }
        }

        invisRect = document.createElementNS("http://www.w3.org/2000/svg", "rect");
        invisRect.setAttribute("x", 0);
        invisRect.setAttribute("y", 0);
        invisRect.setAttribute("width", 1302);
        invisRect.setAttribute("height", 700);
        invisRect.setAttribute("fill", "transparent");
        game.appendChild(invisRect);

        clicked = true;

        invisRect.addEventListener("click", async (event) => {
            if (clicked) {
                const mouseX = event.clientX;
                const mouseY = event.clientY;
                if (level === 1) {
                    if (healthy_product === 0) {
                        if (mouseX >= 0 && mouseX < rectWidth / 3 && mouseY >= 0 && mouseY <= rectHeight) {
                            clicked = false;
                            doWhenClickedCorrectly();
                        }
                        else {
                            clicked = false;
                            doWhenClickedUncorrectly();
                        }   
                    }
                    else if (healthy_product === 1) {
                        if (mouseX >= rectWidth / 3 && mouseX < (rectWidth / 3) * 2 && mouseY >= 0 && mouseY <= rectHeight) {
                            clicked = false;
                            doWhenClickedCorrectly();
                        }
                        else {
                            clicked = false;
                            doWhenClickedUncorrectly();
                        }   
                    }
                    else if (healthy_product === 2) {
                        if (mouseX >= (rectWidth / 3) * 2 && mouseX < rectWidth && mouseY >= 0 && mouseY <= rectHeight) {
                            clicked = false;
                            doWhenClickedCorrectly();
                        }
                        else {
                            clicked = false;
                            doWhenClickedUncorrectly();
                        }   
                    }
                }

                else if (level === 2) {
                    if (healthy_product === 0) {
                        if (mouseX >= 0 && mouseX < rectWidth / 4 && mouseY >= 0 && mouseY <= rectHeight) {
                            clicked = false;
                            doWhenClickedCorrectly();
                        }
                        else {
                            clicked = false;
                            doWhenClickedUncorrectly();
                        }   
                    }
                    else if (healthy_product === 1) {
                        if (mouseX >= rectWidth / 4 && mouseX < (rectWidth / 4) * 2 && mouseY >= 0 && mouseY <= rectHeight) {
                            clicked = false;
                            doWhenClickedCorrectly();
                        }
                        else {
                            clicked = false;
                            doWhenClickedUncorrectly();
                        }   
                    }
                    else if (healthy_product === 2) {
                        if (mouseX >= (rectWidth / 4) * 2 && mouseX < (rectWidth / 4) * 3 && mouseY >= 0 && mouseY <= rectHeight) {
                            clicked = false;
                            doWhenClickedCorrectly();
                        }
                        else {
                            clicked = false;
                            doWhenClickedUncorrectly();
                        }   
                    }
                    else if (healthy_product === 3) {
                        if (mouseX >= (rectWidth / 4) * 3 && mouseX < rectWidth && mouseY >= 0 && mouseY <= rectHeight) {
                            clicked = false;
                            doWhenClickedCorrectly();
                        }
                        else {
                            clicked = false;
                            doWhenClickedUncorrectly();
                        }   
                    }
                }

                else if (level === 3) {
                    if (healthy_product === 0) {
                        if (mouseX >= 0 && mouseX < rectWidth / 4 && mouseY >= 0 && mouseY <= rectHeight / 2) {
                            clicked = false;
                            doWhenClickedCorrectly();
                        }
                        else {
                            clicked = false;
                            doWhenClickedUncorrectly();
                        }
                    }
                    else if (healthy_product === 1) {
                        if (mouseX >= rectWidth / 4 && mouseX < (rectWidth / 4) * 2 && mouseY >= 0 && mouseY <= rectHeight / 2) {
                            clicked = false;
                            doWhenClickedCorrectly();
                        }
                        else {
                            clicked = false;
                            doWhenClickedUncorrectly();
                        }
                    }
                    else if (healthy_product === 2) {
                        if (mouseX >= (rectWidth / 4) * 2 && mouseX < (rectWidth / 4) * 3 && mouseY >= 0 && mouseY <= rectHeight / 2) {
                            clicked = false;
                            doWhenClickedCorrectly();
                        }
                        else {
                            clicked = false;
                            doWhenClickedUncorrectly();
                        }
                    }
                    else if (healthy_product === 3) {
                        if (mouseX >= (rectWidth / 4) * 3 && mouseX < rectWidth && mouseY >= 0 && mouseY <= rectHeight / 2) {
                            clicked = false;
                            doWhenClickedCorrectly();
                        }
                        else {
                            clicked = false;
                            doWhenClickedUncorrectly();
                        }
                    }
                    else if (healthy_product === 4) {
                        if (mouseX >= 0 && mouseX < rectWidth / 4 && mouseY > rectHeight / 2 && mouseY <= rectHeight) {
                            clicked = false;
                            doWhenClickedCorrectly();
                        }
                        else {
                            clicked = false;
                            doWhenClickedUncorrectly();
                        }
                    }
                    else if (healthy_product === 5) {
                        if (mouseX >= rectWidth / 4 && mouseX < (rectWidth / 4) * 2 && mouseY > rectHeight / 2 && mouseY <= rectHeight) {
                            clicked = false;
                            doWhenClickedCorrectly();
                        }
                        else {
                            clicked = false;
                            doWhenClickedUncorrectly();
                        }
                    }
                    else if (healthy_product === 6) {
                        if (mouseX >= (rectWidth / 4) * 2 && mouseX < (rectWidth / 4) * 3 && mouseY > rectHeight / 2 && mouseY <= rectHeight) {
                            clicked = false;
                            doWhenClickedCorrectly();
                        }
                        else {
                            clicked = false;
                            doWhenClickedUncorrectly();
                        }
                    }
                    else if (healthy_product === 7) {
                        if (mouseX >= (rectWidth / 4) * 3 && mouseX < rectWidth && mouseY > rectHeight / 2 && mouseY <= rectHeight) {
                            clicked = false;
                            doWhenClickedCorrectly();
                        }
                        else {
                            clicked = false;
                            doWhenClickedUncorrectly();
                        }
                    }
                } 

            }
            else {
                await startGame();
            }
        });

        
    }, changeTime)

    if (scores.length >= 7) {
        avgClickTime = Math.round((sumClickTime/7));
        alert(`Game over.\nBest score: ${minClickTime}.\nAverage score: ${avgClickTime}.\nWorst score: ${maxClickTime}.\nFinal score: ${sumClickTime}.\nClick OK to restart the game.`);
        clicked = false;
        await updateRanking({
            gameID: 7,
            score: sumClickTime
        });
        
        if (level === 1) {
            unlockedLevels.level2 = true;
        }

        if (level === 2) {
            unlockedLevels.level3 = true;
        }

        await saveGameData({
            gameId: 7,
            gameData: unlockedLevels
        });

        location.reload();
    }

}

function addScoreToScores(score) {
    scores.unshift(score);

    for (let i = 0; i < Math.min(scores.length, 7); i++) {
        const score = scores[i]
        scoreElements[i].textContent = `${score}`;
    }
    minClickTime = Math.max(...scores);
    maxClickTime = Math.min(...scores);
}

function doWhenClickedCorrectly() {
    const score = Date.now() - msAfterBgColorChange;
    let points = 100;
    if (score >= 0 && score <= 200) {
        points += (level === 1 ? 500 : level === 2 ? 1000 : 1500)
    }
    if (score >= 201 && score <= 400) {
        points += (level === 1 ? 450 : level === 2 ? 900 : 1350)
    }
    if (score >= 401 && score <= 600) {
        points += (level === 1 ? 400 : level === 2 ? 800 : 1200)
    }
    if (score >= 601 && score <= 800) {
        points += (level === 1 ? 350 : level === 2 ? 700 : 1050)
    }
    if (score >= 801 && score <= 1000) {
        points += (level === 1 ? 300 : level === 2 ? 600 : 900)
    }
    if (score >= 1001 && score <= 1200) {
        points += (level === 1 ? 250 : level === 2 ? 500 : 750)
    }
    if (score >= 1201 && score <= 1400) {
        points += (level === 1 ? 200 : level === 2 ? 400 : 600)
    }
    if (score >= 1401 && score <= 1600) {
        points += (level === 1 ? 150 : level === 2 ? 300 : 450)
    }
    if (score >= 1601 && score <= 1800) {
        points += (level === 1 ? 100 : level === 2 ? 200 : 300)
    }
    if (score >= 1801 && score <= 2000) {
        points += (level === 1 ? 50 : level === 2 ? 100 : 150)
    }
    else {
        points += 0;
    }
    sumClickTime += points;
    clicked = false;
    invisRect.style.fill = "#33A574";
    addScoreToScores(points);
    removeElementsByClassName("HEL");
    removeElementsByClassName("JUN");
    game.removeChild(invisRect);
    clickSpace.style.fill = "#33A574";
    displayText.style.fill = "white";
    if (scores.length >= 7) {
        displayText.textContent = `You clicked correct image! Your click time was ${score} ms. You scored ${points}. Click me to end the game.`;
    }
    else {
        displayText.textContent = `You clicked correct image! Your click time was ${score} ms. You scored ${points}. Click me to play again!`;
    }
}

function doWhenClickedUncorrectly() {
    const score = Date.now() - msAfterBgColorChange;
    let points = 0;
    sumClickTime += points;
    clicked = false;
    invisRect.style.fill = "#D80B0B";
    addScoreToScores(points);
    removeElementsByClassName("HEL");
    removeElementsByClassName("JUN");
    game.removeChild(invisRect);
    clickSpace.style.fill = "#D80B0B";
    displayText.style.fill = "white";
    if (scores.length >= 7) {
        displayText.textContent = `You clicked wrong image (no points). Your click time was ${score} ms. Click me to end the game.`;
    }
    else {
        displayText.textContent = `You clicked wrong image (no points). Your click time was ${score} ms. Click me to play again!`;
    }
}

function removeElementsByClassName(className) {
    const elements = document.querySelectorAll(`.${className}`);
    elements.forEach(element => element.parentNode.removeChild(element));
}


clickSpace.addEventListener("click", (event) => {
    if (clicked) {
        clicked = false;
    } else {
        startGame();
    }
})

function chooseLevel() {

    for (let i = 0; i < 3; i++) {
        const newRect = document.createElementNS("http://www.w3.org/2000/svg", "rect");
        const newText = document.createElementNS("http://www.w3.org/2000/svg", "text");
        newRect.setAttribute("x", 0);
        newRect.setAttribute("y", (rectHeight / 3) * i);
        newRect.setAttribute("width", 1502);
        newRect.setAttribute("height", rectHeight / 3);
        newRect.setAttribute("stroke", "black");
        newRect.setAttribute("stroke-width", "2");
        newRect.setAttribute("fill", i % 2 === 0 ? "#c9bfb5" : "#F7ECE1");
        newText.setAttribute("x", 751);
        newText.setAttribute("y", (rectHeight / 3) * i + rectHeight / 6);
        newText.setAttribute("fill", (i === 0 && unlockedLevels.level1) || (i === 1 && unlockedLevels.level2) || (i === 2 && unlockedLevels.level3) ? "black" : "grey");
        newText.setAttribute("font-size", "50");
        newText.setAttribute("text-anchor", "middle");
        newText.setAttribute("font-family", "Monospace");
        newText.textContent = `Level ${i + 1}`;
        newRect.classList.add("START");
        newText.classList.add("START");
        game.appendChild(newRect);
        game.appendChild(newText);
    }

    
    startRect = document.createElementNS("http://www.w3.org/2000/svg", "rect");
    startRect.setAttribute("x", 0);
    startRect.setAttribute("y", 0);
    startRect.setAttribute("width", 1502);
    startRect.setAttribute("height", 700);
    startRect.setAttribute("fill", "transparent");
    startRect.classList.add("START");
    game.appendChild(startRect);

    startRect.addEventListener("click", async (event) => {
        // const unlockedLevel = await loadGameData({ gameId: 7 });
        // console.log(unlockedLevel);

        if (!startClick) {
            const mouseX = event.clientX;
            const mouseY = event.clientY;
            if (mouseX >= 0 && mouseX <= 1502 && mouseY >= 0 && mouseY <= rectHeight/3) {
                level = 1;
                startClick = true;
            }

            if (mouseX >= 0 && mouseX <= 1502 && mouseY > rectHeight/3 && mouseY <= (rectHeight/3) * 2) {
                level = 2;
                startClick = true;
            }

            if (mouseX >= 0 && mouseX <= 1502 && mouseY > (rectHeight/3) * 2 && mouseY <= rectHeight) {
                level = 3;
                startClick = true;
            }
        }

        if (level === 1 && !unlockedLevels.level1) {
            alert("You did not unlock this level yet!");
            location.reload();
        }

        if (level === 1 && unlockedLevels.level1) {
            startClick = false;
            removeElementsByClassName("START");
            startGame();
        }

        if (level === 2 && !unlockedLevels.level2) {
            alert("You have to complete level 1 first!");
            location.reload();
        }

        if (level === 2 && unlockedLevels.level2) {
            startClick = false;
            removeElementsByClassName("START");
            startGame();
        }

        if (level === 3 && !unlockedLevels.level3) {
            alert("You have to complete levels 1 and 2 first!");
            location.reload();
        }

        if (level === 3 && unlockedLevels.level3) {
            startClick = false;
            removeElementsByClassName("START");
            startGame();
        }

    });
}
