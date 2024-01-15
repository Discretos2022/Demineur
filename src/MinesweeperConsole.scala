
import scala.io.StdIn.{readInt, readLine}
import scala.util.Random

object MinesweeperConsole extends App{
  //variable
  var diff : Int = difficulties()
  var game : Array[Array[Case]] = Array.ofDim(diff, diff)
  var tryX : Int = 0
  var tryY : Int = 0
  var perdu : Boolean = false
  var numOfMine:Int = 10;

  InitGame(tryX, tryY);
  printingGameBoard()

  while(!perdu){
    println("where do you want to play")
    print("X : ")
    tryX = readLine().toInt
    print("Y : ")
    tryY = readLine().toInt
    if(tryX > diff || tryY > diff){
      println("error")
    }else{
      println("flag? 1 : y, 0 : n")
      var flag: Int = readInt()

      if (flag == 1){
        playing(tryX-1, tryY-1, true)
      }else{
        playing(tryX-1, tryY-1)
      }

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

  def ending_condition() : Boolean = {
    var winning : Boolean = false
    if(perdu){
      winning = false
      println("perdu")
    }else{
      winning = true
      println("gagné")
    }
    return winning
  }

  def playing(x : Int, y : Int, f : Boolean = false) : Unit = {
    game(x)(y).isHide = false
    if(game(x)(y).isMine() && !f){
      perdu = true
      ending_condition()
    }
    else{
      if(f){
        game(x)(y).flag
      }
      else if(game(x)(y).numOfAdjacentMine == 0)
        DiscoverAdjacentCase(x, y);
    }
  }

  def printingGameBoard() : Unit = {
    for(i <- 0 until diff){
      for(j <- 0 until diff){
        print(game(i)(j).toString() + "  ")
      }
      print("\n")
    }
  }


  def DiscoverAdjacentCase(x: Int, y:Int): Unit = {

    for(i <- x - 1 to x + 1) {
      for (j <- y - 1 to y + 1) {

        if(i >= 0 && i < game.length && j >= 0 && j < game(0).length) {

          if(game(i)(j).isHide){
            game(i)(j).isHide = false;
            if (game(i)(j).numOfAdjacentMine == 0) {
              DiscoverAdjacentCase(i, j)
            }
          }
        }
      }
    }
  }


  def InitGame(selectedX:Int, selectedY:Int): Unit = {

    println("where do you want to play")
    print("X : ")
    var X = readLine().toInt
    print("Y : ")
    var Y = readLine().toInt
    if (X > diff || Y > diff)
      println("error")

    for(i <- game.indices){
      for (j <- game(i).indices) {
        game(i)(j) = new Case();
      }
    }

    for(r <- 1 to numOfMine){

      var randomX = Random.nextInt(diff)
      var randomY = Random.nextInt(diff)

      if(!game(randomX)(randomY).isMine() && randomX != selectedX && randomY != selectedY)
        game(randomX)(randomY).setMine()
      else
        setMine()

    }

    for (i <- game.indices) {
      for (j <- game(i).indices) {
        game(i)(j).CalculateAdjacentMine(i, j, game)
      }
    }

    playing(X, Y)

  }

  def setMine(): Unit = {

    var randomX = Random.nextInt(diff)
    var randomY = Random.nextInt(diff)

    if (!game(randomX)(randomY).isMine())
      game(randomX)(randomY).setMine()
    else
      setMine()

  }

}