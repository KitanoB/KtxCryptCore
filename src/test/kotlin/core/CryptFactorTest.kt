package core

import com.kitano.crypto.internal.enums.AlgorithmType
import com.kitano.crypto.internal.CryptFactory
import com.kitano.crypto.internal.crypters.asymetric.RSACrypt
import com.kitano.crypto.internal.crypters.symetric.AESCrypt
import com.kitano.crypto.internal.crypters.symetric.DESCrypt
import kotlin.test.Test
import kotlin.test.assertTrue

class CryptFactorTest {

    @Test
    fun `createCrypt with algorithm type AES returns a AESCrypt instance`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.AES)
        assertTrue(crypt is AESCrypt)
    }

    @Test
    fun `createCrypt with algorithm type DES returns a DESCrypt instance`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.DES)
        assertTrue(crypt is DESCrypt)
    }

    @Test
    fun `createCrypt er with algorithm type RSA returns a RSACrypt instance`() {
        val crypt = CryptFactory.createCrypt(AlgorithmType.RSA)
        assertTrue(crypt is RSACrypt)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `createCrypt with algorithm type UNKNOWN throws IllegalArgumentException`() {
        CryptFactory.createCrypt(AlgorithmType.UNKNOWN)
    }
}