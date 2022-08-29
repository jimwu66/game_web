export const SNAKE_SPEED = 12;
import { getInputDirection} from "./input.js";
let newSegments = 0
const snakeBody = [{x:11,y:11}]
                //   {x:11,y:12},
                //   {x:11,y:13},
                //   {x:11,y:14}]
const scoreData = document.getElementById('score')
let score = 0
//each apple eaten get 10 point
const APPLE = 10

export function draw(gameBoard){
    snakeBody.forEach(segment => {
        const snakeElement = document.createElement('div')
        snakeElement.style.gridRowStart = segment.y
        snakeElement.style.gridColumnStart = segment.x
        snakeElement.classList.add('snake')
        gameBoard.appendChild(snakeElement)
    });
}
export function update(){
    snakeAdd()
    scoreData.innerText = score
    const inputDirection = getInputDirection()
    for(let i = snakeBody.length -2;i>=0;i--){
        snakeBody[i+1] = { ...snakeBody[i]}
    }
    snakeBody[0].x += inputDirection.x
    snakeBody[0].y += inputDirection.y
}
export function snakeEat(position, { ignoreHead= false } = {} ){
    return snakeBody.some((segment, index) => {
        if(ignoreHead && index === 0) return false
        return equalPosition(position,segment)
    })
}
export function snakeHead(){
    return snakeBody[0]
}
export function headHitBody(){
    return snakeEat(snakeBody[0], { ignoreHead : true })
}
function equalPosition(pos1,pos2){
    return pos1.x === pos2.x && pos1.y === pos2.y
}
export function snakeGrow(amount){
    newSegments += amount
    score += APPLE
}
function snakeAdd(){
    for(let i =0;i<newSegments;i++){
        snakeBody.push({ ...snakeBody[snakeBody.length-1]})
    }
    newSegments = 0
}
