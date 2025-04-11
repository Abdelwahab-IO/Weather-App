package com.example.domain

data class AppError(
    override val message: String,
    val stackTrace: String
) : Throwable()