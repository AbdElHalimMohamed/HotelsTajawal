package com.halim.hotelstajawal.domain.entity

import android.os.Parcel
import android.os.Parcelable


open class Entity(val id: Long): Parcelable {

    constructor(parcel: Parcel) : this(parcel.readLong())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Entity> {
        override fun createFromParcel(parcel: Parcel): Entity {
            return Entity(parcel)
        }

        override fun newArray(size: Int): Array<Entity?> {
            return arrayOfNulls(size)
        }
    }
}