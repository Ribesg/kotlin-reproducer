package com.example.app.feature.startup

import platform.UIKit.UIViewController

class StartupViewController : UIViewController(null, null) {

    private lateinit var feature: StartupView

    override fun loadView() {
        super.loadView()
        view = StartupUIView(view.frame)
    }

    override fun viewDidLoad() {
        super.viewDidLoad()
        feature = StartupView(this, view as StartupUIView)
        feature.presenter.onViewDidLoad()
    }

}
