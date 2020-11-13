package com.example.app.extension.constraint

import com.example.app.extension.constraint.Constraints.Type
import com.example.app.extension.constraint.Constraints.Type.*
import platform.UIKit.NSLayoutAnchor
import platform.UIKit.NSLayoutConstraint
import platform.UIKit.NSLayoutDimension
import platform.UIKit.UILayoutGuide
import platform.UIKit.UIView
import platform.UIKit.bottomAnchor
import platform.UIKit.centerXAnchor
import platform.UIKit.centerYAnchor
import platform.UIKit.heightAnchor
import platform.UIKit.leadingAnchor
import platform.UIKit.safeAreaLayoutGuide
import platform.UIKit.superview
import platform.UIKit.topAnchor
import platform.UIKit.trailingAnchor
import platform.UIKit.widthAnchor

internal abstract class BaseConstraintsBuilder(
    private val constraints: MutableList<NSLayoutConstraint>
) {

    protected operator fun NSLayoutConstraint.unaryPlus(): NSLayoutConstraint {
        constraints += this
        return this
    }

    protected fun resolveSafeArea(from: Any, to: Any, useSafeArea: Boolean): Pair<Boolean, Boolean> {
        if (!useSafeArea) return false to false
        val fromSuper = when (from) {
            is UILayoutGuide -> from.owningView
            is UIView -> from.superview
            else -> unsupportedReceiverError(from)
        }
        val toSuper = when (to) {
            is UILayoutGuide -> to.owningView
            is UIView -> to.superview
            else -> unsupportedParameterError(to)
        }
        if (from == toSuper) {
            return true to false
        }
        if (to == fromSuper) {
            return false to true
        }
        if (fromSuper == toSuper) {
            error("Cannot useSafeArea with sibling views")
        }
        error("No relationship found between views")
    }

    protected fun NSLayoutAnchor.constraintToAnchor(
        type: Type,
        to: NSLayoutAnchor,
        constant: Double
    ): NSLayoutConstraint =
        when (type) {
            EQUAL -> constraintEqualToAnchor(to, constant)
            GREATER -> constraintGreaterThanOrEqualToAnchor(to, constant)
            LESS -> constraintLessThanOrEqualToAnchor(to, constant)
        }

    protected fun NSLayoutDimension.constraintToAnchor(
        type: Type,
        to: NSLayoutDimension,
        multiplier: Double,
        constant: Double
    ): NSLayoutConstraint =
        when (type) {
            EQUAL -> constraintEqualToAnchor(to, multiplier, constant)
            GREATER -> constraintGreaterThanOrEqualToAnchor(to, multiplier, constant)
            LESS -> constraintLessThanOrEqualToAnchor(to, multiplier, constant)
        }

    protected fun NSLayoutDimension.constraintToConstant(
        type: Type,
        constant: Double
    ): NSLayoutConstraint =
        when (type) {
            EQUAL -> constraintEqualToConstant(constant)
            GREATER -> constraintGreaterThanOrEqualToConstant(constant)
            LESS -> constraintLessThanOrEqualToConstant(constant)
        }

    protected fun Any.widthAnchor(useSafeArea: Boolean): NSLayoutDimension = when (this) {
        is UILayoutGuide -> widthAnchor
        is UIView -> if (useSafeArea) safeAreaLayoutGuide.widthAnchor else widthAnchor
        else -> unsupportedReceiverError(this)
    }

    protected fun Any.heightAnchor(useSafeArea: Boolean): NSLayoutDimension = when (this) {
        is UILayoutGuide -> heightAnchor
        is UIView -> if (useSafeArea) safeAreaLayoutGuide.heightAnchor else heightAnchor
        else -> unsupportedReceiverError(this)
    }

    protected fun Any.leadAnchor(useSafeArea: Boolean): NSLayoutAnchor = when (this) {
        is UILayoutGuide -> leadingAnchor
        is UIView -> if (useSafeArea) safeAreaLayoutGuide.leadingAnchor else leadingAnchor
        else -> unsupportedReceiverError(this)
    }

    protected fun Any.trailAnchor(useSafeArea: Boolean): NSLayoutAnchor = when (this) {
        is UILayoutGuide -> trailingAnchor
        is UIView -> if (useSafeArea) safeAreaLayoutGuide.trailingAnchor else trailingAnchor
        else -> unsupportedReceiverError(this)
    }

    protected fun Any.topAnchor(useSafeArea: Boolean): NSLayoutAnchor = when (this) {
        is UILayoutGuide -> topAnchor
        is UIView -> if (useSafeArea) safeAreaLayoutGuide.topAnchor else topAnchor
        else -> unsupportedReceiverError(this)
    }

    protected fun Any.bottomAnchor(useSafeArea: Boolean): NSLayoutAnchor = when (this) {
        is UILayoutGuide -> bottomAnchor
        is UIView -> if (useSafeArea) safeAreaLayoutGuide.bottomAnchor else bottomAnchor
        else -> unsupportedReceiverError(this)
    }

    protected fun Any.centerXAnchor(useSafeArea: Boolean): NSLayoutAnchor = when (this) {
        is UILayoutGuide -> centerXAnchor
        is UIView -> if (useSafeArea) safeAreaLayoutGuide.centerXAnchor else centerXAnchor
        else -> unsupportedReceiverError(this)
    }

    protected fun Any.centerYAnchor(useSafeArea: Boolean): NSLayoutAnchor = when (this) {
        is UILayoutGuide -> centerYAnchor
        is UIView -> if (useSafeArea) safeAreaLayoutGuide.centerYAnchor else centerYAnchor
        else -> unsupportedReceiverError(this)
    }

    private fun unsupportedReceiverError(receiver: Any): Nothing {
        error("Unsupported receiver type: ${receiver::class.simpleName}")
    }

    private fun unsupportedParameterError(parameter: Any): Nothing {
        error("Unsupported parameter type: ${parameter::class.simpleName}")
    }

}
