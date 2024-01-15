import Game.scale
import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

class Animation(images: Array[GraphicsBitmap]) {

  var enable: Boolean = false;

  var timer: Int = 0;
  var frame: Int = 0;
  var end: Boolean = false;

  def start(): Unit = {
    enable = true;
  }

  def stop(): Unit = {
    enable = false;
  }

  def draw(posX: Int, posY: Int, elapsedTime: Int, window: FunGraphics): Unit = {

    if (enable) {
      timer += 1;

      if (timer >= elapsedTime) {

        frame += 1;
        timer = 0;
      }

      if (frame >= images.length) {
        end = true; frame = 0
      }
    }

    window.drawTransformedPicture((posX).toInt, (posY).toInt, 0, scale * frame * 0.25, images(frame))
  }
}
