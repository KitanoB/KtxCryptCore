package com.kitano.crypto.internal.interfaces

import java.security.PrivateKey
import java.security.PublicKey

interface ICrypt {
    fun encrypt(input: String, password: String?, publicKey: PublicKey?): String
    fun decrypt(input: String, password: String?, privateKey: PrivateKey?): String
}