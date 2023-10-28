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
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class DESCrypter : ICipher {

    private val cryptoResourceGenerator = CryptoResourceGenerator

    override fun encrypt(input: String, password: String?, publicKey: PublicKey?): String {
        val salt = cryptoResourceGenerator.generateSalt()
        val keySpec = SecretKeySpec(
            cryptoResourceGenerator.deriveKey(password!!, salt, CryptoConstants.DES_KEY_SIZE),
            AlgorithmType.DES.name
        )
        val ivSpec = IvParameterSpec(cryptoResourceGenerator.generateIV(CryptoConstants.DES_IV_SIZE))

        val cipher = Cipher.getInstance(CryptoConstants.DES_TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)

        val cipherText = cipher.doFinal(input.toByteArray())

        // Combine IV and encrypted part for easier decryption process
        val combinedSaltIvAndCipherText = ByteArray(salt.size + ivSpec.iv.size + cipherText.size)
        System.arraycopy(salt, 0, combinedSaltIvAndCipherText, 0, salt.size)
        System.arraycopy(ivSpec.iv, 0, combinedSaltIvAndCipherText, salt.size, ivSpec.iv.size)
        System.arraycopy(cipherText, 0, combinedSaltIvAndCipherText, salt.size + ivSpec.iv.size, cipherText.size)

        return Base64.getEncoder().encodeToString(combinedSaltIvAndCipherText)
    }

    override fun decrypt(input: String, password: String?, privateKey: PrivateKey?): String {

        if (password == null) throw IncorrectKeyException("Password cannot be null.")

        val decodedInput = Base64.getDecoder().decode(input)

        val salt = ByteArray(16)
        System.arraycopy(decodedInput, 0, salt, 0, salt.size)
        val iv = ByteArray(CryptoConstants.DES_IV_SIZE)
        System.arraycopy(decodedInput, salt.size, iv, 0, iv.size)
        val cipherText = ByteArray(decodedInput.size - salt.size - iv.size)
        System.arraycopy(decodedInput, salt.size + iv.size, cipherText, 0, cipherText.size)

        val keySpec = SecretKeySpec(
            cryptoResourceGenerator.deriveKey(password, salt, CryptoConstants.DES_KEY_SIZE),
            AlgorithmType.DES.name
        )
        val ivSpec = IvParameterSpec(iv)

        val cipher = Cipher.getInstance(CryptoConstants.DES_TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)

        try {
            val plainText = cipher.doFinal(cipherText)
            return String(plainText)
        } catch (e: BadPaddingException) {
            throw IncorrectKeyException("Sorry, this error often occurs when the password is incorrect. Try again with the correct password.")
        } catch (i: IllegalBlockSizeException) {
            throw IncorrectKeyException("Sorry, this error often occurs when the password is incorrect. Try again with the correct password.")
        }
    }
}
