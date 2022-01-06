package com.udacity.asteroidradar

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid(val id: Long, val codename: String, val closeApproachDate: String,
                    val absoluteMagnitude: Double, val estimatedDiameter: Double,
                    val relativeVelocity: Double, val distanceFromEarth: Double,
                    val isPotentiallyHazardous: Boolean) : Parcelable{
    constructor(id: Long, codename: String, closeApproachDate: String) : this(
        id, codename, closeApproachDate, 69.9,
        96.6, 6.5, 29.9, true
    )
}