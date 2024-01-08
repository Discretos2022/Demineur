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

      GameState.State match {
      }
      GameState.State match{

        case GameState.Menu => {


          if (isIn(mouseX, mouseY, 120, posy, WIDTH, HEIGHT)) {
            //easy
            diff = 1
            GameState.State = GameState.Game;
          } else if (isIn(mouseX, mouseY, 310, posy, WIDTH, HEIGHT)) {
            //medium
            diff = 2
            GameState.State = GameState.Game;
          } else if (isIn(mouseX, mouseY, 490, posy, WIDTH, HEIGHT)) {
            //hard
            diff = 3
            GameState.State = GameState.Game;
          } else if (isIn(mouseX, mouseY, 670, posy, WIDTH, HEIGHT)) {
            //hardcore
            diff = 4
            GameState.State = GameState.Game;
          }
        }
      }
    }
    override def mouseDragged(e: MouseEvent): Unit = {

      MinesweeperFunGraphics.cursorX = e.getX
      MinesweeperFunGraphics.cursorY = e.getY

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
