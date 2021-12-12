package sample


object Main extends App {
  val password = Password("abCD123$")
  println(password.value)
  println(password.value) // throws IllegalStateException
}
