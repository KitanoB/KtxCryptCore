package com.kitano.core

import com.kitano.core.crypters.asymetric.RSACrypter
import com.kitano.core.crypters.symetric.AESCrypter
import com.kitano.core.crypters.symetric.DESCrypter
import com.kitano.core.internal.ICipher


/**
 * Factory class for the encryption algorithms
 * @see BaseCrypter
 * Created by KitanoB on 2023/10/10.
 */
object CrypterFactory {
    /**
     * Creates a new instance of the given algorithm
     * @param type The algorithm to create
     * @return A new instance of the given algorithm
     */
    fun createCrypter(algorithmType: AlgorithmType): ICipher {
        return when (algorithmType) {
            AlgorithmType.AES -> AESCrypter()
            AlgorithmType.DES -> DESCrypter()
            AlgorithmType.RSA -> RSACrypter()
            else -> throw IllegalArgumentException("Unknown algorithm type")
        }
    }
}
