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

package com.josdem.vetlog.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface VetlogService {
    @GET("/geolocation/store/{petIds}")
    suspend fun storePets(
        @Path("petIds") petIds: String,
    ): Response<String>

    @GET("/geolocation/location/{latitude}/{longitude}")
    suspend fun sendLocation(
        @Header("token") token: String,
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double,
    ): Response<String>

    @GET("/geolocation/pullup/{petId}")
    suspend fun pullingUp(
        @Path("petId") petId: String,
    ): Response<String>
}
