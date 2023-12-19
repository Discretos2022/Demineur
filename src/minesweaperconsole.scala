import scala.io.StdIn.readLine

object minesweaperconsole extends App{
  var diff : Int = difficulties()
  var game : Array[Array[Int]] = Array.fill(diff, diff)(0)
  var tryX : Int = 0
  var tryY : Int = 0
  printingGameBoard()
  while(!winning_condition()){
    println("where do you want to play")
    print("X : ")
    tryX = readLine().toInt
    print("Y : ")
    tryY = readLine().toInt
    if(tryX > diff || tryY > diff){
      println("error")
    }else{
      playing(tryX-1, tryY-1)
      printingGameBoard()
    }
  }




  def difficulties(): Int = {
    println("What difficulties do you want? 0 : baby, 1 : easy, 2 : medium, 3 : hard, 4 : hardcore")
    var diff: Int = readLine().toInt
    diff match {
      case 0 => diff = 5
      case 1 => diff = 10
      case 2 => diff = 25
      case 3 => diff = 50
      case _ => 10
    }
    return diff
  }
  def winning_condition() : Boolean = {
    var winning : Boolean = false
    // TODO
    return winning
  }
  def playing(x : Int, y : Int) : Unit = {
    game(x)(y) = 1
  }

  def printingGameBoard() : Unit = {
    for(i <- 0 until diff){
      for(j <- 0 until diff){
        print(game(i)(j))
      }
      print("\n")
    }
  }
}

