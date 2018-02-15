package com.halim.hotelstajawal.data.mapper

import com.halim.hotelstajawal.data.Constant.Defaults
import com.halim.hotelstajawal.domain.entity.Hotel as HotelEntity
import com.halim.hotelstajawal.data.model.Hotel as HotelModel


class HotelMapper(private val addressMapper: AddressMapper)
    : Mapper<HotelModel, HotelEntity>() {

    override fun transform(from: HotelModel?): HotelEntity? =
            from?.let {
                HotelEntity(from.id ?: Defaults.ID,
                        from.summary?.hotelName ?: Defaults.String,
                        from.images?.firstOrNull()?.url ?: Defaults.Image,
                        addressMapper.transform(from.location) ?: Defaults.AddressEntity,
                        from.summary?.lowPrice ?: Defaults.Float,
                        from.summary?.highPrice ?: Defaults.Float)
            }
}