package core

import com.kitano.crypto.internal.enums.AlgorithmType
import com.kitano.crypto.internal.CryptFactory
import com.kitano.crypto.internal.exceptions.IncorrectKeyException
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
        val crypt = CryptFactory.createCrypt(AlgorithmType.RSA)
        val encrypt = crypt.encrypt("This is a test", "password123", keyPair!!.public)
        val decrypt = crypt.decrypt(encrypt, null, keyPair!!.private)
        assertEquals("This is a test", decrypt)
    }

    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt with rsa same string is not retrieved after a corrupted privateKey`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.RSA)
        val encrypt = crypt.encrypt("This is a test", null, keyPair!!.public)
        crypt.decrypt(encrypt, null, privateKeyInvalid)
    }

    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt with rsa same string is not retrieved after a corrupted publicKey`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.RSA)
        val encrypt = crypt.encrypt("This is a test", null, keyPair!!.public)
        crypt.decrypt(encrypt, null, privateKeyInvalid)
    }


    @Test(expected = IncorrectKeyException::class)
    fun `when encrypt with rsa same string is not retrieved after decrypt with nullPrivateKey`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.RSA)
        val encrypt = crypt.encrypt("This is a test", "password123", keyPair!!.public)
        crypt.decrypt(encrypt, "test", null)
    }

    @Test
    fun `when encrypt empty string with rsa and then decrypt returns empty string`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.RSA)
        val encrypt = crypt.encrypt("", null, keyPair!!.public)
        val decrypt = crypt.decrypt(encrypt, null, keyPair!!.private)
        assertEquals("", decrypt)
    }

    @Test
    fun `when encrypt special characters with aes same string is retrieved after decrypt`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.RSA)
        val specialString = "这是一个测试"
        val encrypt = crypt.encrypt(specialString, null, keyPair!!.public)
        val decrypt = crypt.decrypt(encrypt, null, keyPair!!.private)
        assertEquals(specialString, decrypt)
    }

    private fun generateRsaKeyPair(): KeyPair {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(2048)
        return keyPairGenerator.generateKeyPair()
    }

}