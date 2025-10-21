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
import com.josdem.vetlog.databinding.FragmentSecondBinding
import com.josdem.vetlog.helper.DialogHelper
import com.josdem.vetlog.model.PetDto
import com.josdem.vetlog.service.RetrofitHelper
import com.josdem.vetlog.service.VetlogService
import com.josdem.vetlog.tracker.LocationTracker
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private lateinit var vetlogService: VetlogService
    private lateinit var token: String

    // This property is only valid between onCreateView and
    // onDestroyView.
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        token = BuildConfig.TOKEN
        vetlogService = RetrofitHelper.getInstance().create(VetlogService::class.java)
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        val dialogHelper = context?.let { DialogHelper(it) }
        binding.fab.setOnClickListener {
            dialogHelper?.showDialog()
        }

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        val tracker = this.context?.let { LocationTracker(it) }
        tracker?.let { lifecycle.addObserver(it) }

        binding.finish.setOnClickListener {
            MainScope().launch {
                val result = vetlogService.removeAll(token)
                Log.d("response: ", result.body().toString())
            }
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            tracker?.let { lifecycle.removeObserver(it) }
            tracker?.onDestroy(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
