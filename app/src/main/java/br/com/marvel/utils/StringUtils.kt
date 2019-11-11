package br.com.marvel.utils

import br.com.marvel.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest

private const val MD5 = "MD5"

fun generateHash(timestamp: String): String {
    val publicKey = BuildConfig.PUBLIC_KEY
    val privateKey = BuildConfig.PRIVATE_KEY

    return md5Hash("$timestamp$privateKey$publicKey")
}

private fun md5Hash(auth: String): String {
    val messageDigest = MessageDigest.getInstance(MD5)
    messageDigest.update(auth.toByteArray(), 0, auth.length)
    val hash = BigInteger(1, messageDigest.digest()).toString(16)
    return hash
}