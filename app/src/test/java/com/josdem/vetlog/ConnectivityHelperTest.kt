package com.josdem.vetlog

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.josdem.vetlog.helper.ConnectivityHelper
import com.josdem.vetlog.util.ContextUtils
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

internal class ConnectivityHelperTest {
    @MockK
    private lateinit var connectivityManager: ConnectivityManager

    @MockK
    private lateinit var networkCapabilities: NetworkCapabilities

    @MockK
    private lateinit var contextUtils: ContextUtils

    private lateinit var connectivityHelper: ConnectivityHelper

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        connectivityHelper = ConnectivityHelper(contextUtils)
        every { contextUtils.getSystemService() } returns connectivityManager
        every { connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) } returns networkCapabilities
    }

    @Test
    fun `should detect mobile network`() {
        every { networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) } returns true
        assertTrue(connectivityHelper.isMobileConnected())
    }

    @Test
    fun `should detect wifi network`() {
        every { networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) } returns false
        assertFalse(connectivityHelper.isMobileConnected())
    }
}
