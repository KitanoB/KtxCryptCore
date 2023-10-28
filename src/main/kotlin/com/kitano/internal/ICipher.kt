package com.kitano.core.internal

import java.security.PrivateKey
import java.security.PublicKey

interface ICipher {
    fun encrypt(input: String, password: String?, publicKey: PublicKey?): String
    fun decrypt(input: String, password: String?, privateKey: PrivateKey?): String
}