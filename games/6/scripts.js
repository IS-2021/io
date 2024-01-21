"use strict"

import {updateRanking} from "greengame-api-client";

let rubbishType = ['Paper', 'Plastic', 'Glass','FruitLeftovers','GreasyPaper','UsedMedicalEquipment','SprayCans','PetFeces','Electronics']

const btn_u_l = document.querySelector("#btn-up-left")
const btn_u = document.querySelector("#btn-up")
const btn_u_r = document.querySelector("#btn-up-right")
const btn_l = document.querySelector("#btn-left")
const btn_r = document.querySelector("#btn-right")
const btn_d_l = document.querySelector("#btn-down-left")
const btn_d = document.querySelector("#btn-down")
const btn_d_r = document.querySelector("#btn-down-right")

const btn_drop1 = document.querySelector("#btn-drop1")
const btn_drop2 = document.querySelector("#btn-drop2")
const btn_drop3 = document.querySelector("#btn-drop3")

const btn_nextTurn = document.querySelector("#btn-nt")
const btn_end = document.querySelector("#btn-end")


function randomInt(min, max) {
    return Math.floor(Math.random()*(max - min)) + min
}

function getRubbishColor(rubbishType) {
    switch (rubbishType) {
        case 'Paper':
            return "#001d6e"
        case 'Plastic':
            return "#686e00"
        case 'Glass':
            return "#00730b"
        case 'FruitLeftovers':
            return "#4d3311"
        case 'GreasyPaper':
        case 'UsedMedicalEquipment':
        case 'SprayCans':
        case 'PetFeces':
            return "#1a1a1a"
        case 'Electronics':
            return "#4f004e"
    }
}

class Rubbish {
    #rubbishType
    #locationX
    #locationY
    #color
    constructor (type, x, y) {
        this.#rubbishType = type
        this.#locationX = x
        this.#locationY = y
        this.#color = getRubbishColor(this.#rubbishType)
    }
    getRubbishType() {
        return this.#rubbishType
    }
    getLocationX() {
        return this.#locationX
    }
    getLocationY() {
        return this.#locationY
    }
    getColor() {
        return this.#color
    }
}

class RubbishInBag {
    #rubbishType
    #color
    constructor (type) {
        this.#rubbishType = type
        this.#color = getRubbishColor(this.#rubbishType)
    }
    getRubbishType() {
        return this.#rubbishType
    }
    getColor(){
        return this.#color
    }
}

class Bin {
    #rubbishDefault = []
    #locationX
    #locationY
    #game
    #color
    constructor (x, y, types, game, color) {
        this.#locationX = x
        this.#locationY = y
        this.#clearList()
        for (let i=0; i<types.length;i++) {
            this.#rubbishDefault.push(types[i])
        }
        this.#game = game
        this.#color = color
    }
    #clearList() {
        this.#rubbishDefault = []
    }
    getLocationX() {
        return this.#locationX
    }
    getLocationY() {
        return this.#locationY
    }
    getBinColor() {
        return this.#color
    }
    checkDropped(rubbish) {
        if (rubbish.getLocationX() === this.#locationX &&
            rubbish.getLocationY() === this.#locationY ) {
            if (this.#rubbishDefault.includes(rubbish.getRubbishType())) {
                this.#game.addPoints(10)                                    // <- here change scaling of points
            }
            else {
                this.#game.removePoints(5)                                 // <- here change scaling of points
            }
            return true
        }
        return false
    }
}

class Player {
    #locationX
    #locationY
    #directionX
    #directionY
    #movePoints
    #backpack = []
    #map
    constructor(map) {
        this.#map = map
        this.#clearList()
        this.resetMovePoints()
        this.#locationX = 25
        this.#locationY = 25
    }
    #clearList() {
        this.#backpack = []
    }
    getLocationX() {
        return this.#locationX
    }
    getLocationY() {
        return this.#locationY
    }
    getMovePoints(){
        return this.#movePoints
    }
    getBackpackData() {
        let answer = []
        for (let i=0; i<this.#backpack.length; i++) {
            let newObj = {
                x: i,
                color: this.#backpack[i].getColor()
            }
            answer.push(newObj)
        }
        return answer
    }

    resetMovePoints() {
        this.#movePoints = 300
    }

    zeroMovePoints() {
        let points = this.#movePoints
        this.#movePoints = 0
        return points
    }
    move(direction) {
        switch(direction){
            case 'Up-Left':
                this.#directionX = -1
                this.#directionY = -1
                break
            case 'Left':
                this.#directionX = -1
                this.#directionY = 0
                break
            case 'Down-Left':
                this.#directionX = -1
                this.#directionY = 1
                break
            case 'Up':
                this.#directionX = 0
                this.#directionY = -1
                break
            case 'Down':
                this.#directionX = 0
                this.#directionY = 1
                break
            case 'Up-Right':
                this.#directionX = 1
                this.#directionY = -1
                break
            case 'Right':
                this.#directionX = 1
                this.#directionY = 0
                break
            case 'Down-Right':
                this.#directionX = 1
                this.#directionY = 1
                break
        }
        if (this.#movePoints >= 1 &&
            ((this.#locationX + this.#directionX) >= 0) &&
            ((this.#locationY + this.#directionY) >= 0) &&
            ((this.#locationX + this.#directionX) < this.#map.getSizeX()) &&
            ((this.#locationY + this.#directionY) < this.#map.getSizeY())) {
            
            this.#movePoints -= 1
            this.#locationX += this.#directionX
            this.#locationY += this.#directionY
            if(this.#backpack.length < 3 && this.#map.findRubbishOnTheSameCoord(this.getLocationX(), this.getLocationY())) {
                let newObj = this.#map.removeRubbishByCoord(this.getLocationX(), this.getLocationY())
                this.pickupRubbish(newObj)
            }
            else {
            }
        }
    }
    pickupRubbish(rubbish) {
        this.#backpack.push(new RubbishInBag(rubbish.getRubbishType()))
    }
    dropRubbish(index) {
        if (this.#backpack[index]!==undefined) {
            if (this.#map.createRubbish(this.getLocationX(), this.getLocationY(), this.#backpack[index].getRubbishType())) {
                this.#backpack.splice(index,1)
            }
        }
    }
}


class Map {
    #rubbishList = []
    #binList = []
    #sizeX = 51
    #sizeY = 51
    #game
    #player
    constructor(game) {
        this.#clearTables()
        this.#game = game 
        this.#player = new Player(this)
        this.#initializeBins()
        this.#initializeRubbish()
    }
    getSizeX(){
        return this.#sizeX
    }
    getSizeY(){
        return this.#sizeY
    }
    getPositionInfo() {
        let answer = {
            bins: [],
            rubbish: [],
            player: null,
            backpack: []
        }
        for (let i=0; i<this.#binList.length; i++){
            let newBinObj = {
                x: this.#binList[i].getLocationX(),
                y: this.#binList[i].getLocationY(),
                color: this.#binList[i].getBinColor()
            }
            answer.bins.push(newBinObj)
        }
        for (let i=0; i<this.#rubbishList.length; i++){
            let newRubbishObj = {
                x: this.#rubbishList[i].getLocationX(),
                y: this.#rubbishList[i].getLocationY(),
                color: this.#rubbishList[i].getColor()
            }
            answer.rubbish.push(newRubbishObj)
        }

        answer.player = {
            x: this.#player.getLocationX(),
            y: this.#player.getLocationY()
        }
        answer.backpack = this.#player.getBackpackData()
        
        return answer
    }

    endGame(){
        return this.#player.zeroMovePoints()
    }

    getPlayerMovePoints(){
        return this.#player.getMovePoints()
    }

    movePlayer(direction) {
        this.#player.move(direction)
    }

    dropRubbishByPlayer(index) {
        this.#player.dropRubbish(index)
    }

    #clearTables() {
        this.#rubbishList = []
        this.#binList = []
    }
    #initializeBins() {
        let newObj
        newObj = new Bin(24,24,rubbishType.slice(0,1),this.#game, "#1b37f2")
        this.#binList.push(newObj)
        newObj = new Bin(24,25,rubbishType.slice(1,2),this.#game, "#d2ff1f")
        this.#binList.push(newObj)
        newObj = new Bin(25,24,rubbishType.slice(2,3),this.#game, "#0b4203")
        this.#binList.push(newObj)
        newObj = new Bin(25,26,rubbishType.slice(3,4),this.#game, "#593215")
        this.#binList.push(newObj)
        newObj = new Bin(26,25,rubbishType.slice(4,8),this.#game, "#000000")
        this.#binList.push(newObj)
        newObj = new Bin(26,26,rubbishType.slice(8),this.#game, "#ff00e1")
        this.#binList.push(newObj)
    }
    findRubbishOnTheSameCoord(x,y) {
        for (let i=0; i<this.#rubbishList.length; i++){
            if (this.#rubbishList[i].getLocationX() === x &&
                this.#rubbishList[i].getLocationY() === y) {
                return true
            }
        }
        return false
    }

    #findBinOnTheSameCoord(x,y) {
        for (let i=0; i<this.#binList.length; i++){
            if (this.#binList[i].getLocationX() === x &&
                this.#binList[i].getLocationY() === y) {
                return true
            }
        }
        return false
    }
    
    #findRubbishOrBinOrPlayerOnTheSameCoords(x, y) {
        if (this.findRubbishOnTheSameCoord(x, y)) {
            return true
        }
        if (this.#findBinOnTheSameCoord(x, y)) {
            return true
        }
        return x === this.#player.getLocationX() && y === this.#player.getLocationY();

    }

    #initializeRubbish() {
        let newObj, newX, newY
        for (let i=0; i<800; i++) {
            newX = randomInt(0, this.#sizeX)
            newY = randomInt(0, this.#sizeY)
            while (this.#findRubbishOrBinOrPlayerOnTheSameCoords(newX, newY)) {
                newX = randomInt(0, this.#sizeX)
                newY = randomInt(0, this.#sizeY)
            }
            newObj = new Rubbish(rubbishType[randomInt(0, 9)], newX, newY)
            this.#rubbishList.push(newObj)
            
        }
    }
    createRubbish(x,y,rubbishType) {
        if (this.findRubbishOnTheSameCoord(x, y)) {
            return false
        }
        let newObj = new Rubbish(rubbishType, x, y)
        let dumped = false
        for (let i=0; i<this.#binList.length; i++) {
            if (this.#binList[i].checkDropped(newObj) ){
                dumped = true
                break
            }
        }
        if (!dumped) {
            this.#rubbishList.push(newObj)
            this.#game.removePoints(2)
        }
        return true
    }

    removeRubbishByCoord(x,y) {
        for (let i=0; i<this.#rubbishList.length; i++){
            if (this.#rubbishList[i].getLocationX() === x &&
                this.#rubbishList[i].getLocationY() === y) {
                let rubbish = this.#rubbishList[i]
                this.#rubbishList.splice(i,1)
                return rubbish
            }
        }
    }

    resetPlayerMovePoints(){
        this.#player.resetMovePoints()
    }
}

class Game {
    #score = 0
    #turnsLeft = 40
    #map
    #board
    #context
    #blockSize
    #backpack
    #contextBackpack
    #turnsField
    #movePointsField
    #scoreField
    constructor(board, backpack, turns, movePoints, scoreField) {
        this.#map = new Map(this)
        this.#blockSize = 10
        this.#board = board
        this.#board.height = this.#map.getSizeX() * this.#blockSize;
        this.#board.width = this.#map.getSizeY() * this.#blockSize;
        this.#context = board.getContext("2d"); 
        this.#backpack = backpack
        this.#backpack.height = 66;
        this.#backpack.width = 200;
        this.#contextBackpack = backpack.getContext("2d"); 
        this.#turnsField = turns
        this.#movePointsField = movePoints
        this.#scoreField = scoreField
        this.print()
        this.updateStats()
    }

    getTurnsLeft() {
        return this.#turnsLeft
    }

    getPoints() {
        return this.#score
    }
    addPoints(amount) {
        this.#score += amount
    }
    removePoints(amount) {
        this.#score -= amount
    }

    nextTurn() {
        if(this.#turnsLeft!==0){
            this.#turnsLeft -=1
            this.#map.resetPlayerMovePoints()
        }
        this.updateStats()
    }
    endGame() {
        let movepoints = this.#map.endGame()
        movepoints = Math.floor((this.#turnsLeft * 300 + movepoints) / 100)
        this.addPoints(movepoints)
        this.#turnsLeft = 0
        this.updateRanking()
        this.updateStats()
    }
    updateRanking() {
        updateRanking({gameID: 6,score: this.getPoints()})
    }

    getPositionInfo() {
        return this.#map.getPositionInfo()
    }
    
    getPlayerMovesLeft() {
        return this.#map.getPlayerMovePoints()
    }

    move(direction) {
        this.#map.movePlayer(direction)
        this.updateStats()
        this.print()
    }

    drop(index) {
        this.#map.dropRubbishByPlayer(index)
        this.print()
        this.updateStats()
    }

    print() {
        let data = this.getPositionInfo()

        this.#contextBackpack.fillStyle="#ffffff"
        this.#contextBackpack.fillRect(0, 0, this.#backpack.width, this.#backpack.height)

        for (let i=0; i<3; i++) {
            if (data.backpack[i]!==undefined) {
                this.#contextBackpack.fillStyle=data.backpack[i].color
                this.#contextBackpack.fillRect(i*66, 0, 66, this.#backpack.height)
            }
        }
        
        this.#context.fillStyle="#3c9627"
        this.#context.fillRect(0, 0, this.#board.width, this.#board.height)

        for (let i=0; i<data.bins.length; i++) {
            this.#context.fillStyle=data.bins[i].color
            this.#context.fillRect(data.bins[i].x*this.#blockSize, data.bins[i].y*this.#blockSize, this.#blockSize, this.#blockSize)
        }

        for (let i=0; i<data.rubbish.length; i++) {
            this.#context.fillStyle=data.rubbish[i].color
            this.#context.fillRect(data.rubbish[i].x*this.#blockSize, data.rubbish[i].y*this.#blockSize, this.#blockSize, this.#blockSize)
        }
        this.#context.fillStyle="#ffffff"
        this.#context.fillRect(this.#map.getPositionInfo().player.x*this.#blockSize, this.#map.getPositionInfo().player.y*this.#blockSize, this.#blockSize, this.#blockSize)
        
        this.#context.fillStyle="#000000"
        for (let i=0; i<this.#map.getSizeX()*this.#blockSize; i+=this.#blockSize) {
            this.#context.fillRect(i-1, 0, 1, this.#map.getSizeY()*this.#blockSize)
        }
        for (let i=0; i<this.#map.getSizeY()*this.#blockSize; i+=this.#blockSize) {
            this.#context.fillRect(0, i-1, this.#map.getSizeX()*this.#blockSize, 1)
        }
    }
    
    updateStats() {
        this.#turnsField.innerHTML = this.getTurnsLeft()
        this.#movePointsField.innerHTML = this.getPlayerMovesLeft()
        this.#scoreField.innerHTML = this.getPoints()
    }
}

//game preparation :p
let game
btn_u_l.addEventListener("click", () => {
    game.move('Up-Left')
})
btn_u.addEventListener("click", () => {
    game.move('Up')
})
btn_u_r.addEventListener("click", () => {
    game.move('Up-Right')
})
btn_l.addEventListener("click", () => {
    game.move('Left')
})
btn_r.addEventListener("click", () => {
    game.move('Right')
})
btn_d_l.addEventListener("click", () => {
    game.move('Down-Left')
})
btn_d.addEventListener("click", () => {
    game.move('Down')
})
btn_d_r.addEventListener("click", () => {
    game.move('Down-Right')
})
btn_drop1.addEventListener("click", () => {
    game.drop(0)
})
btn_drop2.addEventListener("click", () => {
    game.drop(1)
})
btn_drop3.addEventListener("click", () => {
    game.drop(2)
})
btn_nextTurn.addEventListener("click", () => {
    game.nextTurn()
})
btn_end.addEventListener("click", () => {
    btn_u_l.setAttribute('disabled', 'true')
    btn_u.setAttribute('disabled', 'true')
    btn_u_r.setAttribute('disabled', 'true')
    btn_l.setAttribute('disabled', 'true')
    btn_r.setAttribute('disabled', 'true')
    btn_d_l.setAttribute('disabled', 'true')
    btn_d.setAttribute('disabled', 'true')
    btn_d_r.setAttribute('disabled', 'true')
    btn_drop1.setAttribute('disabled', 'true')
    btn_drop2.setAttribute('disabled', 'true')
    btn_drop3.setAttribute('disabled', 'true')
    btn_nextTurn.setAttribute('disabled', 'true')
    btn_end.setAttribute('disabled', 'true')
    game.endGame()
})

console.log(btn_end)

window.onload = function() {
    let board = document.getElementById("board")
    let backpack = document.getElementById("backpack")
    let turns = document.getElementById("turnsLeft")
    let movePoints = document.getElementById("movePointsLeft")
    let score = document.getElementById("score")
    game = new Game(board, backpack, turns, movePoints, score)
}
