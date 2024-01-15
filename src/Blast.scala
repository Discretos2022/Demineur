import Game.{blastNum, blasts, gameBoard}
import MinesweeperFunGraphics.{audio, blastAnimation}
import hevs.graphics.FunGraphics

class Blast(PosX:Int, PosY:Int, caseX:Int, caseY:Int) {


  var animation:Animation = new Animation(blastAnimation)

  //blastNum += 1;
  audio.play()
  def draw(index:Int, window:FunGraphics): Unit = {
    animation.start()

    if(animation.frame == 5) {
      gameBoard(caseX)(caseY).explosed = true
    };

    if(!animation.end) {
      animation.draw(PosX, PosY, 3, window) // 3
    }



  }


}
