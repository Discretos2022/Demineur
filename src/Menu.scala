import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.Color
import Game.bestScores
object Menu {
  var nameBanner : GraphicsBitmap = new GraphicsBitmap("/Banner.png")
  var easy : GraphicsBitmap = new GraphicsBitmap("/button_easy.png")
  var medium : GraphicsBitmap = new GraphicsBitmap("/button_medium.png")
  var hard : GraphicsBitmap = new GraphicsBitmap("/button_hard.png")
  var hardcore : GraphicsBitmap = new GraphicsBitmap("/button_hardcore.png")
  var easy_selected: GraphicsBitmap = new GraphicsBitmap("/button_easy_selected.png")
  var medium_selected: GraphicsBitmap = new GraphicsBitmap("/button_medium_selected.png")
  var hard_selected: GraphicsBitmap = new GraphicsBitmap("/button_hard_selected.png")
  var hardcore_selected: GraphicsBitmap = new GraphicsBitmap("/button_hardcore_selected.png")

  val WIDTH : Int = 128
  val HEIGHT: Int = 32
  val posy : Int = 200
  var diff : Int = 0

  var easySelected:Boolean = false;
  var mediumSelected:Boolean = false;
  var hardSelected:Boolean = false;
  var hardcoreSelected:Boolean = false;

  def display(wind  : FunGraphics): Unit = {
    wind.drawTransformedPicture(400, 100, 0, 3,  nameBanner)
    wind.drawFancyString(300, 150, "What difficulty do you want?", Color.YELLOW, 15 )
    if(easySelected) {
      Writer.Write("10x10 grid | 10 mines", 315, 400, Color.BLACK, Color.WHITE, 20, wind)
      Writer.Write("best score : " + bestScores(0), 315, 430, Color.BLACK, Color.WHITE, 20, wind)
      wind.drawPicture(120, posy, easy_selected)
    } else
      wind.drawPicture(120, posy, easy)

    if(mediumSelected) {
      Writer.Write("15x15 grid | 40 mines", 315, 400, Color.BLACK, Color.WHITE, 20, wind)
      Writer.Write("best score : "+ bestScores(1), 315, 430, Color.BLACK, Color.WHITE, 20, wind)
      wind.drawPicture(310, posy, medium_selected)
    } else
      wind.drawPicture(310, posy, medium)

    if(hardSelected) {
      Writer.Write("20x20 grid | 75 mines", 315, 400, Color.BLACK, Color.WHITE, 20, wind)
      Writer.Write("best score : " + bestScores(2), 315, 430, Color.BLACK, Color.WHITE, 20, wind)
      wind.drawPicture(490, posy, hard_selected)
    } else
      wind.drawPicture(490, posy, hard)

    if(hardcoreSelected) {
      Writer.Write("25x25 grid | 130 mines", 315, 400, Color.BLACK, Color.WHITE, 20, wind)
      Writer.Write("best score : " + bestScores(3), 315, 430, Color.BLACK, Color.WHITE, 20, wind)
      wind.drawPicture(670, posy, hardcore_selected)
    } else
      wind.drawPicture(670, posy, hardcore)

    Writer.Write("version 1.0 beta", 5, 600 - 5, Color.BLACK, Color.WHITE, 15, wind)

  }

  def isIn(mouseX : Int, mouseY : Int, posX : Int, posY : Int, width : Int, height : Int) : Boolean ={
    if(posX-width/2 < mouseX && mouseX < posX + width/2 && posY-height/2 < mouseY && mouseY < posY+height/2){
      return true
    }
    return false
  }
}
