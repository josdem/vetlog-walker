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

package com.josdem.vetlog.helper

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import com.josdem.vetlog.service.RetrofitHelper
import com.josdem.vetlog.service.VetlogService
import com.josdem.vetlog.state.ApplicationState
import com.josdem.vetlog.state.PET_IDS
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class DialogHelper(
    private val context: Context,
) {
    private val builder: AlertDialog.Builder = AlertDialog.Builder(context)
    private var vetlogService = RetrofitHelper.getInstance().create(VetlogService::class.java)
    val checkedItems = BooleanArray(ApplicationState.getValue(PET_IDS)!!.size)
    val selectedItems = ApplicationState.getValue(PET_IDS)!!.toMutableList()

    init {
        builder
            .setTitle("Send pulling up message")
            .setPositiveButton("Send") { dialog, which ->
                for (i in checkedItems.indices) {
                    if (checkedItems[i]) {
                        Log.d("Item: ", selectedItems[i])
                        MainScope().launch {
                            val result = vetlogService.pullingUp(selectedItems[i])
                            Log.d("response: ", result.body().toString())
                        }
                    }
                }
            }.setNegativeButton("Close") { dialog, which ->
                dialog.dismiss()
            }.setMultiChoiceItems(
                (ApplicationState.getValue("petIds") ?: emptyList()).toTypedArray(),
                checkedItems,
            ) { dialog, which, isChecked ->
                checkedItems[which] = isChecked
            }
    }

    fun showDialog() {
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
