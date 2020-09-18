@file:Suppress("FunctionName")

package com.example.app.feature.startup

import com.example.app.model.user.UserApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import platform.UIKit.UIColor
import platform.UIKit.backgroundColor
import kotlin.native.concurrent.ensureNeverFrozen

class Startup(
    private val controller: StartupViewController,
    private val uiView: StartupUIView,
    private val userApi: UserApi = UserApi()
) : CoroutineScope by MainScope() {

    init {
        ensureNeverFrozen()
        launch(CoroutineExceptionHandler { _, e ->
            e.printStackTrace()
            onError()
        }) {
            userApi.getUsers().forEach(::println)
            onSuccess()
        }
    }

    private fun onError() {
        uiView.backgroundColor = UIColor.redColor
    }

    private fun onSuccess() {
        uiView.backgroundColor = UIColor.greenColor
    }

}
