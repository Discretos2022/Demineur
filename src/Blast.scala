import Game.{blastNum, blasts}
import MinesweeperFunGraphics.blastAnimation
import hevs.graphics.FunGraphics

class Blast(PosX:Int, PosY:Int) {


  var animation:Animation = new Animation(blastAnimation)

  blastNum += 1;

  def draw(index:Int, window:FunGraphics): Unit = {
    animation.start()

    if(!animation.end) {
      animation.draw(PosX, PosY, 5, window)
    }



  }


}
