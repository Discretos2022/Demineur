import java.io.{BufferedOutputStream, BufferedWriter, FileOutputStream, PrintStream, Writer}

object Save {

  var file:String = "/save.txt";

  def WriterSave(): Unit = {

    val f = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)))

    try {

      f.println()

    }

  }


  def ReadSave(): Unit = {

    val f = scala.io.Source.fromFile(file)
    val data:Array[String] = f.getLines().toArray
    f.close()

    //data(0)


  }





}
