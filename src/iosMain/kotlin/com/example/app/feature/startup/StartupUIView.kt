package com.example.app.feature.startup

import com.example.app.extension.constraint.Constraints.Type.GREATER
import com.example.app.extension.constraint.setupConstraints
import com.example.app.extension.view.uiLabel
import kotlinx.cinterop.CValue
import platform.CoreGraphics.CGRect
import platform.UIKit.UIColor
import platform.UIKit.UIFont
import platform.UIKit.UIView
import platform.UIKit.addSubview
import platform.UIKit.backgroundColor

class StartupUIView(frame: CValue<CGRect>) : UIView(frame) {

    val label = uiLabel {
        adjustsFontSizeToFitWidth = true
        font = UIFont.systemFontOfSize(150.0, 700.0)
        minimumScaleFactor = .25
        textColor = UIColor.blackColor
    }

    init {
        backgroundColor = UIColor.yellowColor

        addSubview(label)

        setupConstraints {
            with(label) {
                centerIn(parent)
                fitIn(parent, 20.0, type = GREATER)
            }
        }
    }

}
