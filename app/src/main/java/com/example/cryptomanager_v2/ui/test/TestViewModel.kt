package com.example.cryptomanager_v2.ui.test

//class TestViewModel @AssistedInject constructor(
//    @Assisted state: TestState,
//    private val exchangeRatesApi: ExchangeRatesApi
//): BaseViewModel<TestState>(state) {
//
//    init {
//        getExchangeRates()
//    }
//
//    fun getExchangeRates() {
//        exchangeRatesApi.getFiats()
//            .execute {
//                copy(response = it)
//            }
//    }
//
//    @AssistedInject.Factory
//    interface Factory {
//        fun create(state: TestState): TestViewModel
//    }
//
//    companion object : MvRxViewModelFactory<TestViewModel, TestState> {
//        override fun create(viewModelContext: ViewModelContext, state: TestState): TestViewModel? {
//            val activity = (viewModelContext as ActivityViewModelContext).activity<TestActivity>()
//            return activity.viewModelFactory.create(state)
//        }
//    }
//}