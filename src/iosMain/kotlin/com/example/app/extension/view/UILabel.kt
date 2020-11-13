@file:Suppress("unused")

package com.example.app.extension.view

import platform.UIKit.UILabel
import platform.UIKit.UIView
import platform.UIKit.translatesAutoresizingMaskIntoConstraints

inline fun UIView.uiLabel(setup: UILabel.() -> Unit) =
    UILabel().apply {
        translatesAutoresizingMaskIntoConstraints = false
        setup()
    }
