import hevs.graphics.FunGraphics

import java.awt.{Color}
import java.awt.event.{MouseAdapter, MouseEvent}

object MinesweeperFunGraphics extends App{
  //variable
  val taille : Int = 15
  val WIDTH : Int = 800
  val HEIGHT: Int = 600
  val window : FunGraphics = new FunGraphics(WIDTH, HEIGHT, "TEST 1.0 - Minesweaper", false)

  var mouse : MouseAdapter = new MouseAdapter() {
    override def mouseClicked(e: MouseEvent): Unit = {
      val event = e
      val posX = event.getX
      val posY = event.getY
      val click = event.getButton //1 : Click gauche, 3 : click droit
    }
  window.addMouseListener(mouse)
  Menu.display(window)

  def difficulties(): Int = {
    //diff
    return 1
  }
  def ending() : Unit={
  }
  if(MouseEvent.MOUSE_CLICKED == 1){
    print("gauche")
  }
  }
}
