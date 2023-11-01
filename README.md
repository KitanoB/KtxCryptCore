# KtxCryptoCore  ![Kotlin](https://img.shields.io/badge/KOTLIN-1.9-green)

KtxCryptoCore is a Kotlin-based library designed to provide robust and efficient string encryption and decryption functionalities. It supports a variety of algorithms including AES, DES, and RSA. This library stands out for its simplicity and educational purpose, aiming to demonstrate encryption and decryption processes without the need for external libraries or internet requests.

## Getting Started

### Prerequisites

Ensure you have the following installed on your system:

- Java JDK 17 or later
- Kotlin version 1.9.10

### Installation

To use KtxCryptoCore in your project, add the following dependencies to your `build.gradle` file:

```gradle
implementation("com.kitano.crypto:ktxCryptoCore:0.0.1")
```

## Features ![Algorithm](https://img.shields.io/badge/Algorithm-RSA/AES/DES-red) 

- **String Encryption and Decryption**: Easily encrypt or decrypt strings using AES, DES, or RSA.
- **No External Dependencies**: KtxCryptoCore doesn't rely on any external libraries.
- **Offline Functionality**: All encryption and decryption operations are performed locally, with no internet requests.
- **Educational Tool**: A great resource for learning about different encryption algorithms and their implementations in Kotlin.

## Usage

To use KtxCryptoCore, simply instantiate the class corresponding to the encryption algorithm you wish to use (AES, DES, RSA), and call the `encrypt` or `decrypt` method with the appropriate parameters.

Example:

```kotlin
val encryptor = AES()
val encryptedText = encryptor.encrypt("Hello, World!", "password")
val decryptedText = encryptor.decrypt(encryptedText, "password")
println(decryptedText)  // Output: Hello, World!
```

# Disclaimer

This software, "KtxCryptoCore", was created for learning purposes and is provided "as is", without any warranty. The author holds no responsibility for how this software is used or any consequences arising from its use.

# License

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above disclaimer and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## Contact

If you have any questions or suggestions, feel free to reach out to us at [kitanoblog@proton.me](mailto:kitanoblog@proton.me).
