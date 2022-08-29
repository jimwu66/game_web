import { snakeEat , snakeGrow } from "./snake.js";
import { getRandomGridPosition } from "./grid.js";

let food = getRandomFoodPosition()

const EXPANSION_RATE = 2
export function draw(gameBoard){
        const foodElement = document.createElement('div')
        foodElement.style.gridRowStart = food.y
        foodElement.style.gridColumnStart = food.x
        foodElement.classList.add('food')
        gameBoard.appendChild(foodElement)
}
export function update(){
    if(snakeEat(food)){
        snakeGrow(EXPANSION_RATE)
        food = getRandomFoodPosition()
    }
}
function getRandomFoodPosition(){
    let randomFoodPosition
    while( randomFoodPosition == null || snakeEat(randomFoodPosition)){
        randomFoodPosition = getRandomGridPosition()
    }
    return randomFoodPosition
}