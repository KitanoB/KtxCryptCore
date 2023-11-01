package com.kitano.crypto.internal

import com.kitano.crypto.internal.crypters.asymetric.RSACrypt
import com.kitano.crypto.internal.crypters.symetric.AESCrypt
import com.kitano.crypto.internal.crypters.symetric.DESCrypt
import com.kitano.crypto.internal.enums.AlgorithmType
import com.kitano.crypto.internal.interfaces.ICrypt


/**
 * Factory class for the encryption algorithms
 * Created by KitanoB on 2023/10/10.
 */
object CryptFactory {
    /**
     * Creates a new instance of the given algorithm
     * @param algorithmType The algorithm to create
     * @return A new instance of the given algorithm
     */
    fun createCrypt(algorithmType: AlgorithmType): ICrypt {
        return when (algorithmType) {
            AlgorithmType.AES -> AESCrypt()
            AlgorithmType.DES -> DESCrypt()
            AlgorithmType.RSA -> RSACrypt()
            else -> throw IllegalArgumentException("Unknown algorithm type")
        }
    }
}
