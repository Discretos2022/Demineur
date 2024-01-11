import MinesweeperFunGraphics.{HEIGHT, WIDTH, random}
import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap
import Menu.diff

import java.awt.Color
import scala.collection.mutable.ArrayBuffer

object Game {
  var gameBoard : Array[Array[Case]] = Array.empty
  var numberedCase : Array[GraphicsBitmap] = Array.ofDim(11)
  var caseHide:GraphicsBitmap = new GraphicsBitmap("/CaseHide.png");
  var caseExplosed:GraphicsBitmap = new GraphicsBitmap("/CaseExplosed.png");
    for(i : Int <- 0 to 8){
      numberedCase(i) = new GraphicsBitmap(s"/Case_$i.png")
    }
    numberedCase(9) = new GraphicsBitmap("/Mine.png")
    numberedCase(10) = new GraphicsBitmap("/Flag.png")

  var backgroundEndGame:GraphicsBitmap = new GraphicsBitmap("/BackgroundEndGame.png")

  var caseSide: Int = 16
  var mine: Int = 0
  var mine2: Int = 0
  var scale : Double = 2
  var bestScores : Array[Int] = Array.ofDim(4)

  var ticks:Int = 0;
  var second:Int = 0;
  var minute:Int = 0;
  var hours:Int = 0;

  // Position dans la grille
  var distanceOfExlosion:Float = 0;
  var positionExplosionX = 0;
  var positionExplosionY = 0;

  var blasts:ArrayBuffer[Blast] = new ArrayBuffer[Blast]();
  var blastNum:Int = 0;

  var counterEnable:Boolean = false;

  def InitGame(difficult:Int): Unit = {
     difficult match {
      case 1 => gameBoard = Array.ofDim(10, 10)
                mine = 10
                mine2 = 10
                scale = 2
      case 2 => gameBoard = Array.ofDim(15, 15)
                mine = 40
                mine2 = 40
                scale = 1.5
      case 3 => gameBoard = Array.ofDim(20, 20)
                mine = 75
                mine2 = 75
                scale = 1.2
      case 4 => gameBoard = Array.ofDim(25, 25)
                mine = 130
                mine2 = 130
                scale = 1
      case _ => gameBoard = Array.empty
    }
    for (i: Int <- gameBoard.indices;
         j: Int <- gameBoard(0).indices) {
      gameBoard(i)(j) = new Case
    }
  }

  def ending(): Int = {
    var end: Int = 10
    if(mine == -1){
      return 0
    }
    else if (mine2 == mine && mine == 0) {
      for (i: Int <- gameBoard.indices;
           j: Int <- gameBoard(0).indices) {
        if(!gameBoard(i)(j).isHide || gameBoard(i)(j).flag){
          end = 1
        }else{
          return 10
        }
      }
      end = 1
    }
    return end
  }

  def update(): Unit = {

    if(counterEnable){
      ticks += 1;

      if (ticks >= 60) {
        ticks = 0; second += 1
      }
      if (second >= 60) {
        second = 0; minute += 1
      }
      if (minute >= 60) {
        minute = 0; hours += 1
      }
    }

    if(ending() == 0 && !AllMinesExplosed()) {

      distanceOfExlosion += 0.25f;

      for(i:Int <- 0 until gameBoard.length){
        for (j: Int <- 0 until gameBoard(0).length) {

          /// Position on screen
          var x: Int = ((800 - caseSide * scale * gameBoard.length) / 2 + (caseSide * scale) / 2 + i * scale * 16).toInt
          var y: Int = ((600 - caseSide * scale * gameBoard(0).length) / 2 + caseSide * scale / 2 + j * scale * 16).toInt

          if(!gameBoard(i)(j).blastIsGenerated) {
            if(gameBoard(i)(j).isMine())
              if(!gameBoard(i)(j).flag)
                if(Math.sqrt(Math.pow(positionExplosionX - i, 2) + Math.pow(positionExplosionY - j, 2)) < distanceOfExlosion) {
                  gameBoard(i)(j).explode(x, y, i, j)
            }
          }
        }
      }

    }

  }
  def display(wind: FunGraphics): Unit = {

    Writer.Write("TIME : " + hours + "h " + minute + "min " + second + "s", 600, 50, Color.black, Color.white, 20, wind)
    Writer.Write(s"number of mine left : $mine2", 50, 50, Color.black, Color.gray, 20, wind)

    //wind.drawString(50, 50, s"number of mine left : $mine2", Color.darkGray, 20)

    var Xvibr:Int = 0;
    var Yvibr:Int = 0;

    if(ending() == 0 && !AllMinesExplosed()){
      Xvibr = random.nextInt(-1, 2);
      Yvibr = random.nextInt(-1, 2)
    }

    for(i : Int <- gameBoard.indices;
        j : Int <- gameBoard(0).indices){
      var x : Int = ((WIDTH-caseSide*scale*gameBoard.length)/2 + (caseSide*scale)/2 + i *scale*16).toInt
      var y : Int = ((HEIGHT-caseSide*scale*gameBoard(0).length)/2 + caseSide*scale/2 + j *scale* 16).toInt
      var img : GraphicsBitmap = numberedCase(gameBoard(i)(j).numOfAdjacentMine)
      if (gameBoard(i)(j).flag) {
        img = numberedCase(10)
      }
      else if(gameBoard(i)(j).isHide) {
         img = caseHide
      }
      else if(gameBoard(i)(j).isMine()){
        img = numberedCase(9)
      }
      if(gameBoard(i)(j).explosed) img = caseExplosed
      wind.drawTransformedPicture(x + Xvibr, y + Yvibr, 0, scale, img)
    }

    for (b: Int <- 0 until blasts.length) {
      blasts(b).draw(b, wind)
    }

    ending() match {
      case 1 => //wind.drawTransformedPicture(0, 0, 0, 2, backgroundEndGame)
                wind.drawFancyString(WIDTH/2-100, HEIGHT/2, "YOU WON!", Color.GREEN, 40)
                counterEnable = false
                if (bestScores(diff - 1) > ticks || bestScores(diff-1) == 0) {
                  bestScores(diff - 1) = ticks
                }
                if(AllMinesExplosed())
                  Writer.Write("PRESS <F12> TO RETURN MAIN MENU", 5, 600 - 5, Color.BLACK, Color.WHITE, 15, wind)
      case 0 => //wind.drawPicture(0, 0, backgroundEndGame)
                wind.drawFancyString(WIDTH/2-100, HEIGHT/2, "YOU LOSE!", Color.RED, 40)
                counterEnable = false
                if (AllMinesExplosed())
                  Writer.Write("PRESS <F12> TO RETURN MAIN MENU", 5, 600 - 5, Color.BLACK, Color.WHITE, 15, wind)
      case _ =>
    }

  }

  def AllMinesExplosed(): Boolean = {

    for (b: Int <- 0 until blasts.length) {
      if (!blasts(b).animation.end)
        return false;
    }
    return true;
  }

}
