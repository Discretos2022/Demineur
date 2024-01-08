import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.Color

object Menu {
  var nameBanner : GraphicsBitmap = new GraphicsBitmap("/Banner.png")
  var easy : GraphicsBitmap = new GraphicsBitmap("/button_easy.png")
  var medium : GraphicsBitmap = new GraphicsBitmap("/button_medium.png")
  var hard : GraphicsBitmap = new GraphicsBitmap("/button_hard.png")
  var hardcore : GraphicsBitmap = new GraphicsBitmap("/button_hardcore.png")
  val WIDTH : Int = 128
  val HEIGHT: Int = 32
  val posy : Int = 200
  def update(mouseX : Int, mouseY : Int, bouton : Int): Int = {
    if(bouton == 1){
      print("click")
      if(isIn(mouseX, mouseY, 120, posy, WIDTH, HEIGHT)){
        //easy
        print("ok")
      }else if(isIn(mouseX, mouseY, 310, posy, WIDTH, HEIGHT)){
        //medium
      }else if(isIn(mouseX, mouseY, 490, posy, WIDTH, HEIGHT)) {
        //diff
        }
    }
    return 0
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
