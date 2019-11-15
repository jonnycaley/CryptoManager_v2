package com.example.cryptomanager_v2.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T? = null): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String?): Resource<T> {
            return Resource(Status.ERROR, null, msg)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
        fun <T> idle(): Resource<T> {
             return Resource(Status.IDLE, null, null)
        }
    }
}

sealed class Status {
    object SUCCESS : Status()
    object ERROR: Status()
    object LOADING: Status()
    object IDLE: Status()
}