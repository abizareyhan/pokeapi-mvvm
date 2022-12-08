package com.abizareyhan.pokedex.domain.base

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<P, R>: CoroutineScope {
    protected val liveData by lazy { MutableLiveData<Resource<R>>() }
    protected var job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    abstract operator fun invoke(parameter: P)

    fun transformToUseCaseLiveData(
        resource: Resource<R>
    ) {
        liveData.postValue(
            resource
        )
    }

    fun getResultLiveData() = liveData
}