package com.kitano.core.crypters.symetric

import com.kitano.core.AlgorithmType
import com.kitano.core.CryptoConstants
import com.kitano.core.CryptoResourceGenerator
import com.kitano.core.exceptions.IncorrectKeyException
import com.kitano.core.internal.ICipher
import java.security.PrivateKey
import java.security.PublicKey
import java.util.Base64
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class AESCrypter : ICipher {

    private fun getCipher(): Cipher {
        return Cipher.getInstance(AlgorithmType.AES.transformation)
    }

    override fun encrypt(input: String, password: String?, publicKey: PublicKey?): String {
        val salt = CryptoResourceGenerator.generateSalt()
        val key = deriveKey(password!!, salt, AlgorithmType.AES)

        val cipher = getCipher()

        val iv = CryptoResourceGenerator.generateIV(AlgorithmType.AES.ivSize)
        val ivSpec = IvParameterSpec(iv)
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec)

        val cipherText = cipher.doFinal(input.toByteArray())

        val combinedSaltIvAndCipherText = ByteArray(salt.size + iv.size + cipherText.size)
        System.arraycopy(salt, 0, combinedSaltIvAndCipherText, 0, salt.size)
        System.arraycopy(iv, 0, combinedSaltIvAndCipherText, salt.size, iv.size)
        System.arraycopy(cipherText, 0, combinedSaltIvAndCipherText, salt.size + iv.size, cipherText.size)

        return Base64.getEncoder().encodeToString(combinedSaltIvAndCipherText)
    }

    override fun decrypt(input: String, password: String?, privateKey: PrivateKey?): String {

        if (password == null) throw IncorrectKeyException("Password cannot be null.")

        val fullCipher = Base64.getDecoder().decode(input)
        val salt = ByteArray(16)
        System.arraycopy(fullCipher, 0, salt, 0, salt.size)

        val key = deriveKey(password, salt, AlgorithmType.AES)

        val cipher = getCipher()
        val iv = ByteArray(AlgorithmType.AES.ivSize)
        System.arraycopy(fullCipher, salt.size, iv, 0, iv.size)
        val ivSpec = IvParameterSpec(iv)

        val cipherText = ByteArray(fullCipher.size - salt.size - iv.size)
        System.arraycopy(fullCipher, salt.size + iv.size, cipherText, 0, cipherText.size)

        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec)

        try {
            val plainText = cipher.doFinal(cipherText)
            return String(plainText)
        } catch (e: BadPaddingException) {
            throw IncorrectKeyException("Sorry, this error often occurs when the password is incorrect. Try again with the correct password.")
        } catch (i: IllegalBlockSizeException) {
            throw IncorrectKeyException("Sorry, this error often occurs when the password is incorrect. Try again with the correct password.")
        }
    }

    /**
     * Derives a key from the given password and salt using PBKDF2WithHmacSHA256
     * to generate a secret key of the given length
     * @param password The password to derive the key from
     * @param salt The salt to use
     * @return The derived key
     */
    private fun deriveKey(
        password: String,
        salt: ByteArray,
        algorithmType: AlgorithmType,
    ): SecretKeySpec {
        val factory = SecretKeyFactory.getInstance(CryptoConstants.PBKDF2_HMAC_SHA256_ALGORITHM)
        val spec = PBEKeySpec(password.toCharArray(), salt, 65536, algorithmType.keyLength)
        val secretKey = factory.generateSecret(spec)
        val keyBytes = secretKey.encoded
        return SecretKeySpec(keyBytes, algorithmType.name)
    }
}
