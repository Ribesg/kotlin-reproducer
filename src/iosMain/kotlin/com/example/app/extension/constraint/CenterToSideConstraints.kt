package com.example.app.extension.constraint

import com.example.app.extension.constraint.Constraints.Type
import com.example.app.extension.constraint.Constraints.Type.EQUAL
import platform.UIKit.NSLayoutConstraint

interface CenterToSideConstraints {

    fun Any.centerToTopOf(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    fun Any.centerToBottomOf(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    fun Any.centerToLeadOf(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    fun Any.centerToTrailOf(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    fun Any.topToCenterOf(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    fun Any.bottomToCenterOf(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    fun Any.leadToCenterOf(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

    fun Any.trailToCenterOf(
        other: Any,
        constant: Double = .0,
        type: Type = EQUAL,
        useSafeArea: Boolean = false
    ): NSLayoutConstraint

}

internal class CenterToSideConstraintsImpl(
    constraints: MutableList<NSLayoutConstraint>
) : BaseConstraintsBuilder(constraints), CenterToSideConstraints {

    override fun Any.centerToTopOf(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +centerYAnchor(useFromSafeArea).constraintToAnchor(type, other.topAnchor(useToSafeArea), constant)
    }

    override fun Any.centerToBottomOf(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +other.bottomAnchor(useToSafeArea).constraintToAnchor(type, centerYAnchor(useFromSafeArea), constant)
    }

    override fun Any.centerToLeadOf(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +centerXAnchor(useFromSafeArea).constraintToAnchor(type, other.leadAnchor(useToSafeArea), constant)
    }

    override fun Any.centerToTrailOf(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +other.trailAnchor(useToSafeArea).constraintToAnchor(type, centerXAnchor(useFromSafeArea), constant)
    }

    override fun Any.topToCenterOf(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +topAnchor(useFromSafeArea).constraintToAnchor(type, other.centerYAnchor(useToSafeArea), constant)
    }

    override fun Any.bottomToCenterOf(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +other.centerYAnchor(useToSafeArea).constraintToAnchor(type, bottomAnchor(useFromSafeArea), constant)
    }

    override fun Any.leadToCenterOf(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +leadAnchor(useFromSafeArea).constraintToAnchor(type, other.centerXAnchor(useToSafeArea), constant)
    }

    override fun Any.trailToCenterOf(
        other: Any,
        constant: Double,
        type: Type,
        useSafeArea: Boolean
    ): NSLayoutConstraint {
        val (useFromSafeArea, useToSafeArea) = resolveSafeArea(this, other, useSafeArea)
        return +other.centerXAnchor(useToSafeArea).constraintToAnchor(type, trailAnchor(useFromSafeArea), constant)
    }

}
