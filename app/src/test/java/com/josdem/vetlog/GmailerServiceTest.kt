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

import com.josdem.vetlog.model.MessageCommand
import com.josdem.vetlog.service.GmailerService
import com.josdem.vetlog.service.RetrofitHelper
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeNotNull
import org.junit.Test

class GmailerServiceTest {
    private val emailerService: GmailerService =
        RetrofitHelper.getGmailerInstance().create(GmailerService::class.java)

    @Test
    fun `should send a pulling up email`() =
        runTest {
            val token = System.getenv("TOKEN")
            assumeNotNull(token)
            val messageCommand = getMessageCommand(token!!)
            val response = emailerService.sendMessage(messageCommand)
            val body: String? = response.body()
            assertTrue(response.isSuccessful)
            assertEquals("OK", body)
        }

    private fun getMessageCommand(token: String): MessageCommand =
        MessageCommand(
            "josdem",
            "contact@josdem.io",
            token,
            "Hello from Vetlog Walker!",
            "This is a test email",
            "pullingUp.ftl",
        )
}
