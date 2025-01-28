package com.example.rainforestcoffee.dialog

import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.rainforestcoffee.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Fragment.setupBottomSheetDialog(onSendClick: (String) -> Unit){

    val dialog = BottomSheetDialog(requireContext(), R.style.DialogStyle)
    val view  = layoutInflater.inflate(R.layout.reset_password_dialog, null)
    dialog.setContentView(view)
    dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    dialog.show()

    val emailEditText = view.findViewById<EditText>(R.id.passEditText)
    val cancelButton = view.findViewById<Button>(R.id.cancelBtn)
    val sendButton = view.findViewById<Button>(R.id.sendBtn)

    sendButton.setOnClickListener{
        val email = emailEditText.text.toString().trim()
        onSendClick(email)
        dialog.dismiss()
    }
    cancelButton.setOnClickListener {
        dialog.dismiss()
    }
}