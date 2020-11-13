package com.example.app.extension.constraint

import com.example.app.extension.constraint.Constraints.Type
import com.example.app.extension.constraint.Constraints.Type.EQUAL
import platform.UIKit.NSLayoutConstraint

interface RelativeSizeConstraints {

    fun Any.widthTo(
        other: Any,
        multiplier: Double = 1.0,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    fun Any.heightTo(
        other: Any,
        multiplier: Double = 1.0,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    fun Any.sizeTo(
        other: Any,
        multiplier: Double = 1.0,
        widthMultiplier: Double = multiplier,
        heightMultiplier: Double = multiplier,
        constant: Double = .0,
        widthConstant: Double = constant,
        heightConstant: Double = constant,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): List<NSLayoutConstraint>

}

internal class RelativeSizeConstraintsImpl(
    constraints: MutableList<NSLayoutConstraint>
) : BaseConstraintsBuilder(constraints), RelativeSizeConstraints {

    override fun Any.widthTo(
        other: Any,
        multiplier: Double,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +widthAnchor(useFromSafeArea).constraintToAnchor(
            type,
            other.widthAnchor(useToSafeArea),
            multiplier,
            constant
        )
    }

    override fun Any.heightTo(
        other: Any,
        multiplier: Double,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +heightAnchor(useFromSafeArea).constraintToAnchor(
            type,
            other.heightAnchor(useToSafeArea),
            multiplier,
            constant
        )
    }

    override fun Any.sizeTo(
        other: Any,
        multiplier: Double,
        widthMultiplier: Double,
        heightMultiplier: Double,
        constant: Double,
        widthConstant: Double,
        heightConstant: Double,
        type: Type,
        useSafeArea: Boolean
    ) = listOf(
        widthTo(other, widthMultiplier, widthConstant, type, useSafeArea),
        heightTo(other, heightMultiplier, heightConstant, type, useSafeArea)
    )

}
