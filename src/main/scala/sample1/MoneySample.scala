package sample1

import java.util.{Currency, Locale}

object MoneySample extends App {
  val 花子の3千円 = new Money(BigDecimal(3_000), Currency.getInstance(Locale.JAPAN))
  val 隆の３千円  = new Money(BigDecimal(3_000), Currency.getInstance(Locale.JAPAN))

  if (花子の3千円 == 隆の３千円) println("一緒!!") else println("違う!")
}
