package com.kitano.crypto.internal.interfaces

import com.kitano.crypto.internal.enums.AlgorithmType
import com.kitano.crypto.internal.exceptions.IncorrectKeyException
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.BadPaddingException
import javax.crypto.IllegalBlockSizeException

interface ICryptService {
    @Throws (IncorrectKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
    fun encrypt(input: String, password: String?, algorithmType: AlgorithmType, publicKey: PublicKey?): String
    @Throws (IncorrectKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
    fun decrypt(input: String, password: String?, algorithmType: AlgorithmType, privateKey: PrivateKey?): String

}