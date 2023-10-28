package core

import com.kitano.core.AlgorithmType
import com.kitano.core.CrypterFactory
import com.kitano.core.exceptions.IncorrectKeyException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/**
 * Test class for the AES algorithm
 * Created by KitanoB on 2018/11/11.
 */
class AlgoAESTest {

    @Test
    fun `when encrypt with aes same string is retrieved after decrypt`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.AES)
        val password = "1234567890123456"
        val plainText = "This is a test"
        val encryptedText = crypter.encrypt(plainText, password, null)
        val decryptedText = crypter.decrypt(encryptedText, password, null)
        assertEquals(plainText, decryptedText)

    }

    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt with aes same string is not retrieved after decrypt with wrong password`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.AES)
        val password = "123456"
        val plainText = "This is a test"
        val encryptedText = crypter.encrypt(plainText, password, null)
        val decryptedText = crypter.decrypt(encryptedText, "test", null)
        assertNotEquals(encryptedText, decryptedText)
    }

    @Test(expected = IncorrectKeyException::class)
    fun `when decrypt with different key throws exception`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.AES)
        val password1 = "1234567890123456"
        val password2 = "6543210987654321"
        val plainText = "This is a test"
        val encryptedText = crypter.encrypt(plainText, password1, null)
        crypter.decrypt(encryptedText, password2, null)
    }

    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt long string with aes does throw`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.AES)
        val password = "1234567890123456"
        val longString = "a".repeat(10_000)
        val encrypted = crypter.encrypt(longString, password, null)
        val decrypted = crypter.decrypt(longString, password, null)
        assertEquals(encrypted, decrypted)
    }

    @Test
    fun `when encrypt empty string with aes and then decrypt returns empty string`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.AES)
        val password = "1234567890123456"
        val plainText = ""
        val encryptedText = crypter.encrypt(plainText, password, null)
        val decryptedText = crypter.decrypt(encryptedText, password, null)
        assertEquals(plainText, decryptedText)
    }

    @Test
    fun `when encrypt special characters with aes same string is retrieved after decrypt`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.AES)
        val password = "1234567890123456"
        val specialString = "这是一个测试"
        val encryptedText = crypter.encrypt(specialString, password, null)
        val decryptedText = crypter.decrypt(encryptedText, password, null)
        assertEquals(specialString, decryptedText)
    }

    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt with aes same string is not retrieved after decrypt null password`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.AES)
        val password = "123456"
        val plainText = "This is a test"
        val encryptedText = crypter.encrypt(plainText, password, null)
        crypter.decrypt(encryptedText, "null", null)
    }

}