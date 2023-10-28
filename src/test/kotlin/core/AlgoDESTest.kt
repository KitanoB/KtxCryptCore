package core

import com.kitano.core.AlgorithmType
import com.kitano.core.CrypterFactory
import com.kitano.core.exceptions.IncorrectKeyException
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
        val crypter = CrypterFactory.createCrypter(AlgorithmType.DES)
        val password = "123456"
        val plainText = "This is a test"
        val encryptedText = crypter.encrypt(plainText, password, null)
        val decryptedText = crypter.decrypt(encryptedText, password, null)
        assertEquals(plainText, decryptedText)
    }

    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt with des same string is not retrieved after decrypt with wrong password`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.DES)
        val password = "123456"
        val plainText = "This is a test"
        val encryptedText = crypter.encrypt(plainText, password, null)
        val decryptedText = crypter.decrypt(encryptedText, "test", null)
        assertNotEquals(encryptedText, decryptedText)
    }

    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt with des same string is not retrieved after decrypt with wrong password and wrong key`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.DES)
        val password = "123456"
        val plainText = "This is a test"
        val encryptedText = crypter.encrypt(plainText, password, null)
        val decryptedText = crypter.decrypt(encryptedText, "test", null)
        if (encryptedText != decryptedText) {
            throw IncorrectKeyException("The decrypted text is not the same as the encrypted text")
        }
    }


    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt with des string is not retrieved after decrypt with null password`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.DES)
        val password = "123456"
        val plainText = "This is a test"
        val encryptedText = crypter.encrypt(plainText, password, null)
        crypter.decrypt(encryptedText, null, null)
    }

    @Test
    fun `when encrypt special characters with des same string is retrieved after decrypt`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.DES)
        val password = "1234567890123456"
        val specialString = "这是一个测试"
        val encryptedText = crypter.encrypt(specialString, password, null)
        val decryptedText = crypter.decrypt(encryptedText, password, null)
        assertEquals(specialString, decryptedText)
    }

    @Test
    fun `when encrypt empty string with des and then decrypt returns empty string`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.DES)
        val password = "1234567890123456"
        val plainText = ""
        val encryptedText = crypter.encrypt(plainText, password, null)
        val decryptedText = crypter.decrypt(encryptedText, password, null)
        assertEquals(plainText, decryptedText)
    }


    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt long string with aes does throw`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.DES)
        val password = "1234567890123456"
        val longString = "a".repeat(10_000)
        val encrypted = crypter.encrypt(longString, password, null)
        val decrypted = crypter.decrypt(longString, password, null)
        assertEquals(encrypted, decrypted)
    }

}