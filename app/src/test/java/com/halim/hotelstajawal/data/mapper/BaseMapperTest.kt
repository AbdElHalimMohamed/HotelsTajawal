package com.halim.hotelstajawal.data.mapper

import com.halim.hotelstajawal.di.data.mapper.MapperComponent
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

@Suppress("AddVarianceModifier")
abstract class BaseMapperTest<From : Any, To : Any> {

    @Inject
    lateinit var mapper: Mapper<From, To>

    abstract fun getValidFrom(): From?

    abstract fun getInvalidFrom(): From?

    abstract fun getValidTo(): To?

    abstract fun getInvalidTo(): To?

    abstract fun assertEquals(expected: To?, actual: To?)

    abstract fun inject(component: MapperComponent)

    @Before
    fun init() {
        inject(MapperTestSuite.mapperComponent)
    }

    @Test
    fun validFrom_validTo() {
        val from = getValidFrom()
        val to = getValidTo()

        assertEquals(to, mapper.transform(from))
    }

    @Test
    fun invalidFrom_invalidTo() {
        val from = getInvalidFrom()
        val to = getInvalidTo()

        assertEquals(to, mapper.transform(from))
    }
}