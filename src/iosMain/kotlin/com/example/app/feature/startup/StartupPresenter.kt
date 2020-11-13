package com.example.app.feature.startup

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class StartupPresenter(
    private val model: StartupFeature.Model,
    private val view: StartupFeature.View
) : StartupFeature.Presenter {

    private val scope = MainScope() + CoroutineExceptionHandler { _, e ->
        println("Uncaught exception in coroutine:")
        e.printStackTrace()
    }

    override fun onViewDidLoad() {
        scope.launch {
            try {
                view.setUsers(model.getUsers())
            } catch (t: Throwable) {
                if (t is CancellationException) return@launch
                println("Error while fetching users")
                t.printStackTrace()
            }
        }
    }

}
