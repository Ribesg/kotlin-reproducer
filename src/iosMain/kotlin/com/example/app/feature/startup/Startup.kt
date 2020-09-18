@file:Suppress("FunctionName")

package com.example.app.feature.startup

import io.ktor.client.HttpClient
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import platform.UIKit.UIColor
import platform.UIKit.backgroundColor
import kotlin.native.concurrent.ensureNeverFrozen

class Startup(
    private val controller: StartupViewController,
    private val uiView: StartupUIView
) : CoroutineScope by MainScope() {

    init {
        ensureNeverFrozen()
        launch {
            try {
                val client = HttpClient()
                ResponseObserver.install(ResponseObserver {
                    // Whatever here actually gets executed
                    println("Inside ResponseObserver")
                }, client)
                println("Starting request")
                client.get<HttpResponse>("https://www.google.com/")
                println("Request done") // Runtime never reaches this point
                onSuccess()
            } catch (t: Throwable) {
                t.printStackTrace()
                onError()
            }
        }
    }

    private fun onError() {
        uiView.backgroundColor = UIColor.redColor
    }

    private fun onSuccess() {
        uiView.backgroundColor = UIColor.greenColor
    }

}
