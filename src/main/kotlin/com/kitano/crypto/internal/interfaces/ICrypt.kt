package com.kitano.crypto.internal.interfaces

import com.kitano.crypto.internal.exceptions.IncorrectKeyException
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.BadPaddingException
import javax.crypto.IllegalBlockSizeException

interface ICrypt {
    @Throws (IncorrectKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
    fun encrypt(input: String, password: String?, publicKey: PublicKey?): String

    @Throws (IncorrectKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
    fun decrypt(input: String, password: String?, privateKey: PrivateKey?): String
}