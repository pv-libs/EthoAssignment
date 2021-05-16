package com.assingment.etho.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

abstract class BaseBottomSheetDialog<Binding : ViewDataBinding>(context: Context) :
    BottomSheetDialog(context) {

    protected lateinit var binding: Binding

    @get:LayoutRes
    protected abstract val layoutResource: Int

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding =
            DataBindingUtil.inflate(LayoutInflater.from(context), layoutResource, null, false)

        setContentView(binding.root)

        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setup()

    }


    abstract fun setup()


    protected fun skipCollapsed() {
        val bottomSheet = findViewById<FrameLayout>(
            com.google.android.material.R.id.design_bottom_sheet
        ) ?: return

        // Right here!
        BottomSheetBehavior.from(bottomSheet).apply {
            state = BottomSheetBehavior.STATE_EXPANDED
            skipCollapsed = true
        }
    }

}