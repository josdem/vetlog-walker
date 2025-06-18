package com.josdem.vetlog

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.josdem.vetlog.helper.ConnectivityHelper
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

internal class ConnectivityHelperTest {
    @RelaxedMockK
    private lateinit var context: Context

    @RelaxedMockK
    private lateinit var connectivityManager: ConnectivityManager

    @MockK
    private lateinit var networkCapabilities: NetworkCapabilities

    @InjectMockKs
    private lateinit var connectivityHelper: ConnectivityHelper

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        connectivityHelper.isMobileConnected(context)
        every { context.getSystemService(Context.CONNECTIVITY_SERVICE) } returns connectivityManager
        every { connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) } returns networkCapabilities
    }

    @Test
    fun `should detect mobile network`() {
        every { networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) } returns true
        assertTrue(connectivityHelper.isMobileConnected(context))
    }
}
