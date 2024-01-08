import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.Color

object Game {
  var gameBoard : Array[Array[Case]] = Array.empty
  var numberedCase : Array[GraphicsBitmap] = Array.ofDim(10)
    for(i : Int <- 0 to 8){
      numberedCase(i) = new GraphicsBitmap(s"/Case_$i.png")
    }
    numberedCase(9) = new GraphicsBitmap("/Mine.png")
  var caseSide: Int = 16
  var mine: Int = 0
  var scale : Double = 2
  def InitGame(difficult:Int): Unit = {
     difficult match {
      case 1 => gameBoard = Array.ofDim(9, 9)
                mine = 10
                scale = 2
      case 2 => gameBoard = Array.ofDim(15, 15)
                mine = 40
                scale = 1.5
      case 3 => gameBoard = Array.ofDim(20, 20)
                mine = 75
                scale = 1.2
      case 4 => gameBoard = Array.ofDim(30, 16)
                mine = 99
                scale = 1.2
      case _ => gameBoard = Array.empty
    }
    for (i: Int <- gameBoard.indices;
         j: Int <- gameBoard(0).indices) {
      gameBoard(i)(j) = new Case
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
  wind.drawString(50, 50, s"number of mine left : $mine", Color.darkGray, 20)
    for(i : Int <- gameBoard.indices;
        j : Int <- gameBoard(0).indices){
      if(gameBoard(i)(j).isHide)
      wind.drawTransformedPicture(((800-caseSide*scale*gameBoard.length)/2 + (caseSide*scale)/2 + i *scale*16).toInt,
                                  ((600-caseSide*scale*gameBoard(0).length)/2 + caseSide/2 + j *scale* 16).toInt,
                             0,
                                    scale,
                                    numberedCase(0))
      else if(gameBoard(i)(j).flag){
        wind.drawTransformedPicture(((800 - caseSide * scale * gameBoard.length) / 2 + (caseSide * scale) / 2 + i * scale * 16).toInt,
          ((600 - caseSide * scale * gameBoard(0).length) / 2 + caseSide / 2 + j * scale * 16).toInt,
          0,
          scale,
          numberedCase(0))
      }
      else{
        wind.drawTransformedPicture(((800 - caseSide * scale * gameBoard.length) / 2 + (caseSide * scale) / 2 + i * scale * 16).toInt,
          ((600 - caseSide * scale * gameBoard(0).length) / 2 + caseSide / 2 + j * scale * 16).toInt,
          0,
          scale,
          numberedCase(gameBoard(i)(j).numOfAdjacentMine))
      }
    }
  }
}
