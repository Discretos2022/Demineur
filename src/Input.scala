import Menu.{HEIGHT, WIDTH, diff, isIn, posy}

import java.awt.event.{KeyAdapter, KeyEvent, MouseAdapter, MouseEvent}

object Input {

  val Right:String = "Right"
  val Left:String = "Left"
  val Middle:String = "Middle"

  var posX:Int = 0;
  var posY:Int = 0;
  var leftButton:Boolean = false;
  var oldButtonState:Int = 0;


  var mouse: MouseAdapter = new MouseAdapter() {
    override def mouseClicked(e: MouseEvent): Unit = {

      var mouseX = e.getX;
      var mouseY = e.getY;


      if (isIn(mouseX, mouseY, 120, posy, WIDTH, HEIGHT)) {
        //easy
        diff = 10
        GameState.State = GameState.Game;
      } else if (isIn(mouseX, mouseY, 310, posy, WIDTH, HEIGHT)) {
        //medium
        diff = 25
        GameState.State = GameState.Game;
      } else if (isIn(mouseX, mouseY, 490, posy, WIDTH, HEIGHT)) {
        //hard
        diff = 50
        GameState.State = GameState.Game;
      } else if (isIn(mouseX, mouseY, 670, posy, WIDTH, HEIGHT)) {
        //hardcore
        diff = 100
        GameState.State = GameState.Game;
      }



    }

  }


  var keyboard: KeyAdapter = new KeyAdapter() {

    override def keyPressed(e: KeyEvent): Unit = {

      if(e.getKeyCode == KeyEvent.VK_F12)
        GameState.State = GameState.Menu;

    }

  }


}
