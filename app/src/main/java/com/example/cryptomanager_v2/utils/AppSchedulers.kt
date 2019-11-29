package com.example.cryptomanager_v2.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class AppSchedulers(
    open val mainThread: Scheduler,
    open val computation: Scheduler,
    open val io: Scheduler
) {
    companion object {
        fun get(): AppSchedulers {
            return AppSchedulers(
                AndroidSchedulers.mainThread(),
                Schedulers.computation(),
                Schedulers.io()
            )
        }
    }
}