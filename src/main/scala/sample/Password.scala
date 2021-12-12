package sample

import java.io.{Externalizable, ObjectInput, ObjectOutput}
import scala.util.matching.Regex

/**
 * パスワードクラス
 *
 * [[https://book.mynavi.jp/ec/products/detail/id=124056 セキュア・バイ・デザイン]]
 * のRead-Onceオブジェクトところを読んで試しにScalaで作成。
 *
 * @note 章を参考に同じような実装をしてみたが、プレーンパスワードのvalueを外に出すのではなく、
 *       ハッシュ化する処理も入れて、プレーンパスワードは内部だけで扱う方が安全なような気もする。
 *       JVMのメモリに残らないようにといった話もあるので、よくわからないが。。
 * @param underlying プレーンパスワード
 */
final class Password private (                // 継承させない
    @transient private var underlying: String // シリアライズ不可
) extends Externalizable { // シリアライズが行われる際の振る舞いを制御

  import Password._

  require(isValid(underlying))

  private var consumed = false

  def value: String = synchronized { // 1度に1スレッドからしかアクセスできない
    if (consumed) throw new IllegalStateException("Password value has already been consumed")

    val returnValue = underlying
    consumed = true // 読み込まれたことを伝えるフラグ
    underlying = "" // 保持していた値を削除

    returnValue
  }

  override def writeExternal(out: ObjectOutput): Unit = deny // シリアライズされそうになると、例外をスローする

  override def readExternal(in: ObjectInput): Unit = deny // シリアライズされそうになると例外をスローする

  override def toString = s"Password(underlying=********)" // 意図しない漏洩を防ぐ
}

object Password {

  private val passwordRegex: Regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,50}$".r

  private def isValid(value: String) = value.length <= 50 && passwordRegex.matches(value)

  private def deny = throw new UnsupportedOperationException("Serialization of passwords is not allowed")

  def apply(value: String) = new Password(value)
}
