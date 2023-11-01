package com.kitano.crypto

import com.kitano.crypto.internal.CryptFactory
import com.kitano.crypto.internal.enums.AlgorithmType
import com.kitano.crypto.internal.interfaces.ICryptService
import java.security.PrivateKey
import java.security.PublicKey

class CryptService : ICryptService {
    override fun encrypt(
        input: String,
        password: String?,
        algorithmType: AlgorithmType,
        publicKey: PublicKey?,
    ): String {
        val crypt = CryptFactory.createCrypt(algorithmType)
        return crypt.encrypt(input, password, publicKey)
    }

    override fun decrypt(
        input: String,
        password: String?,
        algorithmType: AlgorithmType,
        privateKey: PrivateKey?,
    ): String {
        val crypt = CryptFactory.createCrypt(algorithmType)
        return crypt.decrypt(input, password, privateKey)
    }
}