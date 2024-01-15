import MinesweeperFunGraphics.bestScores

import java.io.{BufferedOutputStream, FileNotFoundException, FileOutputStream, PrintStream}

object Save {

  // Path of the save file
  var file:String = "res/save.txt";

  // Write highscore in the save file
  def WriterSave(): Unit = {
    val f = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)))
    try {
      for(i:Int <-bestScores.indices)
        f.println(bestScores(i))
      f.close();
    }
  }

  // Read the save file
  def ReadSave(): Unit = {
    try{
      val f = scala.io.Source.fromFile(file)
      val data: Array[String] = f.getLines().toArray
      f.close()
      for (i: Int <- data.indices) {
        bestScores(i) = java.lang.Integer.parseInt(data(i))
      }
    }
    catch{
      case e:FileNotFoundException => {
        val f = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)))
        try {
          for (i: Int <- 0 until 4) {
            bestScores(i) = 216000;
            f.println(216000)
          }
          f.close();
        }
      }
    }
  }
}