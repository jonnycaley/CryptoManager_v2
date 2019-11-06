package com.example.cryptomanager_v2.utils

import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable

class StateData<T> {
    @NonNull
    @get:NonNull
    var status: DataStatus? = null
        private set
    @Nullable
    @get:Nullable
    var data: T? = null
        private set
    @Nullable
    @get:Nullable
    var error: Throwable? = null
        private set

    init {
        this.status = DataStatus.CREATED
        this.data = null
        this.error = null
    }

    fun loading(): StateData<T> {
        this.status = DataStatus.LOADING
        this.data = null
        this.error = null
        return this
    }

    fun success(@NonNull data: T): StateData<T> {
        this.status = DataStatus.SUCCESS
        this.data = data
        this.error = null
        return this
    }

    fun error(@NonNull error: Throwable): StateData<T> {
        this.status = DataStatus.ERROR
        this.data = null
        this.error = error
        return this
    }

    fun complete(): StateData<T> {
        this.status = DataStatus.COMPLETE
        return this
    }

    enum class DataStatus {
        CREATED,
        SUCCESS,
        ERROR,
        LOADING,
        COMPLETE
    }
}