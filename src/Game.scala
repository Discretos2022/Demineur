import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.Color

class Game {
  var gameBoard : Array[Array[Case]] = Array.empty
  var numberedCase : Array[GraphicsBitmap] = Array.empty
    for(i : Int <- 0 to 8){
      numberedCase(i) = new GraphicsBitmap(s"Case_$i.png")
    }
    numberedCase(9) = new GraphicsBitmap("Mine.png")
  var caseSide: Int = 16
  var mine: Int = 0
  def InitGame(difficult:Int): Unit = {
     difficult match {
      case 1 => gameBoard = Array.ofDim(9, 9)
                mine = 10
      case 2 => gameBoard = Array.ofDim(15, 15)
                mine = 40
      case 3 => gameBoard = Array.ofDim(20, 20)
                mine = 75
      case 4 => gameBoard = Array.ofDim(16, 30)
                mine = 99
      case _ => gameBoard = Array.empty
    }
  }

  def update(mouseX: Int, mouseY: Int, bouton: Int): Int = {
    if (bouton == 1) {

    }
    else if(bouton == 3){

    }
    return 0
  }

  def display(wind: FunGraphics): Unit = {
    for(i : Int <- gameBoard.indices;
        j : Int <- gameBoard(0).indices){
      if(gameBoard(i)(j).isHide)
      wind.drawPicture((314+caseSide/2 + i*2), 60+ caseSide/2 + i*2,numberedCase(0))
    }
  }



}
