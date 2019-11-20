package com.example.cryptomanager_v2.utils

data class Resource<out T>(val status: Status, val data: T?) {
    companion object {
        fun <T> success(data: T? = null): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(msg: String): Resource<T> {
            return Resource(Status.ERROR(msg), null)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null)
        }
        fun <T> idle(): Resource<T> {
             return Resource(Status.IDLE, null)
        }
    }
}

sealed class Status {
    object SUCCESS : Status()
    data class ERROR(val reason: String): Status()
    object LOADING: Status()
    object IDLE: Status()
}