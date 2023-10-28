package com.kitano.core

/**
 * Enum class for the different types of encryption algorithms
 * Created by KitanoB on 2023/10/10.
 */
enum class AlgorithmType(val keyLength: Int, val transformation: String, val ivSize: Int) {
    AES(128, "AES/CBC/PKCS5PADDING", 16),
    DES(64, "DES/CBC/PKCS5PADDING", 8),
    RSA(128, "RSA/ECB/PKCS1PADDING", 16),
    UNKNOWN(0, "", 0);
}