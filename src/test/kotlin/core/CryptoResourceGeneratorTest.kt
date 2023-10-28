package core

import com.kitano.core.CryptoResourceGenerator
import com.kitano.core.CryptoResourceGenerator.generateSalt
import java.security.NoSuchAlgorithmException
import javax.crypto.SecretKeyFactory
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class CryptoResourceGeneratorTest {

    private val classUnderTest = CryptoResourceGenerator

    @Test
    fun `deriveKey produces consistent output`() {
        val password = "testPassword"
        val salt = generateSalt()
        val keyLength = 256
        val derivedKey1 = CryptoResourceGenerator.deriveKey(password, salt, keyLength)
        val derivedKey2 = CryptoResourceGenerator.deriveKey(password, salt, keyLength)
        assertArrayEquals(derivedKey1, derivedKey2)
    }

    @Test(expected = NoSuchAlgorithmException::class)
    fun `deriveKey throws NoSuchAlgorithmException if invalid algorithm is used`() {
        SecretKeyFactory.getInstance("InvalidAlgorithm")
    }

    @Test
    fun `generateIV produces random output`() {
        val size = 16
        val iv1 = CryptoResourceGenerator.generateIV(size)
        val iv2 = CryptoResourceGenerator.generateIV(size)
        assertFalse(iv1.contentEquals(iv2))
    }

    @Test
    fun `generateIV produces correct length output`() {
        val size = 16
        val iv = CryptoResourceGenerator.generateIV(size)
        assertEquals(size, iv.size)
    }

    @Test
    fun `generateSalt produces random output`() {
        val salt1 = classUnderTest.generateSalt()
        val salt2 = classUnderTest.generateSalt()
        assertFalse(salt1.contentEquals(salt2))
    }

    @Test
    fun `generateSalt produces correct length output`() {
        val expectedSize = 16
        val salt = classUnderTest.generateSalt()
        assertEquals(expectedSize, salt.size)
    }
}
