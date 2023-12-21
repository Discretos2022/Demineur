import java.awt.event.MouseEvent

object Input {

  val Right:String = "Right"
  val Left:String = "Left"
  val Middle:String = "Middle"

  def MouseClick(e: MouseEvent, key:String):Boolean = {

    if(e.getButton == 1 && key == Left)
      return true;
    if (e.getButton == 2 && key == Middle)
      return true;
    if (e.getButton == 3 && key == Right)
      return true;
    return false;
  }

}
