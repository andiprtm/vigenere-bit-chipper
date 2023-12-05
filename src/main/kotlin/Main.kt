import kotlin.experimental.xor

fun main() {
    // Input plaintext and key
    print("Masukkan plaintext: ")
    val plaintext = readln().toByteArray()
    println("bit plaintext: ${bytesToBitString(plaintext)}")
    print("Masukkan key: ")
    val key = readln().toByteArray()

    // Ensure the key has the same length as plaintext
    val extendedKey = extendKey(key, plaintext.size)
    println("key: ${bytesToBitString(extendedKey)}")

    // Encrypt plaintext
    val encryptedText = encrypt(plaintext, extendedKey)
    println("Hasil Enkripsi: ${bytesToBitString(encryptedText)}")

    // Decrypt ciphertext
    val decryptedText = decrypt(encryptedText, extendedKey)
    println("Hasil Dekripsi: ${String(decryptedText)}")

}

// Function to extend the key to match the length of plaintext
fun extendKey(key: ByteArray, length: Int): ByteArray {
    val extendedKey = ByteArray(length)
    var keyIndex = 0

    for (i in 0..<length) {
        extendedKey[i] = key[keyIndex]
        keyIndex = (keyIndex + 1) % key.size
    }

    return extendedKey
}

// Function to encrypt plaintext using Vigenere Cipher
fun encrypt(plaintext: ByteArray, key: ByteArray): ByteArray {
    val encryptedText = ByteArray(plaintext.size)

    for (i in plaintext.indices) {
        encryptedText[i] = (plaintext[i] xor key[i])
    }

    return encryptedText
}

// Function to decrypt ciphertext using Vigenere Cipher
fun decrypt(ciphertext: ByteArray, key: ByteArray): ByteArray {
    val decryptedText = ByteArray(ciphertext.size)

    for (i in ciphertext.indices) {
        decryptedText[i] = (ciphertext[i] xor key[i])
    }

    return decryptedText
}

fun bytesToBitString(bytes: ByteArray): String {
    val stringBuilder = StringBuilder()

    for (byte in bytes) {
        for (i in 7 downTo 0) {
            val bit = (byte.toInt() shr i) and 1
            stringBuilder.append(bit)
        }
    }

    return stringBuilder.toString()
}
