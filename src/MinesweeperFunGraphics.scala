import hevs.graphics.FunGraphics

import java.awt.{Color}
import java.awt.event.{MouseAdapter, MouseEvent}

object MinesweeperFunGraphics extends App{
  //variable
  val taille : Int = 15
  val WIDTH : Int = 800
  val HEIGHT: Int = 600
  val window : FunGraphics = new FunGraphics(WIDTH, HEIGHT, "TEST 1.0 - Minesweeper", false)

  var mouse: MouseAdapter = new MouseAdapter() {
    override def mouseClicked(e: MouseEvent): Unit = {
      val posX = e.getX
      val posY = e.getY
      val bouton = e.getButton //1 : Click gauche, 3 : click droit
      println(s"Mouse position $posX - $posY")
      Menu.update(posX, posY, bouton)
    }
  }
  //Menu.display(window)

  def difficulties(): Int = {
    //diff
    return 1
  }
  def ending() : Unit={
  }

  gameLoop();

  def gameLoop(): Unit = {

    window.displayFPS(true)

    GameState.State match {

      case GameState.Menu =>{
        window.addMouseListener(mouse)

        Menu.display(window)

      }
      case GameState.Game => {

        // TODO : game.update();
        // TODO : game.display(window);

      }
    }
  }
}
