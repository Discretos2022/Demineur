import hevs.graphics.FunGraphics

import java.awt.Color

object Writer {

  def Write(text:String, x:Int, y:Int, backColor: Color, frontColor:Color, size:Int, window:FunGraphics): Unit = {

    for (i <- -2 to 2){
      for (j <- -2 to 2) {

        window.drawString(x - i, y - j, text, backColor, size)

      }
    }

    window.drawString(x, y, text, frontColor, size)

  }

}
