package sample1

import java.util.Currency

case class Money(amount: BigDecimal, currency: Currency)

//class Money(val amount: BigDecimal, val currency: Currency) {
//
//  require(amount >= BigDecimal(0))
//
//  def canEqual(other: Any): Boolean = other.isInstanceOf[Money]
//
//  override def equals(other: Any): Boolean = other match {
//    case that: Money =>
//      (that canEqual this) &&
//        amount == that.amount &&
//        currency == that.currency
//    case _ => false
//  }
//
//  override def hashCode(): Int = {
//    val state = Seq(amount, currency)
//    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
//  }
//
//  override def toString = s"Money(amount=$amount, currency=$currency)"
//}
