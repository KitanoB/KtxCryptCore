package com.kitano.crypto.internal.interfaces

import com.kitano.crypto.internal.enums.AlgorithmType
import java.security.PrivateKey
import java.security.PublicKey

interface ICryptService {
    fun encrypt(input: String, password: String?, algorithmType: AlgorithmType, publicKey: PublicKey?): String
    fun decrypt(input: String, password: String?, algorithmType: AlgorithmType, privateKey: PrivateKey?): String

}