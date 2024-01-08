import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.Color

object Menu {
  var nameBanner : GraphicsBitmap = new GraphicsBitmap("/Banner.png")
  var easy : GraphicsBitmap = new GraphicsBitmap("/button_easy.png")
  var medium : GraphicsBitmap = new GraphicsBitmap("/button_medium.png")
  var hard : GraphicsBitmap = new GraphicsBitmap("/button_hard.png")
  var hardcore : GraphicsBitmap = new GraphicsBitmap("/button_hardcore.png")
  def update(mouseX : Int, mouseY : Int, bouton : Int): Unit = {
    if(bouton == 1){
      print("click")
      if(isIn(mouseX, mouseY, 120, 200, 128, 32)){
        print("ok")
      }
    }
  }
  def display(wind  : FunGraphics): Unit = {
    wind.drawTransformedPicture(400, 100, 0, 3,  nameBanner)
    wind.drawFancyString(300, 150, "What difficulty do you want?", Color.YELLOW, 15 )
    wind.drawPicture(120, 200, easy)
    wind.drawPicture(310, 200, medium)
    wind.drawPicture(490, 200, hard)
    wind.drawPicture(670, 200, hardcore)
  }

  def isIn(mouseX : Int, mouseY : Int, posX : Int, posY : Int, width : Int, height : Int) : Boolean ={
    if(posX-width/2 < mouseX && mouseX < posX + width/2 && posY-height/2 < mouseY && mouseY < posY+height/2){
      return true
    }
    return false
  }
}
