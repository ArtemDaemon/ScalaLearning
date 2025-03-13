import scala.annotation.tailrec

object Taylor {

  /////////////////////////// Дописать /////////////////////////////////////////////////////////////////////////////////
  def taylorSum(x: Double, e: Double, n: Int = 0, acc: Double = 0.0): (Double, Int) = {
    val term = math.pow(-1, n) * math.
  }

  // Функция ln(x+1)
  private def exactFunction(x: Double): Double = math.log(x + 1)

  // Рекурсивный цикл от xStart до xEnd
  private def processRange(xStart: Double, xEnd: Double, dx: Double, e: Double): Unit = {
    @tailrec
    def loop(x: Double): Unit = {
      if (x <= xEnd) {
        val fx = exactFunction(x)
        val (taylor, iterations) = taylorSum(x, e)
        println(f"x = $x%6.3f | f(x) = $fx%10.6f")
        loop(x + dx)
      }
    }
    loop(xStart)
  }

  def main(args: Array[String]): Unit = {
    // Добавить защиту от дурака ///////////////////////////////////////////////////////////////////////////////////////
    println("Input start X: ")
//    val xStart = scala.io.StdIn.readDouble()
    val xStart = -0.5

    println("Input end X: ")
    val xEnd = 0.5
//    val xEnd = scala.io.StdIn.readDouble()

    println("Input dx: ")
//    val dx = scala.io.StdIn.readDouble()
    val dx = 0.03

    println("Input e: ")
//    val e = scala.io.StdIn.readDouble()
    val e = 0.0001

    processRange(xStart, xEnd, dx, e)
  }
}