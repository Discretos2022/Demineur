import MinesweeperFunGraphics.bestScores

import java.io.{BufferedOutputStream, FileOutputStream, PrintStream}

object Save {

  var file:String = "res/save.txt";

  def WriterSave(): Unit = {

    val f = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)))

    try {
      for(i:Int <-0 until bestScores.length)
        f.println(bestScores(i))

      f.close();
    }

  }

  def ReadSave(): Unit = {

    try{
      val f = scala.io.Source.fromFile(file)
      val data: Array[String] = f.getLines().toArray
      f.close()

      for (i: Int <- 0 until data.length) {

        bestScores(i) = java.lang.Integer.parseInt(data(i))

        println(Integer.getInteger(data(i)))

      }

    }

  }

}
