import scala.annotation.tailrec

object Taylor {
  @tailrec
  private def taylorSum(x: Double, e: Double, n: Int = 0, acc: Double = 0.0): (Double, Int) = {
    val term = (math.pow(-1, n) * math.pow(x, n + 1)) / (n + 1)
    if (math.abs(term) < e) (acc, n)
    else taylorSum(x, e, n + 1, acc + term)
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
        val xStr = x.formatted("%6.3f")
        val fxStr = fx.formatted("%10.6f")
        val taylorStr = taylor.formatted("%10.6f")
        val diffStr = (fx - taylor).abs.formatted("%10.6f")
        val iterStr = iterations.toString

        println(s"x = $xStr | f(x) = $fxStr | Taylor(x) = $taylorStr | Δ = $diffStr | Итераций: $iterStr")

        loop(x + dx)
      }
    }
    loop(xStart)
  }

  def main(args: Array[String]): Unit = {
    def readDouble(prompt: String, validate: Double => Boolean, errorMsg: String): Double = {
      def loop(): Double = {
        println(prompt)
        try {
          val input = scala.io.StdIn.readLine().trim
          val value = input.toDouble
          if (validate(value)) value
          else {
            println(errorMsg)
            loop()
          }
        } catch {
          case _: NumberFormatException =>
            println("Некорректный ввод. Введите число.")
            loop()
        }
      }

      loop()
    }

    val xStart = readDouble(
      "Введите начальное значение X (-1 < X < 1):",
      x => x > -1 && x < 1,
      "Ошибка: X должен быть в диапазоне (-1, 1) (не включая границы)."
    )

    val xEnd = readDouble(
      "Введите конечное значение X (-1 < X < 1):",
      x => x > -1 && x < 1,
      "Ошибка: X должен быть в диапазоне (-1, 1) (не включая границы)."
    )

    val dx = readDouble(
      "Введите шаг dx:",
      _ > 0,
      "Шаг dx должен быть положительным числом."
    )

    val e = readDouble(
      "Введите точность e:",
      _ > 0,
      "Точность e должна быть положительным числом."
    )

    if (xStart > xEnd) {
      println("Начальное значение X не может быть больше конечного. Программа завершена.")
    } else {
      processRange(xStart, xEnd, dx, e)
    }
  }
}