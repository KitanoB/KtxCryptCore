package core

import com.kitano.core.AlgorithmType
import com.kitano.core.CrypterFactory
import com.kitano.core.exceptions.IncorrectKeyException
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test class for the RSA algorithm
 * Created by KitanoB on 2018/11/11.
 */
class AlgoRSATest {

    private var keyPair: KeyPair? = null

    private var privateKeyInvalid: PrivateKey? = null

    @BeforeTest
    fun setup() {
        keyPair = generateRsaKeyPair()
        privateKeyInvalid = generateRsaKeyPair().private
    }


    @Test
    fun `when encrypt with rsa same string is  retrieved after decrypt private key`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.RSA)
        val encrypt = crypter.encrypt("This is a test", "password123", keyPair!!.public)
        val decrypt = crypter.decrypt(encrypt, null, keyPair!!.private)
        assertEquals("This is a test", decrypt)
    }

    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt with rsa same string is not retrieved after a corrupted privateKey`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.RSA)
        val encrypt = crypter.encrypt("This is a test", null, keyPair!!.public)
        crypter.decrypt(encrypt, null, privateKeyInvalid)
    }

    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt with rsa same string is not retrieved after a corrupted publicKey`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.RSA)
        val encrypt = crypter.encrypt("This is a test", null, keyPair!!.public)
        crypter.decrypt(encrypt, null, privateKeyInvalid)
    }


    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt with rsa same string is not retrieved after decrypt with nullPrivateKey`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.RSA)
        val encrypt = crypter.encrypt("This is a test", "password123", keyPair!!.public)
        crypter.decrypt(encrypt, "test", null)
    }

    @Test
    fun `when encrypt empty string with rsa and then decrypt returns empty string`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.RSA)
        val encrypt = crypter.encrypt("", null, keyPair!!.public)
        val decrypt = crypter.decrypt(encrypt, null, keyPair!!.private)
        assertEquals("", decrypt)
    }

    @Test
    fun `when encrypt special characters with aes same string is retrieved after decrypt`() {
        val crypter = CrypterFactory.createCrypter(AlgorithmType.RSA)
        val specialString = "这是一个测试"
        val encrypt = crypter.encrypt(specialString, null, keyPair!!.public)
        val decrypt = crypter.decrypt(encrypt, null, keyPair!!.private)
        assertEquals(specialString, decrypt)
    }

    private fun generateRsaKeyPair(): KeyPair {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(2048)
        return keyPairGenerator.generateKeyPair()
    }

}