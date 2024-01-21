import {updateRanking} from "greengame-api-client";

let maze1 = [
    `A=-=-]=-=-=-D                             `, //0
    `|_...|.....0|                             `, //1
    `d...~a..#...|                             `, //2
    `|....|...v..|                             `, //3
    `|....^.#.|..|                             `, //4
    `a.......A[D.f                             `, //5
    `}=-D.v..|.|.|                             `, //6
    `|.~b.|..|.|.g                             `, //7
    `b..C=B.~c.|.h                             `, //8
    `c.......|.e/e                             `, //9
    `C=-=-=-=[-[=B                             `, //10
    `LEVEL 1 OF 3                              `,
    `                                          `,
    `                                          `,
    `                                          `,
    `                                          `,
];

let maze2 = [
    `A=-]]=-=-=-=-=-=-=-]=-=-]=-D              `, //0
    `|_.||..............|....g..|              `, //1
    `|..||...........v..f....|..|              `, //2
    `|..CB.....A=<...|..|..#AB..|              `, //3
    `|......A=-{..AD.C=-B...^...|              `, //4
    `}=-D...|..b.#||............|              `, //5
    `|..|..AB..|..c^.A=-=-=-=-=-{              `, //6
    `}=-B..|...}=-[=-B..........|              `, //7
    `|.....^...|........A=-]=-D.|              `, //8
    `b.....#...|...AD...|..d..|.|              `, //9
    `}=-D......e...^|...|..|..|.e              `, //10
    `c..a..v...|....|...|.AB..|.f              `, //11
    `d..C=-B...C=-=-B...^.C=-Dh.g              `, //12
    `a......................0C{.h              `, //13
    `C=-=-=-=-=-=-=-=-=-=-=-=-[-B              `, //14
    `LEVEL 2 OF 3`,
];

let maze3 = [
    `A=-=-=-=-=-=-=-]=-=-=-=-=-=-=-=-=-=-=-=-D`, //0
    `|_........#....a.v..................A=-D|`, //1
    `|..A=-]=D.....AB.|.....A=-D...#..A<>B..||`, //2
    `|.AB..e.|#....|..|..A=-B..|.#....|.....||`, //3
    `|.|..AB.C=-D..|..|..bA=-=D|.A=-=<|#....|b`, //4
    `|.|.A{.....|..|.0|..}B...c|.|....C=-D..|c`, //5
    `|.^.|C=-=D.|..C=-B..|..A=[B.|....A=-B.AB|`, //6
    `|...|....|.|.....#..|..|....|....|....d.d`, //7
    `|...|.AD.|.|........|.#|....}=-=<|....CD|`, //8
    `|...^.||.|.C=-=-=]=-{..C=-=-B....C=-D..||`, //9
    `a.....||.^....A=-{..|...............CD.|e`, //10
    `b.v..v|C=-=-Dvf..|..C=-=-=-]=-]=-D.A]B.|f`, //11
    `c.C=-[B..#..C[B..|.........C=-B..|.^g..||`, //12
    `d................h..#A=-D........^..|..gh`, //13
    `C=-=-=-=-=-=-=-=-[=-=[=-[=-=-=-=-=-=[=-[B`, //14
    `LEVEL 3 OF 3`,
];



//global variables
let currentLevel = maze1;
let body = document.querySelector('body');
let divTable = document.getElementById('cover');
let tableEl = document.querySelector('table');
let good = 1;
let lastLevel = false;
let temp_x = 0;
let temp_y = 0;
let out = false;
let v1, v2, v3, v4, v5, v6, v7, v8 = false;
const buttonPlay = document.querySelector('#one');
const buttonControls = document.querySelector('#two');



//if button clicked it loads this function for info on keys
let info = () => {
    let b1 = document.querySelector('#one')
    b1.textContent = 'Use WASD to move. Press me to play';
}; //end of info function
//if button clicked it loads the game
let loadPage = () => {
    let timerSeconds = 0;
    const timeElement = document.getElementById('timer');

    const updateTimer = () => {
        const minutes = Math.floor(timerSeconds / 60);
        const seconds = timerSeconds % 60;
        const formattedMinutes = minutes < 10 ? `0${minutes}` : minutes;
        const formattedSeconds = seconds < 10 ? `0${seconds}` : seconds;

        timeElement.textContent = `${formattedMinutes}:${formattedSeconds}`;
        timerSeconds++;
    };

    const timerInterval = setInterval(updateTimer, 1000);
    updateTimer();
    let outOfMenu = () => {
        let b0 = document.querySelector('#txt');
        let b1 = document.querySelector('#one');
        let b2 = document.querySelector('#two');
        b0.style.display = 'none';
        b1.style.display = 'none';
        b2.style.display = 'none';
        body.style.flexDirection = 'row';
        body.style.justifyContent = 'flex-start';
        body.style.alignItems = 'flex-start';
    };
    outOfMenu();

    const end = (parameter) => {
        //elements to display when it is end of the game
        let endGameBackground = document.createElement('section');
        let endGameText1 = document.createElement('p');
        let endGameHeaderText = document.createElement('h1');
        let endGameButtonText = document.createElement('button');
        clearTable(tableEl);
        player.style.display = 'none';
        if(parameter === 1) {
            endGameHeaderText.textContent = 'GAME OVER';
            endGameText1.textContent = 'Press the Button below to restart.';
        } else if(parameter === 2) {
            endGameHeaderText.innerHTML = 'YOU WON !<br>Your time is ' + timeElement.textContent.toString() + '<br> Points: ' + calculatePoints(timerSeconds);
            endGameText1.textContent = 'Press the Button below to play again.';
            updateData(timerSeconds);
            clearInterval(timerInterval);
        }
        endGameButtonText.textContent = 'Restart';
        endGameButtonText.setAttribute('onclick', 'window.location.reload();');
        endGameButtonText.setAttribute('type', 'button');
        body.appendChild(endGameBackground);
        endGameBackground.appendChild(endGameHeaderText);
        endGameBackground.appendChild(endGameText1);
        endGameBackground.appendChild(endGameButtonText);
        body.style.justifyContent = 'center';
    }

    const clearTable = (tableEl) => {
        while (tableEl.firstChild) {
            tableEl.removeChild(tableEl.firstChild);
        }
    };

    const calculatePoints = (timerSeconds) => {
        let points = 2000;
        if(timerSeconds < 60) {
            points = 2000;
        } else if (timerSeconds >= 500) {
            points = 500;
        } else {
            points = points - timerSeconds + 60;
        }
        return points;
    }

    const updateData = async (timerSeconds) => {
        const score = calculatePoints(timerSeconds);
        const data = {
            gameID: 2,
            score: score
        };

        await updateRanking(data);
    }

    let player = document.createElement('div');
    const drawMaze = (maze, playerPosX, playerPosY) => {
        //creating a function to draw maze
        clearTable(tableEl);
        divTable.appendChild(player);
        //creating player
        player.style.left = playerPosX;
        player.style.top = playerPosY;
        player.setAttribute('id', 'player');
        player.textContent = '@';
        for (let i = 0; i < currentLevel.length; i++) {

            let rowElement = document.createElement('tr');

            tableEl.appendChild(rowElement);

            for (let x = 0; x < currentLevel[i].length; x++) {
                let tdElement = document.createElement('td');
                rowElement.appendChild(tdElement)

                tdElement.innerHTML = maze[i].charAt(x);

                //conditionals below if the char is a specific character
                switch (maze[i].charAt(x)) {
                    case '#':
                        tdElement.setAttribute('class', 'hole');
                        break;
                    case '.':
                        tdElement.setAttribute('class', 'freespace');
                        break;
                    case '_':
                        tdElement.setAttribute('class', 'start');
                        break;
                    case '0':
                        if(out) {
                            tdElement.setAttribute('class', 'out_opened');
                        } else {
                            tdElement.setAttribute('class', 'out_closed');
                        }
                        break;
                    case '=':
                        tdElement.setAttribute('class', 'pipe_across_l');
                        break;
                    case '-':
                        tdElement.setAttribute('class', 'pipe_across_r');
                        break;
                    case '|':
                        tdElement.setAttribute('class', 'pipe_vertical');
                        break;
                     case '}':
                        tdElement.setAttribute('class', 'pipe_u_r_d');
                         break;
                    case '{':
                        tdElement.setAttribute('class', 'pipe_l_u_d');
                        break;
                    case ']':
                        tdElement.setAttribute('class', 'pipe_l_d_r');
                        break;
                    case '[':
                        tdElement.setAttribute('class', 'pipe_l_u_r');
                        break;
                    case 'q':
                        tdElement.setAttribute('class', 'puddle_u');
                        break;
                    case 'x':
                        tdElement.setAttribute('class', 'puddle_d');
                        break;
                    case 'y':
                        tdElement.setAttribute('class', 'puddle_r');
                        break;
                    case 'z':
                        tdElement.setAttribute('class', 'puddle_l');
                        break;
                    case '>':
                        tdElement.setAttribute('class', 'spill_right');
                        break;
                    case '<':
                        tdElement.setAttribute('class', 'spill_left');
                        break;
                    case '^':
                        tdElement.setAttribute('class', 'spill_up');
                        break;
                    case 'v':
                        tdElement.setAttribute('class', 'spill_down');
                        break;
                    case '(':
                        tdElement.setAttribute('class', 'valve_l_closed');
                        break;
                    case ')':
                        tdElement.setAttribute('class', 'valve_r_closed');
                        break;
                    case 'a':
                        tdElement.setAttribute('class', 'valve_l_opened1');
                        break;
                    case 'b':
                        tdElement.setAttribute('class', 'valve_l_opened2');
                        break;
                    case 'c':
                        tdElement.setAttribute('class', 'valve_l_opened3');
                        break;
                    case 'd':
                        tdElement.setAttribute('class', 'valve_l_opened4');
                        break;
                    case 'e':
                        tdElement.setAttribute('class', 'valve_r_opened1');
                        break;
                    case 'f':
                        tdElement.setAttribute('class', 'valve_r_opened2');
                        break;
                    case 'g':
                        tdElement.setAttribute('class', 'valve_r_opened3');
                        break;
                    case 'h':
                        tdElement.setAttribute('class', 'valve_r_opened4');
                        break;
                    case 'A':
                        tdElement.setAttribute('class', 'pipe_d_r');
                        break;
                    case 'B':
                        tdElement.setAttribute('class', 'pipe_l_u');
                        break;
                    case 'C':
                        tdElement.setAttribute('class', 'pipe_u_r');
                        break;
                    case 'D':
                        tdElement.setAttribute('class', 'pipe_l_d');
                        break;
                    case '~':
                        tdElement.setAttribute('class', 'arrow_r');
                        break;
                    case '/':
                        tdElement.setAttribute('class', 'arrow_l');
                        break;
                    default:
                        break;
                }
            }
        }
    };
    drawMaze(currentLevel, '35px', '50px');

    window.addEventListener('keydown', event => {
        //player based on which key is press then the action will occur
        switch (event.key) {
            case 'w':
                player.style.top = parseInt(player.style.top) - 10 + 'px';
                break;
            case 'a':
                player.style.left = parseInt(player.style.left) - 10 + 'px';
                break;
            case 's':
                player.style.top = parseInt(player.style.top) + 10 + 'px';
                break;
            case 'd':
                player.style.left = parseInt(player.style.left) + 10 + 'px';
                break;
        }

        let pos = player.getBoundingClientRect();
        let holes = document.querySelectorAll('.hole');
        let barriers = document.querySelectorAll('.pipe_vertical, .pipe_across_l, .pipe_across_r, .pipe_u_r_d, .pipe_l_u_d, .pipe_l_d_r, .pipe_l_u_r, .pipe_d_r, .pipe_u_r, .pipe_l_u, .pipe_l_d, .valve_l_closed, .valve_r_closed, .valve_l_opened1, .valve_l_opened2, .valve_l_opened3, .valve_l_opened4, .valve_r_opened1, .valve_r_opened2, .valve_r_opened3, .valve_r_opened4');
        let valve_l_1 = document.querySelector('.valve_l_opened1').getBoundingClientRect();
        let valve_l_2 = document.querySelector('.valve_l_opened2').getBoundingClientRect();
        let valve_l_3 = document.querySelector('.valve_l_opened3').getBoundingClientRect();
        let valve_l_4 = document.querySelector('.valve_l_opened4').getBoundingClientRect();
        let valve_r_1 = document.querySelector('.valve_r_opened1').getBoundingClientRect();
        let valve_r_2 = document.querySelector('.valve_r_opened2').getBoundingClientRect();
        let valve_r_3 = document.querySelector('.valve_r_opened3').getBoundingClientRect();
        let valve_r_4 = document.querySelector('.valve_r_opened4').getBoundingClientRect();

        for (let hole of holes) {
            let lava = hole.getBoundingClientRect();
            // checks for wall and player collision
            if (out) {
                let out_opened = document.querySelector('.out_opened');
                let wins = out_opened.getBoundingClientRect();
                if (pos.x < wins.x + wins.width && pos.x + pos.width > wins.x && pos.y < wins.y + wins.height && pos.y + pos.height > wins.y) {
                    if (lastLevel) {
                        end(2);
                    } else if (currentLevel[11] === `LEVEL 1 OF 3                              `) {
                        out = false;
                        drawMaze(maze2, '35px', '50px');
                        currentLevel = maze2;
                    } else if (currentLevel[15] === `LEVEL 2 OF 3`) {
                        out = false;
                        drawMaze(maze3, '35px', '50px');
                        currentLevel = maze3;
                    } else if (currentLevel[15] === `LEVEL 3 OF 3`) {
                        out = false;
                    }
                    v1 = false;
                    v2 = false;
                    v3 = false;
                    v4 = false;
                    v5 = false;
                    v6 = false;
                    v7 = false;
                    v8 = false;
                }
            }
            if (pos.x < lava.x + lava.width && pos.x + pos.width > lava.x && pos.y < lava.y + lava.height && pos.y + pos.height > lava.y) {
                end(1);
            } else {
                for (let barrier1 of barriers) {
                    let barrier = barrier1.getBoundingClientRect();
                    if (pos.x < barrier.x + barrier.width && pos.x + pos.width > barrier.x && pos.y < barrier.y + barrier.height && pos.y + pos.height > barrier.y) {
                        if (good === 1) {
                            switch (event.key) {
                                case 'w':
                                    player.style.top = (barrier.y + barrier.height) + 'px';
                                    break;
                                case 'a':
                                    player.style.left = (barrier.x + barrier.width) + 'px';
                                    break;
                                case 's':
                                    player.style.top = (barrier.y - 20) + 'px';
                                    break;
                                case 'd':
                                    player.style.left = (barrier.x - 20) + 'px';
                                    break;
                            }
                        }
                    }
                }
                if (pos.x < valve_l_1.x + valve_l_1.width && pos.x + pos.width > valve_l_1.x && pos.y < valve_l_1.y + valve_l_1.height && pos.y + pos.height > valve_l_1.y) {
                    if (good === 1) {
                        switch (event.key) {
                            case 'd':
                                if(currentLevel === maze2) {
                                    maze2[11] = `c..(..x...|....|...|.AB..|.f              `;
                                } else if (currentLevel === maze3) {
                                    maze3[1] = `|_........#....(.x..................A=-D|`;
                                } else if (currentLevel === maze1) {
                                    maze1[2] = `d...~(..#...|                             `;
                                    maze1[4] = `|....q.#.|..|                             `;
                                }
                                drawMaze(currentLevel, (valve_l_1.x - 23) + 'px', (valve_l_1.y + 7) + 'px');
                                console.log('v1');
                                v1 = true;
                                break;
                        }
                    }
                } else if (pos.x < valve_l_2.x + valve_l_2.width && pos.x + pos.width > valve_l_2.x && pos.y < valve_l_2.y + valve_l_2.height && pos.y + pos.height > valve_l_2.y) {
                    if (good === 1) {
                        switch (event.key) {
                            case 'd':
                                if (currentLevel === maze2) {
                                    maze2[3] = `|..CB.....A=z...|..|..#AB..|              `;
                                    maze2[5] = `}=-D...|..(.#||............|              `;
                                    maze2[8] = `|.....q...|........A=-]=-D.|              `;
                                } else if (currentLevel === maze3) {
                                    maze3[4] = `|.|..AB.C=-D..|..|..(A=-=D|.A=-=<|#....|b`;
                                } else if (currentLevel === maze1) {
                                    maze1[7] = `|.~(.|..|.|.g                             `;
                                    maze1[6] = `}=-D.x..|.|.|                             `;
                                }
                                drawMaze(currentLevel, (valve_l_2.x - 23) + 'px', (valve_l_2.y + 7) + 'px');
                                console.log('v2');
                                v2 = true;
                                break;
                        }
                    }
                } else if (pos.x < valve_l_4.x + valve_l_4.width && pos.x + pos.width > valve_l_4.x && pos.y < valve_l_4.y + valve_l_4.height && pos.y + pos.height > valve_l_4.y) {
                    if (good === 1) {
                        switch (event.key) {
                            case 'd':
                                if (currentLevel === maze2) {
                                    maze2[9] = `b.....#...|...AD...|..(..|.|              `;
                                } else if (currentLevel === maze3) {
                                    maze3[7] = `|...|....|.|.....#..|..|....|....|....(.d`;
                                    maze3[2] = `|..A=-]=D.....AB.|.....A=-D...#..A<yB..||`;
                                }
                                drawMaze(currentLevel, (valve_l_4.x - 23) + 'px', (valve_l_4.y + 7) + 'px');
                                v4 = true;
                                temp_x = valve_l_4.x - 23;
                                temp_y = valve_l_4.y + 7;
                                console.log('v4');
                                break;
                        }
                    }
                } else if (pos.x < valve_l_3.x + valve_l_3.width && pos.x + pos.width > valve_l_3.x && pos.y < valve_l_3.y + valve_l_3.height && pos.y + pos.height > valve_l_3.y) {
                    if (good === 1) {
                        switch (event.key) {
                            case 'd':
                                if (currentLevel === maze2) {
                                    maze2[6] = `|..|..AB..|..(q.A=-=-=-=-=-{              `;
                                } else if (currentLevel === maze3) {
                                    maze3[5] = `|.|.A{.....|..|.0|..}B...(|.|....C=-D..|c`;
                                } else if (currentLevel === maze1) {
                                    maze1[8] = `b..C=B.~(.|.h                             `;
                                }
                                drawMaze(currentLevel, (valve_l_3.x - 23) + 'px', (valve_l_3.y + 7) + 'px');
                                console.log('v3');
                                v3 = true;
                                temp_x = valve_l_3.x - 23;
                                temp_y = valve_l_3.y + 7;
                                break;
                        }
                    }
                } else if (pos.x < valve_r_1.x + valve_r_1.width && pos.x + pos.width > valve_r_1.x && pos.y < valve_r_1.y + valve_r_1.height && pos.y + pos.height > valve_r_1.y) {
                    if (good === 1) {
                        switch (event.key) {
                            case 'a':
                                if (currentLevel === maze2) {
                                    maze2[10] = `}=-D......)...q|...|..|..|.e              `;
                                } else if (currentLevel === maze3) {
                                    maze3[3] = `|.AB..).|#....|..|..A=-B..|.#....|.....||`;
                                    maze3[9] = `|...q.||.|.C=-=-=]=-{..C=-=-B....C=-D..||`;
                                    maze3[10] = `a.....||.q....A=-{..|...............CD.|e`;
                                } else if (currentLevel === maze1) {
                                    maze1[9] = `c.......|.)/e                             `;
                                    v4 = true;
                                    v6 = true;
                                    v7 = true;
                                    v8 = true;
                                }
                                drawMaze(currentLevel, (valve_r_1.x + 25) + 'px', (valve_r_1.y + 7) + 'px');
                                v5 = true;
                                temp_x = valve_r_1.x + 25;
                                temp_y = valve_r_1.y + 7;
                                console.log('v5');
                                break;
                        }
                    }
                } else if (pos.x < valve_r_2.x + valve_r_2.width && pos.x + pos.width > valve_r_2.x && pos.y < valve_r_2.y + valve_r_2.height && pos.y + pos.height > valve_r_2.y) {
                    if (good === 1) {
                        switch (event.key) {
                            case 'a':
                                if (currentLevel === maze2) {
                                    maze2[2] = `|..||...........x..)....|..|              `;
                                } else if (currentLevel === maze3) {
                                    maze3[11] = `c.x..x|C=-=-Dx)..|..C=-=-=-]=-]=-D.A]B.|f`;
                                }
                                drawMaze(currentLevel, (valve_r_2.x + 25) + 'px', (valve_r_2.y + 7) + 'px');
                                v6 = true;
                                console.log('v6');
                                break;
                        }
                    }
                } else if (pos.x < valve_r_3.x + valve_r_3.width && pos.x + pos.width > valve_r_3.x && pos.y < valve_r_3.y + valve_r_3.height && pos.y + pos.height > valve_r_3.y) {
                    if (good === 1) {
                        switch (event.key) {
                            case 'a':
                                if (currentLevel === maze2) {
                                    maze2[1] = `|_.||..............|....)..|              `;
                                    maze2[4] = `|......A=-{..AD.C=-B...q...|              `;
                                } else if (currentLevel === maze3) {
                                    maze3[12] = `d.C=-[B..#..C[B..|.........C=-B..|.q)..||`;
                                    maze3[2] = `|..A=-]=D.....AB.|.....A=-D...#..AzyB..||`;
                                    v4 = true;
                                }
                                drawMaze(currentLevel, (valve_r_3.x + 25) + 'px', (valve_r_3.y + 7) + 'px');
                                console.log('v7');
                                v7 = true;
                                break;
                        }
                    }
                } else if (pos.x < valve_r_4.x + valve_r_4.width && pos.x + pos.width > valve_r_4.x && pos.y < valve_r_4.y + valve_r_4.height && pos.y + pos.height > valve_r_4.y) {
                    if (good === 1) {
                        switch (event.key) {
                            case 'a':
                                if (currentLevel === maze2) {
                                    maze2[12] = `d..C=-B...C=-=-B...^.C=-D).g              `;
                                } else if (currentLevel === maze3) {
                                    maze3[13] = `a................)..#A=-D........q..|..gh`;
                                    maze3[6] = `|.q.|C=-=D.|..C=-B..|..A=[B.|....A=-B.AB|`;
                                    maze3[9] = `|...q.||.|.C=-=-=]=-{..C=-=-B....C=-D..||`;
                                    maze3[10] = `a.....||.q....A=-{..|...............CD.|e`;
                                    maze3[8] = `|...|.AD.|.|........|.#|....}=-=z|....CD|`;
                                    if(maze3[11] === `b.v..v|C=-=-Dvf..|..C=-=-=-]=-]=-D.A]B.|f`) {
                                        maze3[11] = `c.x..x|C=-=-Dxf..|..C=-=-=-]=-]=-D.A]B.|f`;
                                    }
                                    if(maze3[4] === `|.|..AB.C=-D..|..|..bA=-=D|.A=-=<|#....|b` || maze3[4] === `|.|..AB.C=-D..|..|..(A=-=D|.A=-=<|#....|b`) {
                                        maze3[4] = `|.|..AB.C=-D..|..|..bA=-=D|.A=-=z|#....|b`;
                                    }
                                    v2 = true;
                                    v3 = true;
                                    v5 = true;
                                    v6 = true;
                                }
                                drawMaze(currentLevel, (valve_r_4.x + 25) + 'px', (valve_r_4.y + 7) + 'px');
                                v8 = true;
                                temp_x = valve_r_4.x + 25;
                                temp_y = valve_r_4.y + 7;
                                console.log('v8');
                                break;
                        }
                    }
                }
            }
            if (maze2[12] === `d..C=-B...C=-=-B...^.C=-D).g              ` && maze2[9] === `b.....#...|...AD...|..(..|.|              `) {
                if (good === 1) {
                    maze2[12] = `d..C=-B...C=-=-B...q.C=-D).g              `;
                    drawMaze(maze2, (valve_r_4.x + 25) + 'px', (valve_r_4.y + 7) + 'px');
                }
            }
            if (maze3[4] === `|.|..AB.C=-D..|..|..(A=-=D|.A=-=<|#....|b` && maze3[5] === `|.|.A{.....|..|.0|..}B...(|.|....C=-D..|c`) {
                if (good === 1) {
                    maze3[8] = `|...|.AD.|.|........|.#|....}=-=z|....CD|`;
                    maze3[4] = `|.|..AB.C=-D..|..|..(A=-=D|.A=-=z|#....|b`;
                    drawMaze(maze3, (valve_r_4.x + 25) + 'px', (valve_r_4.y + 7) + 'px');
                }
            }
            if (maze1[8] === `b..C=B.~(.|.h                             ` && maze1[9] === `c.......|.)/e                             `) {
                if (good === 1) {
                    maze1[3] = `|....|...x..|                             `;
                   // drawMaze(currentLevel, '35px', '50px');
                }
            }
            if (pos.x === 0) {
                end(1);
            }
            if (v1 === true && v2 === true && v3 === true && v4 === true && v5 === true && v6 === true && v7 === true && v8 === true) {
                if (good === 1) {
                    out = true;
                    drawMaze(currentLevel, temp_x + 'px', temp_y + 'px');
                    if (currentLevel === maze3) {
                        lastLevel = true;
                    }
                    v1 = false;
                    v2 = false;
                    v3 = false;
                    v4 = false;
                    v5 = false;
                    v6 = false;
                    v7 = false;
                    v8 = false;
                }
            }
        }
    });
};

buttonPlay.addEventListener("click", loadPage)
buttonControls.addEventListener("click", info)
