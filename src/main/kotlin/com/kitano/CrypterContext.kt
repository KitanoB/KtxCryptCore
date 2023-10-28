package com.kitano.core

import com.kitano.core.internal.ICipher
import java.security.PrivateKey
import java.security.PublicKey

/**
 * Context class for the encryption algorithms
 * @see BaseCrypter
 * Created by KitanoB on 2023/10/10.
 */
class CrypterContext(private var strategy: ICipher) {
    fun encrypt(input: String, password: String?, publicKey: PublicKey?): String =
        strategy.encrypt(input, password, publicKey)

    fun decrypt(input: String, password: String?, privateKey: PrivateKey?): String =
        strategy.decrypt(input, password, privateKey)
}
