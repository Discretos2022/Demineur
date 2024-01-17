import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.Color
import MinesweeperFunGraphics.bestScores

object Menu {
  //image
  var nameBanner: GraphicsBitmap = new GraphicsBitmap("/Banner.png")
  var easy: GraphicsBitmap = new GraphicsBitmap("/button_easy.png")
  var medium: GraphicsBitmap = new GraphicsBitmap("/button_medium.png")
  var hard: GraphicsBitmap = new GraphicsBitmap("/button_hard.png")
  var hardcore: GraphicsBitmap = new GraphicsBitmap("/button_hardcore.png")
  var easy_selected: GraphicsBitmap = new GraphicsBitmap("/button_easy_selected.png")
  var medium_selected: GraphicsBitmap = new GraphicsBitmap("/button_medium_selected.png")
  var hard_selected: GraphicsBitmap = new GraphicsBitmap("/button_hard_selected.png")
  var hardcore_selected: GraphicsBitmap = new GraphicsBitmap("/button_hardcore_selected.png")
  var credit: GraphicsBitmap = new GraphicsBitmap("/button_credit.png")
  var credit_selected: GraphicsBitmap = new GraphicsBitmap("/button_credit_selected.png")

  var creditPosX = 800 / 2;
  var creditPosY = 600 - 40;

  val WIDTH: Int = 128
  val HEIGHT: Int = 32
  val posy: Int = 250
  var diff: Int = 0

  var easySelected: Boolean = false;
  var mediumSelected: Boolean = false;
  var hardSelected: Boolean = false;
  var hardcoreSelected: Boolean = false;
  var creditSelected: Boolean = false;

  var colorDiff: Color = Color.WHITE;

  //display the menu, if the mouse is over a button, animation begins
  def display(wind: FunGraphics): Unit = {
    var numberHighscore: Int = -1
    wind.drawTransformedPicture(400, 50, 0, 3, nameBanner)

    //wind.drawFancyString(300, 150, "CHOOSE A DIFFICULTY", Color.YELLOW, 15 ) // What difficulty do you want?
    Writer.Write("What difficulty do you want ?", 270, 150, Color.BLACK, colorDiff, 20, wind)

    if (easySelected) {
      numberHighscore = 0
      wind.drawPicture(120, posy, easy_selected)
    }
    else if (mediumSelected) {
      numberHighscore = 1
      wind.drawPicture(310, posy, medium_selected)
    }
    else if (hardSelected) {
      numberHighscore = 2
      wind.drawPicture(490, posy, hard_selected)
    }
    else if (hardcoreSelected) {
      numberHighscore = 3
      wind.drawPicture(670, posy, hardcore_selected)
    } else {
      wind.drawPicture(120, posy, easy)
      wind.drawPicture(310, posy, medium)
      wind.drawPicture(490, posy, hard)
      wind.drawPicture(670, posy, hardcore)
      numberHighscore = -1
    }

    if (numberHighscore >= 0) {
      if(numberHighscore == 0)
        Writer.Write("10x10 grid | 10 mines", 315, 400, Color.BLACK, Color.WHITE, 20, wind)
      else if(numberHighscore == 1)
        Writer.Write("15x15 grid | 40 mines", 315, 400, Color.BLACK, Color.WHITE, 20, wind)
      else if(numberHighscore == 2)
        Writer.Write("20x20 grid | 75 mines", 315, 400, Color.BLACK, Color.WHITE, 20, wind)
      else if(numberHighscore == 3)
        Writer.Write("25x25 grid | 130 mines", 315, 400, Color.BLACK, Color.WHITE, 20, wind)
      Writer.Write("Best score : " + Game.GetTime(bestScores(numberHighscore)), 315, 430, Color.BLACK, Color.WHITE, 20, wind)
    }
    if (creditSelected) {
      wind.drawPicture(creditPosX, creditPosY, credit_selected)
    } else
      wind.drawPicture(creditPosX, creditPosY, credit)
    Writer.Write("version 1.0 beta", 5, 600 - 5, Color.BLACK, Color.WHITE, 15, wind)
  }

  def isIn(mouseX: Int, mouseY: Int, posX: Int, posY: Int, width: Int, height: Int): Boolean = {
    if (posX - width / 2 < mouseX && mouseX < posX + width / 2 && posY - height / 2 < mouseY && mouseY < posY + height / 2) {
      return true
    }
    return false
  }
}
