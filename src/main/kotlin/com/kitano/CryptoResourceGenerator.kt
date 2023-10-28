package com.kitano.core

import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object CryptoResourceGenerator {
    fun deriveKey(password: String, salt: ByteArray, keyLength: Int): ByteArray {
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val spec = PBEKeySpec(password.toCharArray(), salt, 65536, keyLength)
        return factory.generateSecret(spec).encoded
    }


    fun generateIV(size: Int): ByteArray {
        val iv = ByteArray(size)
        SecureRandom().nextBytes(iv)
        return iv
    }

    fun generateSalt(): ByteArray {
        val salt = ByteArray(16)
        SecureRandom().nextBytes(salt)
        return salt
    }
}

