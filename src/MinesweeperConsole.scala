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

  InitGame();
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
            if (game(i)(j).numOfAdjacentMine == 0) {
              game(i)(j).isHide = false;
              DiscoverAdjacentCase(i, j)
            }
            else if(game(i)(j).numOfAdjacentMine != 0) {
              game(i)(j).isHide = false;
            }
          }

        }

      }
    }

  }


  def InitGame(): Unit = {

    for(i <- 0 until game.length){
      for (j <- 0 until game(i).length) {
        game(i)(j) = new Case();
      }
    }

    for(r <- 1 to numOfMine){

      var randomX = Random.nextInt(diff)
      var randomY = Random.nextInt(diff)

      if(!game(randomX)(randomY).isMine())
        game(randomX)(randomY).setMine()
      else
        setMine()

    }

    for (i <- 0 until game.length) {
      for (j <- 0 until game(i).length) {
        game(i)(j).CalculateAdjacentMine(i, j, game)
      }
    }
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