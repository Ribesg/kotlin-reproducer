@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.example.app.extension.constraint

import platform.UIKit.NSLayoutConstraint
import platform.UIKit.UILayoutPriorityRequired

inline fun NSLayoutConstraint.withPriority(priority: Number): NSLayoutConstraint {
    val doubleValue = priority.toDouble()
    require(doubleValue > .0 && doubleValue <= UILayoutPriorityRequired)
    this.priority = priority.toFloat()
    return this
}

inline fun List<NSLayoutConstraint>.withPriority(priority: Number): List<NSLayoutConstraint> {
    val doubleValue = priority.toDouble()
    require(doubleValue > .0 && doubleValue <= UILayoutPriorityRequired)
    val floatValue = priority.toFloat()
    forEach { it.priority = floatValue }
    return this
}

inline fun NSLayoutConstraint.withMinPriority(): NSLayoutConstraint =
    withPriority(Float.MIN_VALUE)

inline fun List<NSLayoutConstraint>.withMinPriority(): List<NSLayoutConstraint> =
    withPriority(Float.MIN_VALUE)

inline var List<NSLayoutConstraint>.active: Boolean
    get() = throw UnsupportedOperationException("Cannot get active for list of constraints")
    set(value) {
        if (value)
            NSLayoutConstraint.activateConstraints(this)
        else
            NSLayoutConstraint.deactivateConstraints(this)
    }
