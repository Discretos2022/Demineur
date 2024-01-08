class Case(){

  private var Mine = false;

  var isHide:Boolean = true;
  var numOfAdjacentMine:Int = 0;
  var flag:Boolean = false;
  val DIRECTIONS: Array[Array[Int]] = Array(
    Array(-1, -1), // haut gauche
    Array(-1, 0), // haut
    Array(-1, 1), // haut droite
    Array(0, -1), // gauche
    Array(0, 1), // droite
    Array(1, -1), // bas gauche
    Array(1, 0), // bas
    Array(1, 1) // bas droite
  )


  def setMine(): Unit = {
    Mine = true;
  }

  def setFlag(_flag:Boolean): Unit = {
    flag = _flag;
  }

  def CalculateAdjacentMine(i:Int, j:Int, t:Array[Array[Case]]): Unit = {

    if(!Mine){

      for (direction: Array[Int] <- DIRECTIONS){
        var ni: Int = i + direction(0)
        var nj: Int = j + direction(1)
        if (0 <= ni && ni < t.length && 0 <= nj && nj < t.length){
          if (t(ni)(nj).Mine){
            numOfAdjacentMine += 1
          }
        }

      }
      /*
      if(i + 1 < t.length)
        if(t(i + 1)(j).Mine)
          numOfAdjacentMine += 1;

      if (i + 1 < t.length && j - 1 >= 0)
        if (t(i + 1)(j - 1).Mine)
          numOfAdjacentMine += 1;

      if (j - 1 >= 0)
        if (t(i)(j - 1).Mine)
          numOfAdjacentMine += 1;

      if (i - 1 >= 0 && j - 1 >= 0)
        if (t(i - 1)(j - 1).Mine)
          numOfAdjacentMine += 1;

      if (i - 1 >= 0)
        if (t(i - 1)(j).Mine)
          numOfAdjacentMine += 1;

      if (i - 1 >= 0 && j + 1 < t(i).length)
        if (t(i - 1)(j + 1).Mine)
          numOfAdjacentMine += 1;

      if (j + 1 < t(i).length)
        if (t(i)(j + 1).Mine)
          numOfAdjacentMine += 1;

      if (i + 1 < t.length && j + 1 < t(i).length)
        if (t(i + 1)(j + 1).Mine)
          numOfAdjacentMine += 1;

      */
    }
  }

  def isMine(): Boolean = {
    return Mine;
  }

  override def toString(): String = {
    if (flag)
      return "!"

    if(isHide)
      return "X"

    if(Mine)
      return "▀"

    return numOfAdjacentMine.toString

  }

}
