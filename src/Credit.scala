import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.Color

// ALT + 159 : ƒ

object Credit {

  var color: Float = 0;

  // Display text of credit
  def display(wind: FunGraphics): Unit = {

    color += 0.01f;

    Writer.Write("MineSweeper version 1.0 beta", 210, 30, Color.BLACK, Color.getHSBColor(color, 1, 1), 30, wind)

    Writer.Write("Developer : Natasha Landry, Joshua SIEDEL", 10, 100, Color.BLACK, Color.WHITE, 20, wind)

    Writer.Write("Graphic Designer : Natasha Landry, Joshua SIEDEL", 10, 150, Color.BLACK, Color.WHITE, 20, wind)

    Writer.Write("Tester : Natasha Landry, Joshua SIEDEL", 10, 200, Color.BLACK, Color.WHITE, 20, wind)

    Writer.Write("Micro-projet ISC HES-SO Valais-Wallis", 10, 250, Color.BLACK, Color.WHITE, 20, wind)

    Writer.Write2("Infiniteright ∞ 2024 LANDRY SIEDEL™  ", 650, 600 - 15, "Consolas", Color.getHSBColor(color, 1, 1), Color.getHSBColor(color + 0.5f, 1, 1), 20, wind)

    Writer.Write("PRESS <F12> TO RETURN MAIN MENU", 5, 600 - 5, Color.BLACK, Color.WHITE, 15, wind)
  }
}
