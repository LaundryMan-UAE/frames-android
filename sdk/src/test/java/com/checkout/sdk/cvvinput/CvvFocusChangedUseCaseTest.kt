package com.checkout.sdk.cvvinput

import com.checkout.sdk.store.InMemoryStore
import junit.framework.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CvvFocusChangedUseCaseTest {

    @Mock
    private lateinit var storeMock: InMemoryStore

    @Test
    fun `given cvv has length 4 and expected length is uninitialised then an error will be shown`() {
        var initialCvv = Cvv("345678", -1)
        given(storeMock.cvv).willReturn(initialCvv)
        val showError = CvvFocusChangedUseCase(
            initialCvv.value,
            false,
            storeMock
        ).execute()

        assertFalse(showError)
    }

    @Test
    fun `given cvv has length 3 and expected length is 4 then an error will be shown`() {
        val initialCvv = Cvv("146", 4)
        given(storeMock.cvv).willReturn(initialCvv)
        val showError = CvvFocusChangedUseCase(
            initialCvv.value,
            false,
            storeMock
        ).execute()

        assertTrue(showError)
    }

    @Test
    fun `given cvv gains focus then error will be cleared`() {
        val (cvv, hasFocus) = Pair("23456", true)
        val showError = CvvFocusChangedUseCase(
            cvv,
            hasFocus,
            storeMock
        ).execute()

        assertEquals(false, showError)
    }
}
