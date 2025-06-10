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

import android.os.Bundle
import android.view.View
import com.josdem.vetlog.databinding.FragmentFirstBinding
import com.josdem.vetlog.state.ApplicationState
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FirstFragmentTest {
    @MockK
    private lateinit var view: View

    @MockK
    private lateinit var bundle: Bundle

    @MockK
    private lateinit var binding: FragmentFirstBinding

    @InjectMockKs
    private var firstFragment = FirstFragment()

    @Before
    fun setup() = MockKAnnotations.init(this)

    @Test
    fun `should validate first fragment`() {
        // Return a mock button when binding.send is called
        firstFragment.onViewCreated(view, bundle)
        assertEquals(firstFragment, ApplicationState.getValue("firstFragment"))
    }
}
