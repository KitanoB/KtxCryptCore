package com.kitano.core.crypters.asymetric

import com.kitano.core.CryptoConstants
import com.kitano.core.exceptions.IncorrectKeyException
import com.kitano.core.internal.ICipher
import java.security.GeneralSecurityException
import java.security.PrivateKey
import java.security.PublicKey
import java.util.Base64
import javax.crypto.BadPaddingException
import javax.crypto.Cipher

class RSACrypter : ICipher {

    override fun encrypt(input: String, password: String?, publicKey: PublicKey?): String {
        try {
            val cipher = Cipher.getInstance(CryptoConstants.RSA_TRANSFORMATION)
            cipher.init(Cipher.ENCRYPT_MODE, publicKey)

            val encryptedBytes = cipher.doFinal(input.toByteArray())
            return Base64.getEncoder().encodeToString(encryptedBytes)
        } catch (e: GeneralSecurityException) {
            throw IncorrectKeyException("Failed to encrypt using the provided public key.")
        }
    }


    override fun decrypt(input: String, password: String?, privateKey: PrivateKey?): String {
        try {
            val cipher = Cipher.getInstance(CryptoConstants.RSA_TRANSFORMATION)
            cipher.init(Cipher.DECRYPT_MODE, privateKey)

            val decodedBytes = Base64.getDecoder().decode(input)
            val decryptedBytes = cipher.doFinal(decodedBytes)
            return String(decryptedBytes)
        } catch (e: BadPaddingException) {
            throw IncorrectKeyException("Failed to decrypt. Possibly due to an incorrect private key.")
        } catch (e: GeneralSecurityException) {
            throw IncorrectKeyException("Failed to decrypt using the provided private key.")
        }
    }
}



