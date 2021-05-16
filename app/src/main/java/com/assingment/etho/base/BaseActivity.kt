package com.assingment.etho.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.assingment.etho.utils.collect
import com.assingment.etho.utils.safeLaunchWhenCreated
import com.assingment.etho.utils.showToast

abstract class BaseActivity<VM : BaseViewModel, Binding : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: Binding

    abstract val viewModel: VM
    abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToast()

        binding = DataBindingUtil.setContentView(this, layoutResource)
        binding.lifecycleOwner = this

        init()
        initObservers()

    }

    private fun initToast() {
        safeLaunchWhenCreated {
            viewModel.showToastEvent.collect(::showToast)
        }
    }


    abstract fun init()
    abstract fun initObservers()

}