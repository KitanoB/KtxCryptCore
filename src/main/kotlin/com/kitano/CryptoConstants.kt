package com.kitano.core


/**
 * Constants for the encryption algorithms
 * Created by KitanoB on 2023/10/10.
 */
object CryptoConstants {
    const val PBKDF2_HMAC_SHA256_ALGORITHM = "PBKDF2WithHmacSHA256"

    const val DES_TRANSFORMATION = "DES/CBC/PKCS5Padding"
    const val DES_KEY_SIZE = 64
    const val DES_IV_SIZE = 8


    const val RSA_TRANSFORMATION = "RSA/ECB/PKCS1Padding"

}