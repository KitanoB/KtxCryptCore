package com.kitano.core

import com.kitano.core.internal.ICipher
import java.security.PrivateKey
import java.security.PublicKey

class CrypterManager(private val cipher: ICipher) {
    fun encrypt(input: String, password: String?, publicKey: PublicKey?): String =
        cipher.encrypt(input, password, publicKey)

    fun decrypt(input: String, password: String?, privateKey: PrivateKey?): String =
        cipher.decrypt(input, password, privateKey)
}