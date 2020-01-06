package com.example.cryptomanager_v2.ui.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.cryptomanager_v2.R
import dagger.android.support.DaggerAppCompatActivity

class TestActivity : DaggerAppCompatActivity() {

//    @Inject
//    lateinit var viewModelFactory: TestViewModel.Factory

//    private var viewModel by activityViewModel(TestViewModel::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

//        viewModel = viewModelFactory.create(TestState())

//        withState(viewModel) { state ->
//            println(state.response)
//            textView.text = when (state.response) {
//                is Uninitialized -> "Uninitialized"
//                is Loading -> "Loading"
//                is Incomplete -> "Incomplete"
//                is Success -> "Success"
//                is Fail -> "Fail"
//                else -> {"Test"}
//            }
//        }
    }

    companion object {
        fun create(context: Context) : Intent {
            return Intent(context, TestActivity::class.java)
        }
    }
}
