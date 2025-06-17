package com.josdem.vetlog.helper

import android.app.AlertDialog
import android.content.Context
import com.josdem.vetlog.state.ApplicationState

class DialogHelper(
    private val context: Context,
) {
    private val builder: AlertDialog.Builder = AlertDialog.Builder(context)

    init {
        builder
            .setTitle("Send pulling up message")
            .setPositiveButton("Send") { dialog, which ->
                // Do something.
            }.setNegativeButton("Close") { dialog, which ->
                // Do something else.
            }.setMultiChoiceItems(
                ApplicationState.getValue("petIds")!!.toTypedArray(),
                null,
            ) { dialog, which, isChecked ->
                // Do something.
            }
    }

    fun showDialog() {
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
