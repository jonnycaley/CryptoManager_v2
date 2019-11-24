package com.example.cryptomanager_v2.utils

import com.example.cryptomanager_v2.utils.di.AppSchedulers
import io.reactivex.schedulers.Schedulers

open class TestAppSchedulers {
    companion object {
        fun get(): AppSchedulers {
            return AppSchedulers(
                Schedulers.trampoline(),
                Schedulers.trampoline(),
                Schedulers.trampoline()
            )
        }
    }
}