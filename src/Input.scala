import Game.{blasts, caseSide, counterEnable, distanceOfExlosion, ending, gameBoard, mine, mine2, positionExplosionX, positionExplosionY, scale, ticks}
import Menu.{HEIGHT, WIDTH, colorDiff, creditSelected, diff, easySelected, hardSelected, hardcoreSelected, isIn, mediumSelected, posy}
import MinesweeperFunGraphics.window

import java.awt.Color
import java.awt.event.{KeyAdapter, KeyEvent, MouseAdapter, MouseEvent, MouseMotionListener}
import scala.Console.println
import scala.io.StdIn.readLine
import scala.util.Random

object Input {

  var posY: Int = 0;
  var initedMine: Boolean = false;
  var cursorX = 0;
  var cursorY = 0;
  var FPS: Boolean = false;

  var F1Pressed: Boolean = false;
  var F1HasPressed: Boolean = false;

  var DoubleClickTime = 0;

  var mouse: MouseAdapter = new MouseAdapter() {
    override def mouseClicked(e: MouseEvent): Unit = {

      cursorX = e.getX;
      cursorY = e.getY;

      GameState.State match {

        case GameState.Menu => {

          if (isIn(cursorX, cursorY, 120, posy, WIDTH, HEIGHT)) {
            //easy
            diff = 1
            Game.InitGame(diff)
            GameState.State = GameState.Game;
          } else if (isIn(cursorX, cursorY, 310, posy, WIDTH, HEIGHT)) {
            //medium
            diff = 2
            Game.InitGame(diff)
            GameState.State = GameState.Game;
          } else if (isIn(cursorX, cursorY, 490, posy, WIDTH, HEIGHT)) {
            //hard
            diff = 3
            Game.InitGame(diff)
            GameState.State = GameState.Game;
          } else if (isIn(cursorX, cursorY, 670, posy, WIDTH, HEIGHT)) {
            //hardcore
            diff = 4
            Game.InitGame(diff)
            GameState.State = GameState.Game;
          }

          // button credit
          else if (isIn(cursorX, cursorY, Menu.creditPosX, Menu.creditPosY, 128, 32)) {
            GameState.State = GameState.Credit;
          }
        }

        case GameState.Game => {

          /// If the game isn't finished
          if (ending() != 0 && ending() != 1) {

            for (i: Int <- gameBoard.indices;
                 j: Int <- gameBoard(0).indices) {
              var x: Int = ((800 - caseSide * scale * gameBoard.length) / 2 + (caseSide * scale) / 2 + i * scale * 16).toInt
              var y: Int = ((600 - caseSide * scale * gameBoard(0).length) / 2 + caseSide * scale / 2 + j * scale * 16).toInt

              if (isIn(cursorX, cursorY, x, y, (caseSide * scale).toInt, (caseSide * scale).toInt)) {
                if (e.getButton == 1) {

                  if (!initedMine)
                    InitMines(i, j);

                  if (!gameBoard(i)(j).flag && !gameBoard(i)(j).isMine() && !gameBoard(i)(j).flagMIneOrNotMine)
                    discoverAdjacentCase(i, j);

                  if (gameBoard(i)(j).isMine() && !gameBoard(i)(j).flag && !gameBoard(i)(j).flagMIneOrNotMine) {
                    gameBoard(i)(j).isHide = false;
                    gameBoard(i)(j).explode(x, y, i, j);
                    positionExplosionX = i;
                    positionExplosionY = j;
                    Save.WriterSave();
                    mine = -1
                  }

                  // Double Click
                  if (DoubleClickTime < 30) { // 30 pour 0.5 second
                    canDoubleClickOnCase(i, j)
                  }
                }

                // If left click
                else if (e.getButton == 3) {
                  if (initedMine)
                    if (gameBoard(i)(j).isHide) {
                      if (!gameBoard(i)(j).flagMIneOrNotMine && !gameBoard(i)(j).flag) {
                        gameBoard(i)(j).flag = true
                      }
                      else if (gameBoard(i)(j).flag) {
                        gameBoard(i)(j).flag = false
                        gameBoard(i)(j).flagMIneOrNotMine = true;
                        mine2 += 2

                        if (gameBoard(i)(j).isMine())
                          mine += 1
                      }
                      else if (gameBoard(i)(j).flagMIneOrNotMine) {
                        gameBoard(i)(j).flagMIneOrNotMine = false;
                        mine2 += 1;
                      }

                      mine2 -= 1

                      if (gameBoard(i)(j).isMine() && gameBoard(i)(j).flag) {
                        mine -= 1
                      }
                    }
                }
              }
            }
          }
        }
        case _ => GameState.State = GameState.State
      }
      DoubleClickTime = 0;
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

      if (isIn(cursorX, cursorY, 120, posy, WIDTH, HEIGHT)) {
        easySelected = true
        colorDiff = Color.green
      };
      else if (isIn(cursorX, cursorY, 310, posy, WIDTH, HEIGHT)) {
        mediumSelected = true
        colorDiff = Color.YELLOW
      };
      else if (isIn(cursorX, cursorY, 490, posy, WIDTH, HEIGHT)) {
        hardSelected = true
        colorDiff = Color.ORANGE
      };
      else if (isIn(cursorX, cursorY, 670, posy, WIDTH, HEIGHT)) {
        hardcoreSelected = true
        colorDiff = Color.red
      };
      else if (isIn(cursorX, cursorY, Menu.creditPosX, Menu.creditPosY, 128, 32))
        creditSelected = true;
    }

    override def mouseMoved(e: MouseEvent): Unit = {

      cursorX = e.getX
      cursorY = e.getY

      easySelected = false;
      mediumSelected = false;
      hardSelected = false;
      hardcoreSelected = false;
      creditSelected = false;
      colorDiff = Color.WHITE

      if (isIn(cursorX, cursorY, 120, posy, WIDTH, HEIGHT)) {
        easySelected = true
        colorDiff = Color.green
      };
      else if (isIn(cursorX, cursorY, 310, posy, WIDTH, HEIGHT)) {
        mediumSelected = true
        colorDiff = Color.YELLOW
      };
      else if (isIn(cursorX, cursorY, 490, posy, WIDTH, HEIGHT)) {
        hardSelected = true
        colorDiff = Color.ORANGE
      };
      else if (isIn(cursorX, cursorY, 670, posy, WIDTH, HEIGHT)) {
        hardcoreSelected = true
        colorDiff = Color.red
      };
      else if (isIn(cursorX, cursorY, Menu.creditPosX, Menu.creditPosY, 128, 32))
        creditSelected = true;
    }
  }

  var keyboard: KeyAdapter = new KeyAdapter() {

    override def keyPressed(e: KeyEvent): Unit = {

      if (Game.AllMinesExplosed() || ending() != 0 || GameState.State != GameState.Game) {
        if (e.getKeyCode == KeyEvent.VK_F12) { // quit is F12
          initedMine = false;
          GameState.State = GameState.Menu
          ticks = 0;
          distanceOfExlosion = 0;
          counterEnable = false;
          blasts.clear();
        }
      }

      if (e.getKeyCode == KeyEvent.VK_F1) {

        F1Pressed = true;

        if (F1Pressed && !F1HasPressed) {
          if (!FPS)
            FPS = true;
          else
            FPS = false;
        }
        window.displayFPS(FPS)
        F1HasPressed = F1Pressed;
      }

      if (e.getKeyCode == KeyEvent.VK_F2) {
        printInstantOfPart()
      }
    }

    override def keyReleased(e: KeyEvent): Unit = {

      if (e.getKeyCode == KeyEvent.VK_F1) {
        F1Pressed = false;
        F1HasPressed = false;
      }
    }
  }

  def discoverAdjacentCase(x: Int, y: Int): Unit = {
    if (gameBoard(x)(y).isMine()) {
      gameBoard(x)(y).isHide = false
    }
    if (gameBoard(x)(y).numOfAdjacentMine != 0) {
      if (gameBoard(x)(y).isHide)
        gameBoard(x)(y).isHide = false
    }
    else {
      for (i <- x - 1 to x + 1;
           j <- y - 1 to y + 1) {
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


  def InitMines(selectedX: Int, selectedY: Int): Unit = {

    initedMine = true;

    counterEnable = true;

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

  //for debug
  def printInstantOfPart(): Unit = {
    println("STATE OF PART (MINES) : \n")
    for (j <- gameBoard(0).indices) {
      for (i <- gameBoard.indices) {
        if (gameBoard(i)(j).isMine())
          print("X  ");
        else
          print("-  ")
      }
      print("\n")
    }
  }

  def canDoubleClickOnCase(x: Int, y: Int): Boolean = {

    var flagAdjacent: Boolean = false;

    if (gameBoard(x)(y).flag)
      return false;
    var Xmin = x - 1;
    var Xmax = x + 1;
    var Ymin = y - 1;
    var Ymax = y + 1;

    if (Xmin < 0) Xmin = 0
    if (Xmax >= gameBoard.length) Xmax = gameBoard.length - 1
    if (Ymin < 0) Ymin = 0
    if (Ymax >= gameBoard(0).length) Ymax = gameBoard(0).length - 1

    for (i: Int <- Xmin to Xmax;
         j: Int <- Ymin to Ymax) {
      if (gameBoard(i)(j).flag)
        flagAdjacent = true;
    }

    if (!flagAdjacent)
      return false

    for (i: Int <- Xmin to Xmax;
         j: Int <- Ymin to Ymax) {

      if (!gameBoard(i)(j).flag && gameBoard(i)(j).isMine()) {
        gameBoard(i)(j).isHide = false;
        gameBoard(i)(j).explode(x, y, i, j);
        positionExplosionX = i;
        positionExplosionY = j;
        mine = -1
        return false
      };

      if (!gameBoard(i)(j).isMine())
        discoverAdjacentCase(i, j)
    }
    return true;
  }
}
