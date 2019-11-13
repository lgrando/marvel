package br.com.marvel.extensions

import java.math.BigInteger
import java.security.MessageDigest

private const val MD5 = "MD5"

fun String.md5(): String {
    val md = MessageDigest.getInstance(MD5)
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}