package com.assingment.etho.ui.dialogs

import android.content.Context
import com.assingment.etho.R
import com.assingment.etho.base.BaseBottomSheetDialog
import com.assingment.etho.databinding.DialogTextInputBinding

class TextInputDialog(
    context: Context,
    private val hint: String,
    private val onSubmit: (String) -> Unit,
) : BaseBottomSheetDialog<DialogTextInputBinding>(context) {

    override fun setup() {
        skipCollapsed()

        binding.hint = hint
        binding.submitButton.setOnClickListener {
            val text = binding.inputText.text.toString()
            onSubmit(text)
            dismiss()
        }
    }

    override val layoutResource = R.layout.dialog_text_input
}