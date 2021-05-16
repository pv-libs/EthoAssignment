package com.assingment.etho.ui.server

import androidx.fragment.app.viewModels
import com.assingment.etho.R
import com.assingment.etho.base.BaseFragment
import com.assingment.etho.databinding.FragmentServerHomeBinding
import com.assingment.etho.ui.dialogs.TextInputDialog
import com.assingment.etho.ui.server.adapter.ServerEventsAdapter
import com.assingment.etho.utils.collect
import com.assingment.etho.utils.safeLaunchWhenResumed
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class ServerHomeFragment : BaseFragment<ServerHomeViewModel, FragmentServerHomeBinding>() {

    @Inject
    lateinit var adapter: ServerEventsAdapter

    override fun init() {
        binding.viewModel = viewModel
        binding.recyclerView.adapter = adapter
    }

    override fun initObservers() {
        safeLaunchWhenResumed {
            viewModel.openNavigationDialogEvent.collect(::openNavigationInstructionsDialog)
        }
        safeLaunchWhenResumed {
            viewModel.receivedEvents.collect {
                adapter.submitList(it) {
                    binding.recyclerView.scrollToPosition(it.size - 1)
                }
            }
        }
    }

    private fun openNavigationInstructionsDialog() {
        TextInputDialog(
            requireContext(),
            hint = "Instructions",
            onSubmit = viewModel::sendNavigationInstructions
        ).show()
    }

    override val layoutResource = R.layout.fragment_server_home
    override val viewModel: ServerHomeViewModel by viewModels()

}