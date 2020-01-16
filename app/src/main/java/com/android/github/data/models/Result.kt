package com.android.github.data.models

sealed class Result<out T>

class SuccessfulResult<T>(val model: T) : Result<T>()
class ErrorResult<T>(val error: String) : Result<T>()
