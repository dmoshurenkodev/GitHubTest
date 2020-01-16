package com.android.github.ui.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.android.github.data.models.Result
import com.android.github.data.models.SuccessfulResult
import com.android.github.data.models.ErrorResult
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume

inline fun <T> LifecycleOwner.observe(liveData: LiveData<T>, crossinline callback: (T) -> Unit) {
    liveData.observe(this, Observer {
        callback.invoke(it)
    })
}

suspend fun <T : Any> Call<T>.awaitResult(): Result<T> {
    return suspendCancellableCoroutine { continuation ->
        continuation.invokeOnCancellation {
            cancel()
        }
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                continuation.resume(
                    if (response.isSuccessful && body != null) {
                        SuccessfulResult(body)
                    } else {
                        val error = response.errorBody()?.toString() ?: ""
                        ErrorResult(error)
                    }
                )
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resume(ErrorResult(t.localizedMessage))
            }
        })
    }
}