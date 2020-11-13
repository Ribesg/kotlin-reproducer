package com.example.app.extension.constraint

import com.example.app.extension.constraint.Constraints.Type
import com.example.app.extension.constraint.Constraints.Type.EQUAL
import platform.UIKit.NSLayoutConstraint

interface SideConstraints {

    // Same side

    fun Any.topToTopOf(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    fun Any.bottomToBottomOf(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    fun Any.leadToLeadOf(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    fun Any.trailToTrailOf(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    // Opposite side

    fun Any.topToBottomOf(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    fun Any.bottomToTopOf(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    fun Any.leadToTrailOf(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    fun Any.trailToLeadOf(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    // Side shortcuts

    fun Any.fitVerticallyTo(
        other: Any,
        constant: Double = .0,
        topConstant: Double = constant,
        bottomConstant: Double = constant,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): List<NSLayoutConstraint>

    fun Any.fitHorizontallyTo(
        other: Any,
        constant: Double = .0,
        leadConstant: Double = constant,
        trailConstant: Double = constant,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): List<NSLayoutConstraint>

    fun Any.fitIn(
        other: Any,
        constant: Double = .0,
        verticalConstant: Double = constant,
        horizontalConstant: Double = constant,
        topConstant: Double = verticalConstant,
        bottomConstant: Double = verticalConstant,
        leadConstant: Double = horizontalConstant,
        trailConstant: Double = horizontalConstant,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): List<NSLayoutConstraint>

}

internal class SideConstraintsImpl(
    constraints: MutableList<NSLayoutConstraint>
) : BaseConstraintsBuilder(constraints), SideConstraints {

    override fun Any.topToTopOf(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +topAnchor(useFromSafeArea).constraintToAnchor(type, other.topAnchor(useToSafeArea), constant)
    }

    override fun Any.bottomToBottomOf(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +other.bottomAnchor(useToSafeArea).constraintToAnchor(type, bottomAnchor(useFromSafeArea), constant)
    }

    override fun Any.leadToLeadOf(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +leadAnchor(useFromSafeArea).constraintToAnchor(type, other.leadAnchor(useToSafeArea), constant)
    }

    override fun Any.trailToTrailOf(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +other.trailAnchor(useToSafeArea).constraintToAnchor(type, trailAnchor(useFromSafeArea), constant)
    }

    override fun Any.topToBottomOf(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +topAnchor(useFromSafeArea).constraintToAnchor(type, other.bottomAnchor(useToSafeArea), constant)
    }

    override fun Any.bottomToTopOf(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +other.topAnchor(useToSafeArea).constraintToAnchor(type, bottomAnchor(useFromSafeArea), constant)
    }

    override fun Any.leadToTrailOf(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +leadAnchor(useFromSafeArea).constraintToAnchor(type, other.trailAnchor(useToSafeArea), constant)
    }

    override fun Any.trailToLeadOf(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +other.leadAnchor(useToSafeArea).constraintToAnchor(type, trailAnchor(useFromSafeArea), constant)
    }

    override fun Any.fitVerticallyTo(
        other: Any,
        constant: Double,
        topConstant: Double,
        bottomConstant: Double,
        type: Type,
        useSafeArea: Boolean
    ) = listOf(
        topToTopOf(other, topConstant, type, useSafeArea),
        bottomToBottomOf(other, bottomConstant, type, useSafeArea)
    )

    override fun Any.fitHorizontallyTo(
        other: Any,
        constant: Double,
        leadConstant: Double,
        trailConstant: Double,
        type: Type,
        useSafeArea: Boolean
    ) = listOf(
        leadToLeadOf(other, leadConstant, type, useSafeArea),
        trailToTrailOf(other, trailConstant, type, useSafeArea)
    )

    override fun Any.fitIn(
        other: Any,
        constant: Double,
        verticalConstant: Double,
        horizontalConstant: Double,
        topConstant: Double,
        bottomConstant: Double,
        leadConstant: Double,
        trailConstant: Double,
        type: Type,
        useSafeArea: Boolean
    ) = listOf(
        fitVerticallyTo(other, verticalConstant, topConstant, bottomConstant, type, useSafeArea),
        fitHorizontallyTo(other, horizontalConstant, leadConstant, trailConstant, type, useSafeArea)
    ).flatten()

}
