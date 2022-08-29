document.addEventListener('DOMContentLoaded', () => {
    const grid = document.querySelector('.grid')
    let squares = Array.from(document.querySelectorAll('.grid div'))
    const scoreDisplay = document.querySelector('#score')
    const startBtn = document.querySelector('#start-button')
    const width = 10
    let nextRandom = 0
    let timerId
    let score = 0
    const color = [
        'orange',
        'green',
        'pink',
        'blue',
        'red'
    ]

    //total index 0-199 , center start block is 3x3 index is 4/5/6/14/15/16/24/25/26
    const lTetromino = [
        [1, width + 1, width * 2 + 1, 2],
        [width, width + 1, width + 2, width * 2 + 2],
        [1, width + 1, width * 2 + 1, width * 2],
        [width, width * 2, width * 2 + 1, width * 2 + 2]
    ]

    const zTetromino = [
        [0, width, width + 1, width * 2 + 1],
        [width + 1, width + 2, width * 2, width * 2 + 1],
        [0, width, width + 1, width * 2 + 1],
        [width + 1, width + 2, width * 2, width * 2 + 1]
    ]

    const tTetromino = [
        [1, width, width + 1, width + 2],
        [1, width + 1, width + 2, width * 2 + 1],
        [width, width + 1, width + 2, width * 2 + 1],
        [1, width, width + 1, width * 2 + 1]
    ]

    const oTetromino = [
        [0, 1, width, width + 1],
        [0, 1, width, width + 1],
        [0, 1, width, width + 1],
        [0, 1, width, width + 1]
    ]

    const iTetromino = [
        [1, width + 1, width * 2 + 1, width * 3 + 1],
        [width, width + 1, width + 2, width + 3],
        [1, width + 1, width * 2 + 1, width * 3 + 1],
        [width, width + 1, width + 2, width + 3]
    ]

    const theTetrominoes = [lTetromino, zTetromino, tTetromino, oTetromino, iTetromino]

    let currentPosition = 4
    let currentRotation = 0

    //randomly select a Tetromino and its first rotation
    let random = Math.floor(Math.random() * theTetrominoes.length)
    let current = theTetrominoes[random][currentRotation]

    //draw the first rotation in the first tetromino
    function draw() {
        current.forEach(index => {
            squares[currentPosition + index].classList.add('tetromino')
            squares[currentPosition + index].style.backgroundColor = color[random]
        })
    }

    //undraw the Tetromino
    function undraw() {
        current.forEach(index => {
            squares[currentPosition + index].classList.remove('tetromino')
            squares[currentPosition + index].style.backgroundColor = ''
        })
    }

    //make the tetromino move down every second
    //timerId = setInterval(moveDown, 300)

    //assign function to keycode
    function control(e) {
        switch (e.keyCode) {
            case 65:
                moveLeft()
                break;
            case 37:
                moveLeft()
                break;
            case 87:
                rotate()
                break;
            case 38:
                rotate()
                break;
            case 68:
                moveRight()
                break;
            case 39:
                moveRight()
                break;
            case 83:
                moveDown()
                break;
            case 40:
                moveDown()
                break;
        }
        /*
        if (e.keyCode === 65 || 37) {
            moveLeft()
        } else if (e.keyCode === 87 || 38) {
            rotate()
        } else if (e.keyCode === 68 || 39) {
            moveRight()
        } else if (e.keyCode === 83 || 40) {
            moveDown()
        }*/
    }

    document.addEventListener('keydown', control)

    //move down function
    function moveDown() {
        undraw()
        //every 10 index meaning 1 row
        currentPosition += width
        draw()
        freeze()
    }

    //freezefunction
    function freeze() {
        // +width means to check next row
        if (current.some(index => squares[currentPosition + index +width].classList.contains('taken'))) {
            current.forEach(index => squares[currentPosition + index].classList.add('taken'))
            //start a new tetromino falling
            random = nextRandom
            nextRandom = Math.floor(Math.random() * theTetrominoes.length)
            current = theTetrominoes[random][currentRotation]
            currentPosition = 4
            draw()
            displayShape()
            addScore()
            gameOver()
        }
    }

    //move the tetromino left, unless is at the edge or there is a blockage
    function moveLeft() {
        undraw()
        //any tetromino part has true(touch the left wall)
        const isAtLeftEdge = current.some(index => (currentPosition + index) % width === 0)

        if (!isAtLeftEdge) currentPosition -= 1

        if (current.some(index => squares[currentPosition + index].classList.contains('taken'))) {
            currentPosition += 1
        }
        draw()
    }

    //move the tetromino right , unless is at the edge or there is a blockage
    function moveRight() {
        undraw()
        const isAtRightEdge = current.some(index => (currentPosition + index) % width === width - 1)

        if (!isAtRightEdge) currentPosition += 1

        //if out of bound then push back
        if (current.some(index => squares[currentPosition + index].classList.contains('taken'))) {
            currentPosition -= 1
        }
        draw()
    }
    ///FIX ROTATION OF TETROMINOS A THE EDGE
    function isAtRight() {
        return current.some(index=> (currentPosition + index + 1) % width === 0)
    }

    function isAtLeft() {
        return current.some(index=> (currentPosition + index) % width === 0)
    }

    function checkRotatedPosition(P){
        P = P || currentPosition       //get current position.  Then, check if the piece is near the left side.
        if ((P+1) % width < 4) {         //add 1 because the position index can be 1 less than where the piece is (with how they are indexed).
            if (isAtRight()){            //use actual position to check if it's flipped over to right side
                currentPosition += 1    //if so, add one to wrap it back around
                checkRotatedPosition(P) //check again.  Pass position from start, since long block might need to move more.
            }
        }
        else if (P % width > 5) {
            if (isAtLeft()){
                currentPosition -= 1
                checkRotatedPosition(P)
            }
        }
    }
    //rotate the tetromino
    function rotate() {
        undraw()
        currentRotation++
        //if the current rotation gets to 4 , make it go back to 0
        if (currentRotation === current.length) {
            currentRotation = 0
        }
        current = theTetrominoes[random][currentRotation]
        checkRotatedPosition()
        draw()
    }

    //show up-next tetromino in minigrid
    const displaySquares = document.querySelectorAll('.mini-grid div')
    const displayWidth = 4
    const displayIndex = 0

    //the Tetrominos without rotations
    const upNextTetrominoes = [
        [1, displayWidth + 1, displayWidth * 2 + 1, 2], //T shape
        [0, displayWidth, displayWidth + 1, displayWidth * 2 + 1], //z
        [1, displayWidth, displayWidth + 1, displayWidth + 2], //t
        [0, 1, displayWidth, displayWidth + 1],//square
        [1, displayWidth + 1, displayWidth * 2 + 1, displayWidth * 3 + 1] // I
    ]

    //display the shape in the mini-grid display
     function displayShape() {
        //remove any trace of a tetromino from the entire grid
        displaySquares.forEach(square => {
            square.classList.remove('tetromino')
            square.style.backgroundColor = ''
        })
         upNextTetrominoes[nextRandom].forEach(index => {
             displaySquares[displayIndex + index].classList.add('tetromino')
             displaySquares[displayIndex + index].style.backgroundColor = color[nextRandom]
         })
    }
    //add functionality to the button
    startBtn.addEventListener('click', () =>{
        if(timerId){
            clearInterval(timerId)
            timerId = null
        }else{
            draw()
            timerId=setInterval(moveDown, 300)

        }
    })
    function addScore(){
        for(let i = 0 ;i<=199; i += width){
            const row = [i,i+1,i+2,i+3,i+4,i+5,i+6,i+7,i+8,i+9]
            if(row.every(index => squares[index].classList.contains('taken'))){
                score += 10
                scoreDisplay.innerHTML = score
                row.forEach(index => {
                    squares[index].classList.remove('taken')
                    squares[index].classList.remove('tetromino')
                    squares[index].style.backgroundColor = ''
                })
                //splice return what you delete , not deleted squares
                const removeSquares = squares.splice(i,width)
                //delete + squares = 10-40 + 160-190(depend on full fill rows count)
                squares = removeSquares.concat(squares)
                squares.forEach(cell => grid.appendChild(cell))

                console.log(score)

            }
        }
    }
    function gameOver() {
        if(current.some(index => squares[index + currentPosition].classList.contains('taken'))){
            scoreDisplay.innerHTML = 'end'
            clearInterval(timerId)
        }
    }
})
