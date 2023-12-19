class Case(){

  private var Mine = false;

  var isHide:Boolean = true;
  var numOfAdjacentMine:Int = 0;
  var flag:Boolean = false;


  def setMine(): Unit = {
    Mine = true;
  }

  def setFlag(_flag:Boolean): Unit = {
    flag = _flag;
  }

  def CalculateAdjacentMine(i:Int, j:Int, t:Array[Array[Case]]): Unit = {

    if(!Mine){

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

    }
  }

  def isMine(): Boolean = {
    return Mine;
  }

  override def toString(): String = {

    if(isHide)
      return "X"

    if(Mine)
      return "▀"

    return numOfAdjacentMine.toString

  }

}
