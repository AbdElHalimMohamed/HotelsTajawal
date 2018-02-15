package com.halim.hotelstajawal.data.mapper

import com.halim.hotelstajawal.data.Constant.Defaults
import com.halim.hotelstajawal.data.model.Location
import com.halim.hotelstajawal.domain.entity.Address


class AddressMapper : Mapper<Location, Address>() {

    override fun transform(from: Location?): Address?
            = Address(from?.lat ?: Defaults.Double,
            from?.lng ?: Defaults.Double,
            from?.address ?: Defaults.String)
}