package com.example.app.feature.startup

import kotlinx.cinterop.CValue
import platform.CoreGraphics.CGRect
import platform.UIKit.UIView
import kotlin.native.concurrent.ensureNeverFrozen

class StartupUIView(frame: CValue<CGRect>) : UIView(frame) {

    init {
        ensureNeverFrozen()
    }

}
