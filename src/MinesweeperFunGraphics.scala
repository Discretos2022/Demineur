import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.{Color, Cursor, Image, Point, Toolkit}
import java.awt.event.{MouseAdapter, MouseEvent}
import javax.sound.sampled.AudioSystem

object MinesweeperFunGraphics extends App{
  //variable
  val WIDTH : Int = 800
  val HEIGHT: Int = 600
  val window : FunGraphics = new FunGraphics(WIDTH, HEIGHT, "TEST 1.0 - Minesweeper", false)

  var cursorImg:GraphicsBitmap = new GraphicsBitmap("/cursor.png")
  var tk:Toolkit = window.mainFrame.getToolkit();
  var transparent:Cursor = tk.createCustomCursor(tk.getImage(""), new Point(), "trans");
  window.mainFrame.setCursor(transparent);

  var backgroundImg:GraphicsBitmap = new GraphicsBitmap("/Background.png")

  window.addMouseListener(Input.mouse)
  window.addMouseMotionListener(Input.mouseMotion)
  window.setKeyManager(Input.keyboard)

  while(true)
    gameLoop();

  def gameLoop(): Unit = {

    window.frontBuffer.synchronized{

      window.clear()
      window.displayFPS(true)

      window.drawTransformedPicture(0, 0, 0, 2, backgroundImg)

      GameState.State match {

        case GameState.Menu => {

          Menu.display(window)

      }
      case GameState.Game => {

          // TODO : game.update();
          Game.display(window)
        }
      }

    }

    /// Draw The Game Cursor
    window.drawTransformedPicture(Input.cursorX + 16, Input.cursorY + 16, 0, 2, cursorImg)

    window.syncGameLogic(60)

  }
}
