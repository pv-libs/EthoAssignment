package com.assingment.etho.ui.client

import androidx.fragment.app.viewModels
import com.assingment.etho.R
import com.assingment.etho.base.BaseFragment
import com.assingment.etho.databinding.FragmentClientHomeBinding
import com.assingment.etho.ui.client.adapter.ClientEventsAdapter
import com.assingment.etho.utils.safeLaunchWhenResumed
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class ClientHomeFragment : BaseFragment<ClientHomeViewModel, FragmentClientHomeBinding>() {

    @Inject
    lateinit var adapter: ClientEventsAdapter

    override fun init() {
        Timber.d("init")
        binding.recyclerView.adapter = adapter
        binding.viewModel = viewModel
    }

    override fun initObservers() {
        safeLaunchWhenResumed {
            viewModel.receivedEvents.collect {
                Timber.d("events - $it")
                adapter.submitList(it) {
                    binding.recyclerView.scrollToPosition(it.size - 1)
                }
            }
        }
    }

    override val layoutResource = R.layout.fragment_client_home
    override val viewModel: ClientHomeViewModel by viewModels()

}