import hevs.graphics.FunGraphics

import java.awt.event.{MouseAdapter, MouseEvent}

/*object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")
  }
}*/

object Main extends App{

  // TODO : Y a pas grand chose d'écrit.

  var window:FunGraphics = new FunGraphics(800, 600, "TEST 1.0")


  window.addMouseListener(new MouseAdapter() {
    override def mouseClicked(e: MouseEvent): Unit = {
      val event = e
      val posX = event.getX
      val posY = event.getY
      //val click = event.getButton //1 : Click gauche, 3 : click droit

      if(Input.MouseClick(e, Input.Left))
        println("LEFT !!!!")

    }
  })


}




