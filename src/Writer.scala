import hevs.graphics.FunGraphics

import java.awt.Color

object Writer {

  def Write(text:String, x:Int, y:Int, backColor: Color, frontColor:Color, size:Int, window:FunGraphics): Unit = {

    for (i <- -2 to 2;
         j <- -2 to 2) {
        window.drawString(x - i, y - j, text, backColor, size)
    }
    window.drawString(x, y, text, frontColor, size)
  }

  def Write2(text: String, x: Int, y: Int, font:String, backColor: Color, frontColor: Color, size: Int, window: FunGraphics): Unit = {

    for (i <- -2 to 2;
         j <- -2 to 2) {
        window.drawFancyString(x - i, y - j, text, font, 1, size, backColor, 0, 0, 0, 0, Color.GRAY, 0, frontColor, 0)
      }
    window.drawFancyString(x, y, text, font, 1, size, frontColor, 0, 0, 0, 0, Color.GRAY, 0, frontColor, 0)
    }
}
