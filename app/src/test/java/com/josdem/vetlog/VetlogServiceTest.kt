/*
  Copyright 2025 Jose Morales contact@josdem.io

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/

package com.josdem.vetlog

import com.josdem.vetlog.LocationTrackerTest.BlockCoroutineDispatcher
import com.josdem.vetlog.service.RetrofitHelper
import com.josdem.vetlog.service.VetlogService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import kotlin.coroutines.CoroutineContext

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class VetlogServiceTest {
    private val vetlogService: VetlogService =
        RetrofitHelper.getInstance().create(VetlogService::class.java)

    @Test
    fun `a should store a pet for geolocation`() {
        blockCoroutineScope.launch {
            val response = vetlogService.storePets("338")
            val body: String? = response.body()
            assertTrue(response.isSuccessful)
            assertEquals("OK", body)
        }
    }

    @Test
    fun `b should send pet geolocation`() {
        blockCoroutineScope.launch {
            val response = vetlogService.sendLocation(37.7749, -122.4194)
            val body: String? = response.body()
            assertTrue(response.isSuccessful)
            assertEquals("OK", body)
        }
    }

    class BlockCoroutineDispatcher : CoroutineDispatcher() {
        override fun dispatch(
            context: CoroutineContext,
            block: Runnable,
        ) {
            block.run()
        }
    }

    private val blockCoroutineScope =
        CoroutineScope(
            com.josdem.vetlog.LocationTrackerTest
                .BlockCoroutineDispatcher(),
        )
}
