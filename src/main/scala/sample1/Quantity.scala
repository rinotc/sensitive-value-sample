package sample1

/**
 * オンライン書店における冊数
 * @param value
 */
final class Quantity(val value: Int) {

  require(value >= 1 && value <= 200)

  def add(other: Quantity): Quantity = {
    new Quantity(value + other.value)
  }
}
