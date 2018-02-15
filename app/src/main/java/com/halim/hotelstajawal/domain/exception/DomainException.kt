package com.halim.hotelstajawal.domain.exception


sealed class DomainException(val retry: Boolean = true) {

    class RemoteServerException : DomainException()

    class ResourceNotFoundException : DomainException()

    class AuthinticationException : DomainException()

    class NetworkException : DomainException()

    class UnknownException : DomainException(false)
}