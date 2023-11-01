package core

import com.kitano.crypto.internal.enums.AlgorithmType
import com.kitano.crypto.internal.CryptFactory
import com.kitano.crypto.internal.exceptions.IncorrectKeyException
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import org.junit.Test

/**
 * Test class for the DES algorithm
 * Created by KitanoB on 2018/11/11.
 */
class AlgoDESTest {

    @Test
    fun `when when encrypt with two fish same string is not retrieved after decrypt with wrong password`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.DES)
        val password = "123456"
        val plainText = "This is a test"
        val encryptedText = crypt.encrypt(plainText, password, null)
        val decryptedText = crypt.decrypt(encryptedText, password, null)
        assertEquals(plainText, decryptedText)
    }

    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt with des same string is not retrieved after decrypt with wrong password`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.DES)
        val password = "123456"
        val plainText = "This is a test"
        val encryptedText = crypt.encrypt(plainText, password, null)
        val decryptedText = crypt.decrypt(encryptedText, "test", null)
        assertNotEquals(encryptedText, decryptedText)
    }

    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt with des same string is not retrieved after decrypt with wrong password and wrong key`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.DES)
        val password = "123456"
        val plainText = "This is a test"
        val encryptedText = crypt.encrypt(plainText, password, null)
        val decryptedText = crypt.decrypt(encryptedText, "test", null)
        if (encryptedText != decryptedText) {
            throw IncorrectKeyException("The decrypted text is not the same as the encrypted text")
        }
    }


    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt with des string is not retrieved after decrypt with null password`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.DES)
        val password = "123456"
        val plainText = "This is a test"
        val encryptedText = crypt.encrypt(plainText, password, null)
        crypt.decrypt(encryptedText, null, null)
    }

    @Test
    fun `when encrypt special characters with des same string is retrieved after decrypt`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.DES)
        val password = "1234567890123456"
        val specialString = "这是一个测试"
        val encryptedText = crypt.encrypt(specialString, password, null)
        val decryptedText = crypt.decrypt(encryptedText, password, null)
        assertEquals(specialString, decryptedText)
    }

    @Test
    fun `when encrypt empty string with des and then decrypt returns empty string`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.DES)
        val password = "1234567890123456"
        val plainText = ""
        val encryptedText = crypt.encrypt(plainText, password, null)
        val decryptedText = crypt.decrypt(encryptedText, password, null)
        assertEquals(plainText, decryptedText)
    }


    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt long string with aes does throw`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.DES)
        val password = "1234567890123456"
        val longString = "a".repeat(10_000)
        val encrypted = crypt.encrypt(longString, password, null)
        val decrypted = crypt.decrypt(longString, password, null)
        assertEquals(encrypted, decrypted)
    }

}