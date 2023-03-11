package sample1

class Password(val plainPassword: String) extends Product with Serializable {
  require("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,50}$".r.matches(plainPassword))

  def canEqual(other: Any): Boolean = other.isInstanceOf[Password]

  override def equals(other: Any): Boolean = other match {
    case that: Password =>
      (that canEqual this) &&
        plainPassword == that.plainPassword
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(plainPassword)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def toString = s"Password(plainPassword=$plainPassword)"

  def copy(plainPassword: String = this.plainPassword) = new Password(plainPassword)

  override def productArity: Int = 1

  override def productElement(n: Int): Any = {
    val list = Seq(plainPassword)
    list(n)
  }
}

object Password {
  def apply(plainPassword: String) = new Password(plainPassword)

  def unapply(arg: Password): Option[String] = Option(arg.plainPassword)
}
