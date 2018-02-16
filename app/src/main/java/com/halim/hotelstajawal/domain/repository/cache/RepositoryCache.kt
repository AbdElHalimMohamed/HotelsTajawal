package com.halim.hotelstajawal.domain.repository.cache


interface RepositoryCache {

    fun isExpired(): Boolean
}