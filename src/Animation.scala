import Game.scale
import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

// Used for animation
class Animation(images: Array[GraphicsBitmap]) {

  var enable:Boolean = false;

  var timer:Int = 0;
  var frame:Int = 0;
  var end:Boolean = false;

  // Start an animation
  def start(): Unit = {
    enable = true;
  }

  // Stop an animation
  def stop(): Unit = {
    enable = false;
  }

  // Draw an animation
  def draw(posX:Int, posY:Int, elapsedTime:Int, window: FunGraphics): Unit = {

    if(enable){
      timer += 1;

      if (timer >= elapsedTime) {
        frame += 1;
        timer = 0;
      }

      if (frame >= images.length)
        {end = true; frame = 0}
    }
    window.drawTransformedPicture((posX).toInt, (posY).toInt, 0, scale * frame * 0.25, images(frame))
  }

}
