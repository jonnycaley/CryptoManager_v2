package com.example.cryptomanager_v2.utils

import com.example.cryptomanager_v2.utils.di.AppSchedulers
import io.reactivex.schedulers.TestScheduler

open class TestSchedulers {
    companion object {
        fun get(): AppSchedulers {
            return AppSchedulers(
                TestScheduler(),
                TestScheduler(),
                TestScheduler()
            )
        }
    }
}