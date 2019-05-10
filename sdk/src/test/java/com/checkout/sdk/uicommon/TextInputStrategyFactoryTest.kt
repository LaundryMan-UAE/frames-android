package com.checkout.sdk.uicommon

import com.checkout.sdk.billingdetails.strategy.*
import com.checkout.sdk.cvvinput.CvvStrategy
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TextInputStrategyFactoryTest {

    @Test
    fun `when strategy key is cvv then strategy is CvvStrategy`() {
        val strategy = TextInputStrategyFactory.createStrategy("cvv")
        assertEquals(CvvStrategy::class.java, strategy.javaClass)
    }

    @Test
    fun `when strategy key is customer_name then strategy is CustomerNameStrategy`() {
        val strategy = TextInputStrategyFactory.createStrategy("customer_name")
        assertEquals(CustomerNameStrategy::class.java, strategy.javaClass)
    }

    @Test
    fun `when strategy key is address_one then strategy is AddressOneStrategy`() {
        val strategy = TextInputStrategyFactory.createStrategy("address_one")
        assertEquals(AddressOneStrategy::class.java, strategy.javaClass)
    }

    @Test
    fun `when strategy key is address_two then strategy is AddressTwoStrategy`() {
        val strategy = TextInputStrategyFactory.createStrategy("address_two")
        assertEquals(AddressTwoStrategy::class.java, strategy.javaClass)
    }

    @Test
    fun `when strategy key is city then strategy is CityStrategy`() {
        val strategy = TextInputStrategyFactory.createStrategy("city")
        assertEquals(CityStrategy::class.java, strategy.javaClass)
    }

    @Test
    fun `when strategy key is state then strategy is StateStrategy`() {
        val strategy = TextInputStrategyFactory.createStrategy("state")
        assertEquals(StateStrategy::class.java, strategy.javaClass)
    }

    @Test
    fun `when strategy key is unknown then an IllegalArgumentException is thrown`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            TextInputStrategyFactory.createStrategy("unknown")
        }
    }
}
