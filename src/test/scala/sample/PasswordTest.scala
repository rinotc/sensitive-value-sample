package sample

import org.mockito.Mockito.mock
import org.scalatest.wordspec.AnyWordSpec

import java.io.{ObjectInput, ObjectOutput}

class PasswordTest extends AnyWordSpec {

  "PasswordTest" should {
    "value" should {
      "valueを2度参照しようとすると、例外を投げる" in {
        // given(前提条件): Passwordインスタンスを生成し、1度valueを参照する
        val password = Password("abCD123#")
        password.value

        // when(操作): もう一度valueを参照する
        // then(期待する結果): IllegalStateExceptionを投げる
        assertThrows[IllegalStateException] { password.value }
      }
    }

    "writeExternalを実行しようとすると、例外を投げる" in {
      val password = Password("abCD123#")
      val objectOutput = mock(classOf[ObjectOutput])
      assertThrows[UnsupportedOperationException] { password.writeExternal(objectOutput) }
    }

    "readExternal" in {
      val password = Password("abCD123#")
      val objectInput = mock(classOf[ObjectInput])
      assertThrows[UnsupportedOperationException] { password.readExternal(objectInput)}
    }

    "toString" in {
      val password = Password("abCD123#")
      assert(password.toString == "Password(underlying=********)")
    }
  }
}
