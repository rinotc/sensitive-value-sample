package sample


object Main extends App {
  val password = Password("abCD123$")
  println(password.hashedPassword)
  println(password.value)
}
