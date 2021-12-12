package sample

import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.password.BCryptSha256PasswordHasher

import java.io.{Externalizable, ObjectInput, ObjectOutput}
import scala.util.matching.Regex


final class Password private (@transient underlying: String) extends Externalizable {

  import Password._

  require(isValid(underlying))

  private var consumed = false

  def value: String = synchronized {
    if (consumed) throw new IllegalStateException("Password value has already been consumed")

    val returnValue = underlying
    consumed = true

    returnValue
  }

  def hashedPassword: PasswordInfo = {
    val hasher = new BCryptSha256PasswordHasher
    hasher.hash(underlying)
  }

  override def writeExternal(out: ObjectOutput): Unit = deny

  override def readExternal(in: ObjectInput): Unit = deny

  override def toString = s"Password(underlying=********)"
}

object Password {

  private val passwordRegex: Regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,50}$".r

  private def isValid(value: String) = value.length <= 50 && passwordRegex.matches(value)

  private def deny = throw new UnsupportedOperationException("Serialization of passwords is not allowed")

  def apply(value: String) = new Password(value)
}