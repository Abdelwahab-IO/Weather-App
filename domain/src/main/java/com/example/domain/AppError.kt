package com.example.domain

data class AppError(
    val errorName: String,
    val stackTrace: String
) : Throwable()