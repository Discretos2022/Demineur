import hevs.graphics.FunGraphics

import java.awt.Color
import java.awt.event.{MouseAdapter, MouseEvent}

object minesweaperFunGraphics extends App{
  val WIDTH : Int = 800
  val HEIGHT: Int = 600
  val window : FunGraphics = new FunGraphics(WIDTH, HEIGHT, "TEST 1.0 - Minesweaper")
  window.addMouseListener(new MouseAdapter() {
    override def mouseClicked(e: MouseEvent) : Unit = {
      val event = e
      val posX = event.getX
      val posY = event.getY
      val click = event.getButton //1 : Click gauche, 3 : click droit

    }
  })
  window.drawString(WIDTH/2 -150, 100, "WELCOME TO MINESWEAPER", Color.RED, 20)
  difficulties()
  def difficulties(): Int = {
    //diff
    return 1
  }
  def ending() : Unit={
  }

}
