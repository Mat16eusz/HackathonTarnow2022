package com.example.hackathon2022.utils

fun String?.or(message: String): String {
    if (this.isNullOrEmpty()) {
        return message
    }
    return this
}