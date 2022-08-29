import { update as updateSnake , draw as drawSnake , snakeHead , headHitBody , SNAKE_SPEED } from "./snake.js";
import { update as updateFood , draw as drawFood } from "./food.js";
import { outsideGrid } from "./grid.js";

const gameBoard = document.getElementById('game_board')
let lastRenderTime = 0;
let gameOver = false


function main(currentTime){
    if(gameOver){
        saveScore(currentTime)
      return Swal.fire('Game over\n score:'+document.getElementById('score').innerHTML)
    }
    window.requestAnimationFrame(main);
    const secondsFromLastRender = (currentTime -lastRenderTime)/1000
    if(secondsFromLastRender < 1/SNAKE_SPEED) return

    //console.log('render');
    lastRenderTime = currentTime
    update()
    draw()

}
window.requestAnimationFrame(main);

function draw(){
    gameBoard.innerHTML=''
    drawSnake(gameBoard)
    drawFood(gameBoard)
}
function update(){
    updateSnake()
    updateFood()
    checkDeath()
}
function checkDeath(){
   gameOver = outsideGrid(snakeHead())||headHitBody()
}
function saveScore(current){
    const name = document.getElementById('name').innerHTML
    const score = document.getElementById('score').innerHTML
    const gameName = document.title
    const icon = userInfo.user.level
    let duration = Math.floor(current)
    console.log(name + ',' + score + ',' + gameName + ',' + duration)
    $.ajax({
        url:'/v1/score/save',
        data: {
            nickname:name,
            score:score,
            icon:icon,
            gameName:gameName,
            duration:duration
        },
        method: 'GET',
        success:function (r){
            if(r.code == OK){
                console.log(r.message)
            }else{
                console.log(r.message)
            }
        }
    });
}
