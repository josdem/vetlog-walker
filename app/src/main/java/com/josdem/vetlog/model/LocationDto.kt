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

package com.josdem.vetlog.model

/**
 * Data transfer object representing pet's geolocation.
 * @property latitude Pet's latitude geolocation
 * @property longitude Pet's longitude geolocation
 * @property petsIds List of unique identifiers for pets. Used to transfer pet references in API requests and responses.
 */

data class LocationDto(
    val latitude: Double,
    val longitude: Double,
    val petsIds: List<Long>,
)
