@file:Suppress("FunctionName")

package com.example.app.feature.startup

import com.example.app.model.user.User
import platform.UIKit.UIColor
import platform.UIKit.backgroundColor

class StartupView(
    private val controller: StartupViewController,
    private val uiView: StartupUIView
) : StartupFeature.View {

    val presenter: StartupFeature.Presenter = StartupPresenter(StartupModel(), this)

    override fun setUsers(users: List<User>) {
        uiView.backgroundColor = UIColor.greenColor
        uiView.label.text = users.size.toString()
    }

    override fun setError() {
        uiView.backgroundColor = UIColor.redColor
        uiView.label.text = "Error"
    }

}
