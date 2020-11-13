package com.example.app.extension.constraint

import com.example.app.extension.constraint.Constraints.Type
import com.example.app.extension.constraint.Constraints.Type.EQUAL
import platform.UIKit.NSLayoutConstraint

interface SelfConstraints {

    fun Any.width(
        constant: Double,
        type: Type = EQUAL
    ): NSLayoutConstraint

    fun Any.height(
        constant: Double,
        type: Type = EQUAL
    ): NSLayoutConstraint

    fun Any.size(
        width: Double,
        height: Double,
        type: Type = EQUAL
    ): List<NSLayoutConstraint>

    fun Any.size(
        square: Double,
        type: Type = EQUAL
    ): List<NSLayoutConstraint>

    fun Any.aspectRatio(
        multiplier: Double
    ): NSLayoutConstraint

}

internal class SelfConstraintsImpl(
    constraints: MutableList<NSLayoutConstraint>
) : BaseConstraintsBuilder(constraints), SelfConstraints {

    override fun Any.width(constant: Double, type: Type) =
        +widthAnchor(false).constraintToConstant(type, constant)

    override fun Any.height(constant: Double, type: Type) =
        +heightAnchor(false).constraintToConstant(type, constant)

    override fun Any.size(width: Double, height: Double, type: Type) = listOf(
        width(width, type),
        height(height, type)
    )

    override fun Any.size(square: Double, type: Type) =
        size(square, square)

    override fun Any.aspectRatio(multiplier: Double) =
        +widthAnchor(false).constraintEqualToAnchor(heightAnchor(false), multiplier)

}
