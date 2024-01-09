import MinesweeperFunGraphics.{HEIGHT, WIDTH}
import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.Color

object Game {
  var gameBoard : Array[Array[Case]] = Array.empty
  var numberedCase : Array[GraphicsBitmap] = Array.ofDim(11)
  var caseHide:GraphicsBitmap = new GraphicsBitmap("/CaseHide.png");
    for(i : Int <- 0 to 8){
      numberedCase(i) = new GraphicsBitmap(s"/Case_$i.png")
    }
    numberedCase(9) = new GraphicsBitmap("/Mine.png")
    numberedCase(10) = new GraphicsBitmap("/Flag.png")
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
      var x : Int = ((WIDTH-caseSide*scale*gameBoard.length)/2 + (caseSide*scale)/2 + i *scale*16).toInt
      var y : Int = ((HEIGHT-caseSide*scale*gameBoard(0).length)/2 + caseSide*scale/2 + j *scale* 16).toInt
      var img : GraphicsBitmap = numberedCase(gameBoard(i)(j).numOfAdjacentMine)
      if(gameBoard(i)(j).isHide) {
         img = caseHide
      }
      else if(gameBoard(i)(j).flag){
        img = numberedCase(10)
      }
      wind.drawTransformedPicture(x, y, 0, scale, img)
    }
  }
}
