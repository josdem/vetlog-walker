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

package com.josdem.vetlog.state

import java.util.concurrent.ConcurrentHashMap

const val PET_IDS = "petIds"

object ApplicationState {
    private val memory = ConcurrentHashMap<String, List<String>>()

    fun storeValue(
        key: String,
        value: List<String>,
    ) {
        memory[key] = value
    }

    fun getValue(key: String): List<String>? = memory[key]
}
