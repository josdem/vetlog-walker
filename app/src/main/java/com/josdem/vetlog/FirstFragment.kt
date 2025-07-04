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
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.josdem.vetlog.databinding.FragmentFirstBinding
import com.josdem.vetlog.service.RetrofitHelper
import com.josdem.vetlog.service.VetlogService
import com.josdem.vetlog.state.ApplicationState
import com.josdem.vetlog.state.PET_IDS
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private lateinit var vetlogService: VetlogService

    // This property is only valid between onCreateView and
    // onDestroyView.
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        vetlogService = RetrofitHelper.getInstance().create(VetlogService::class.java)
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.send.setOnClickListener {
            val petIds = binding.petIds.text.toString()
            val pets = petIds.split(",").map { it.trim() }
            ApplicationState.storeValue(PET_IDS, pets)
            MainScope().launch {
                val result = vetlogService.storePets(petIds)
                Log.d("response: ", result.body().toString())
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
