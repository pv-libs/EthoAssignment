package com.assingment.etho.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.assingment.etho.utils.collect
import com.assingment.etho.utils.safeLaunchWhenResumed
import com.assingment.etho.utils.showToast

abstract class BaseFragment<VM : BaseViewModel, Binding : ViewDataBinding> : Fragment() {

    lateinit var binding: Binding

    abstract val viewModel: VM
    abstract val layoutResource: Int

    var baseActivity: BaseActivity<*, *>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToast()


        init()
        initObservers()
    }


    private fun initToast() {
        safeLaunchWhenResumed {
            viewModel.showToastEvent.collect(requireContext()::showToast)
        }
    }

    abstract fun init()
    abstract fun initObservers()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            baseActivity = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        baseActivity = null
    }

}