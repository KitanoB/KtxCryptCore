package com.kitano.crypto.internal.crypters.asymetric

import com.kitano.crypto.internal.constants.CryptoConstants
import com.kitano.crypto.internal.exceptions.IncorrectKeyException
import com.kitano.crypto.internal.interfaces.ICrypt
import java.security.GeneralSecurityException
import java.security.PrivateKey
import java.security.PublicKey
import java.util.Base64
import javax.crypto.BadPaddingException
import javax.crypto.Cipher

class RSACrypt : ICrypt {

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



