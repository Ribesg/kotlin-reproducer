package com.example.app

import com.example.app.feature.startup.StartupViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationDelegateProtocol
import platform.UIKit.UIApplicationDelegateProtocolMeta
import platform.UIKit.UIColor
import platform.UIKit.UINavigationController
import platform.UIKit.UIResponder
import platform.UIKit.UIResponderMeta
import platform.UIKit.UIScreen
import platform.UIKit.UIWindow
import platform.UIKit.backgroundColor
import kotlin.native.concurrent.ensureNeverFrozen

class AppDelegate @OverrideInit constructor() : UIResponder(), UIApplicationDelegateProtocol {

    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta

    private var uiWindow: UIWindow? = null

    init {
        ensureNeverFrozen()
    }

    override fun application(
        application: UIApplication,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        didFinishLaunchingWithOptions: Map<Any?, *>?
    ): Boolean {
        setupWindow()
        return true
    }

    private fun setupWindow() {
        setWindow(UIWindow(frame = UIScreen.mainScreen.bounds))
        window!!.run {
            backgroundColor = UIColor.purpleColor
            rootViewController = createRootController()
            makeKeyAndVisible()
        }
    }

    private fun createRootController(): UINavigationController =
        UINavigationController(rootViewController = StartupViewController()).apply {
            navigationBarHidden = true
        }

    override fun window(): UIWindow? = uiWindow

    override fun setWindow(window: UIWindow?) {
        uiWindow = window
    }

}
