package com.josdem.vetlog

import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.josdem.vetlog.service.VetlogService
import com.josdem.vetlog.tracker.LocationTracker
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import kotlin.coroutines.CoroutineContext

internal class LocationTrackerTest {
    @MockK
    private lateinit var context: Context

    @RelaxedMockK
    private lateinit var locationManager: LocationManager

    @MockK
    private lateinit var vetlogService: VetlogService

    @MockK
    private lateinit var owner: LifecycleOwner

    @MockK
    private lateinit var location: Location

    @InjectMockKs
    private lateinit var locationTracker: LocationTracker

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        locationTracker = LocationTracker(context)
        every { context.getSystemService(Context.LOCATION_SERVICE) } returns locationManager
    }

    @Test
    fun `should create a location tracker`() {
        locationTracker.onCreate(owner)
        verify { locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0f, locationTracker) }
    }

    @Test
    fun `should destroy location tracker`() {
        locationTracker.onCreate(owner)
        locationTracker.onDestroy(owner)
        verify { locationManager.removeUpdates(locationTracker) }
    }

    @Test
    fun `should update on location changes`() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0

        every { location.latitude } returns 37.7749
        every { location.longitude } returns -122.4194

        blockCoroutineScope.launch {
            locationTracker.onLocationChanged(location)
        }

        verify { location.latitude }
        verify { location.longitude }
    }

    class BlockCoroutineDispatcher : CoroutineDispatcher() {
        override fun dispatch(
            context: CoroutineContext,
            block: Runnable,
        ) {
            block.run()
        }
    }

    private val blockCoroutineScope = CoroutineScope(BlockCoroutineDispatcher())
}
