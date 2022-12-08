package com.abizareyhan.pokedex.domain.base

import androidx.annotation.Keep
import java.lang.Exception

@Keep
open class Resource<out T> constructor(
    private val _data: T?,
    val status: ResourceState,
    val message: String?,
    val exception: Exception?
) {
    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(data, ResourceState.SUCCESS, null, null)
        }

        fun <T> failed(exception: Exception?): Resource<T> {
            return Resource(null, ResourceState.FAILED, exception?.message, exception,)
        }

        fun <T> failed(message: String?, exception: Exception?): Resource<T> {
            return Resource(null, ResourceState.FAILED, message, exception,)
        }
    }

    fun <C> convertError(): Resource<C> {
        return if(exception != null) {
            failed(this.exception)
        } else {
            failed(this.message, null)
        }
    }

    val data: T
        get() = _data ?: throw java.lang.NullPointerException()
}