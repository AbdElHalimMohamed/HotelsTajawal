package com.halim.hotelstajawal.domain.entity

import android.os.Parcel
import android.os.Parcelable

class Hotel(id: Long,
            val name: String,
            val imageUrl: String,
            val address: Address,
            val currentPrice: Float,
            val prevPrice: Float) : Entity(id), Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Address::class.java.classLoader),
            parcel.readFloat(),
            parcel.readFloat())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        parcel.writeString(name)
        parcel.writeString(imageUrl)
        parcel.writeParcelable(address, flags)
        parcel.writeFloat(currentPrice)
        parcel.writeFloat(prevPrice)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hotel> {
        override fun createFromParcel(parcel: Parcel): Hotel {
            return Hotel(parcel)
        }

        override fun newArray(size: Int): Array<Hotel?> {
            return arrayOfNulls(size)
        }
    }
}