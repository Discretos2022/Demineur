import Game.{caseSide, gameBoard, mine,mine2, numberedCase, scale, ticks, second, minute, hours}
import Menu.{HEIGHT, WIDTH, diff, easySelected, hardSelected, hardcoreSelected, isIn, mediumSelected, posy}
import MinesweeperFunGraphics.{cursorImg, window}

import java.awt.event.{KeyAdapter, KeyEvent, MouseAdapter, MouseEvent, MouseMotionListener}
import scala.Console.println
import scala.io.StdIn.readLine
import scala.util.Random

object Input {

  var posX: Int = 0;
  var posY: Int = 0;

  var initedMine:Boolean = false;

  var cursorX = 0;
  var cursorY = 0;

  var FPS:Boolean = false;

  var mouse: MouseAdapter = new MouseAdapter() {
    override def mouseClicked(e: MouseEvent): Unit = {

      var mouseX = e.getX;
      var mouseY = e.getY;

      GameState.State match {

        case GameState.Menu => {

          if (isIn(mouseX, mouseY, 120, posy, WIDTH, HEIGHT)) {
            //easy
            diff = 1
            GameState.State = GameState.Game;
          } else if (isIn(mouseX, mouseY, 310, posy, WIDTH, HEIGHT)) {
            //medium
            diff = 2
            GameState.State = GameState.Game;
          } else if (isIn(mouseX, mouseY, 490, posy, WIDTH, HEIGHT)) {
            //hard
            diff = 3
            GameState.State = GameState.Game;
          } else if (isIn(mouseX, mouseY, 670, posy, WIDTH, HEIGHT)) {
            //hardcore
            diff = 4
            GameState.State = GameState.Game;
          }
          Game.InitGame(diff)
        }

        case GameState.Game => {

          for (i: Int <- gameBoard.indices;
               j: Int <- gameBoard(0).indices) {
            var x: Int = ((800 - caseSide * scale * gameBoard.length) / 2 + (caseSide * scale) / 2 + i * scale * 16).toInt
            var y: Int = ((600 - caseSide * scale * gameBoard(0).length) / 2 + caseSide * scale / 2 + j * scale * 16).toInt

            if (isIn(mouseX, mouseY, x, y, (caseSide * scale).toInt, (caseSide * scale).toInt)) {
              if (e.getButton == 1) {

                if(!initedMine)
                  InitMines(i, j);

                if(!gameBoard(i)(j).flag && !gameBoard(i)(j).isMine())
                  discoverAdjacentCase(i, j);
              }

              else if (e.getButton == 3) {
                if(initedMine)
                  if(gameBoard(i)(j).isHide) {
                    if(gameBoard(i)(j).flag) {
                      gameBoard(i)(j).flag = false
                      mine2 += 2
                    } else
                      gameBoard(i)(j).flag = true;
                      mine2 -= 1
                    if(gameBoard(i)(j).isMine() && gameBoard(i)(j).flag){
                      mine -= 1
                    }
                  };
              }
            }
          }
        }
        case _ => GameState.State = GameState.Game
      }
    }
  }



  var mouseMotion: MouseMotionListener = new MouseMotionListener {
    override def mouseDragged(e: MouseEvent): Unit = {

      cursorX = e.getX
      cursorY = e.getY

      easySelected = false;
      mediumSelected = false;
      hardSelected = false;
      hardcoreSelected = false;

      if (isIn(cursorX, cursorY, 120, posy, WIDTH, HEIGHT))
        easySelected = true;
      else if (isIn(cursorX, cursorY, 310, posy, WIDTH, HEIGHT))
        mediumSelected = true;
      else if (isIn(cursorX, cursorY, 490, posy, WIDTH, HEIGHT))
        hardSelected = true;
      else if (isIn(cursorX, cursorY, 670, posy, WIDTH, HEIGHT))
        hardcoreSelected = true;

    }

    override def mouseMoved(e: MouseEvent): Unit = {

      cursorX = e.getX
      cursorY = e.getY

      easySelected = false;
      mediumSelected = false;
      hardSelected = false;
      hardcoreSelected = false;

        if (isIn(cursorX, cursorY, 120, posy, WIDTH, HEIGHT))
          easySelected = true;
        else if (isIn(cursorX, cursorY, 310, posy, WIDTH, HEIGHT))
          mediumSelected = true;
        else if (isIn(cursorX, cursorY, 490, posy, WIDTH, HEIGHT))
          hardSelected = true;
        else if (isIn(cursorX, cursorY, 670, posy, WIDTH, HEIGHT))
          hardcoreSelected = true;




    }

  }


  var keyboard: KeyAdapter = new KeyAdapter() {

    override def keyPressed(e: KeyEvent): Unit = {

      if(e.getKeyCode == KeyEvent.VK_F12) {
        initedMine = false;
        GameState.State = GameState.Menu
        ticks = 0;
        second = 0;
        minute = 0;
        hours = 0;
      };

    }

    override def keyTyped(e: KeyEvent): Unit = {

      if(e.getKeyCode == KeyEvent.VK_F) {

        if(!FPS)
          window.displayFPS(true)
        else
          window.displayFPS(false)

        println("salut")


      }

    }

  }

  def discoverAdjacentCase(x: Int, y: Int): Unit = {

    if (gameBoard(x)(y).numOfAdjacentMine != 0) {
      if (gameBoard(x)(y).isHide && !gameBoard(x)(y).isMine())
        gameBoard(x)(y).isHide = false
    };
    else {
      for (i <- x - 1 to x + 1) {
        for (j <- y - 1 to y + 1) {

          if (i >= 0 && i < gameBoard.length && j >= 0 && j < gameBoard(0).length) {

            if (gameBoard(i)(j).isHide && !gameBoard(i)(j).isMine()) {
              gameBoard(i)(j).isHide = false;
              if (gameBoard(i)(j).numOfAdjacentMine == 0 && !gameBoard(i)(j).isMine()) {
                discoverAdjacentCase(i, j)
              }
            }
          }
        }
      }
    }
  }


  def InitMines(selectedX: Int, selectedY: Int): Unit = {

    initedMine = true;

    for (r <- 1 to mine) {

      var randomX = Random.nextInt(gameBoard.length)
      var randomY = Random.nextInt(gameBoard(0).length)

      if (!gameBoard(randomX)(randomY).isMine() && randomX != selectedX && randomY != selectedY)
        gameBoard(randomX)(randomY).setMine()
      else
        setMine()

    }

    for (i <- gameBoard.indices) {
      for (j <- gameBoard(i).indices) {
        gameBoard(i)(j).CalculateAdjacentMine(i, j, gameBoard)
      }
    }


  }

  def setMine(): Unit = {

    var randomX = Random.nextInt(gameBoard.length)
    var randomY = Random.nextInt(gameBoard(0).length)

    if (!gameBoard(randomX)(randomY).isMine())
      gameBoard(randomX)(randomY).setMine()
    else
      setMine()

  }


}
