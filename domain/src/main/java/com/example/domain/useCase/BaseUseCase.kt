package com.example.domain.useCase

import com.example.domain.AppError
import com.example.domain.repositories.Repository

abstract class BaseUseCase<T>(protected val repository: Repository) {
    protected suspend fun unFoldRepoCall(call: suspend () -> T): T {
        try {
            return call.invoke()
        } catch (throwable: Throwable) {
            throw AppError(
                message = throwable.localizedMessage,
                stackTrace = throwable.stackTraceToString()
            )
        }

    }
}