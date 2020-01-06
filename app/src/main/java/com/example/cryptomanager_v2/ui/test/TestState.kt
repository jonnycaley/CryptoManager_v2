package com.example.cryptomanager_v2.ui.test

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized

data class TestState(val response: Async<String> = Uninitialized): MvRxState