package com.example.app.extension.constraint

import platform.UIKit.NSLayoutConstraint
import platform.UIKit.UIView
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun UIView.setupConstraints(setup: Constraints.() -> Unit): List<NSLayoutConstraint> {
    contract { callsInPlace(setup, InvocationKind.EXACTLY_ONCE) }
    val constraints = mutableListOf<NSLayoutConstraint>()
    Constraints(this, constraints).setup()
    NSLayoutConstraint.activateConstraints(constraints)
    return constraints
}

class Constraints internal constructor(
    val parent: UIView,
    constraints: MutableList<NSLayoutConstraint>
) : CenterConstraints by CenterConstraintsImpl(constraints),
    CenterToSideConstraints by CenterToSideConstraintsImpl(constraints),
    RelativeSizeConstraints by RelativeSizeConstraintsImpl(constraints),
    SelfConstraints by SelfConstraintsImpl(constraints),
    SideConstraints by SideConstraintsImpl(constraints) {

    enum class Type {
        EQUAL,
        GREATER,
        LESS
    }

}
