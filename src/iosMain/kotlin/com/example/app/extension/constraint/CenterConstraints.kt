package com.example.app.extension.constraint

import com.example.app.extension.constraint.Constraints.Type
import com.example.app.extension.constraint.Constraints.Type.EQUAL
import platform.UIKit.NSLayoutConstraint

interface CenterConstraints {

    fun Any.centerVerticallyTo(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    fun Any.centerHorizontallyTo(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    fun Any.centerIn(
        other: Any,
        verticalConstant: Double = .0,
        horizontalConstant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): List<NSLayoutConstraint>

}

internal class CenterConstraintsImpl(
    constraints: MutableList<NSLayoutConstraint>
) : BaseConstraintsBuilder(constraints), CenterConstraints {

    override fun Any.centerVerticallyTo(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +centerYAnchor(useFromSafeArea).constraintToAnchor(type, other.centerYAnchor(useToSafeArea), constant)
    }

    override fun Any.centerHorizontallyTo(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +centerXAnchor(useFromSafeArea).constraintToAnchor(type, other.centerXAnchor(useToSafeArea), constant)
    }

    override fun Any.centerIn(
        other: Any,
        verticalConstant: Double,
        horizontalConstant: Double,
        type: Type,
        useSafeArea: Boolean
    ) = listOf(
        centerVerticallyTo(other, verticalConstant, type, useSafeArea),
        centerHorizontallyTo(other, horizontalConstant, type, useSafeArea)
    )

}
