const GRID_SIZE = 21

export function getRandomGridPosition(){
    let randomGridPosition = { x:0,y:0 }
    randomGridPosition.x = Math.floor(Math.random()*GRID_SIZE) + 1
    randomGridPosition.y = Math.floor(Math.random()*GRID_SIZE) + 1
    return randomGridPosition
}
export function outsideGrid(position){
    return position.x > GRID_SIZE ||  position.y > GRID_SIZE ||
           position.x < 1         ||  position.y < 1
}