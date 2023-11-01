package core

import com.kitano.crypto.internal.enums.AlgorithmType
import com.kitano.crypto.internal.CryptFactory
import com.kitano.crypto.internal.exceptions.IncorrectKeyException
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
        val crypt = CryptFactory.createCrypt(AlgorithmType.AES)
        val password = "1234567890123456"
        val plainText = "This is a test"
        val encryptedText = crypt.encrypt(plainText, password, null)
        val decryptedText = crypt.decrypt(encryptedText, password, null)
        assertEquals(plainText, decryptedText)

    }

    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt with aes same string is not retrieved after decrypt with wrong password`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.AES)
        val password = "123456"
        val plainText = "This is a test"
        val encryptedText = crypt.encrypt(plainText, password, null)
        val decryptedText = crypt.decrypt(encryptedText, "test", null)
        assertNotEquals(encryptedText, decryptedText)
    }

    @Test(expected = IncorrectKeyException::class)
    fun `when decrypt with different key throws exception`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.AES)
        val password1 = "1234567890123456"
        val password2 = "6543210987654321"
        val plainText = "This is a test"
        val encryptedText = crypt.encrypt(plainText, password1, null)
        crypt.decrypt(encryptedText, password2, null)
    }

    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt long string with aes does throw`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.AES)
        val password = "1234567890123456"
        val longString = "a".repeat(10_000)
        val encrypted = crypt.encrypt(longString, password, null)
        val decrypted = crypt.decrypt(longString, password, null)
        assertEquals(encrypted, decrypted)
    }

    @Test
    fun `when encrypt empty string with aes and then decrypt returns empty string`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.AES)
        val password = "1234567890123456"
        val plainText = ""
        val encryptedText = crypt.encrypt(plainText, password, null)
        val decryptedText = crypt.decrypt(encryptedText, password, null)
        assertEquals(plainText, decryptedText)
    }

    @Test
    fun `when encrypt special characters with aes same string is retrieved after decrypt`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.AES)
        val password = "1234567890123456"
        val specialString = "这是一个测试"
        val encryptedText = crypt.encrypt(specialString, password, null)
        val decryptedText = crypt.decrypt(encryptedText, password, null)
        assertEquals(specialString, decryptedText)
    }

    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt with aes same string is not retrieved after decrypt null password`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.AES)
        val password = "123456"
        val plainText = "This is a test"
        val encryptedText = crypt.encrypt(plainText, password, null)
        crypt.decrypt(encryptedText, "null", null)
    }

}