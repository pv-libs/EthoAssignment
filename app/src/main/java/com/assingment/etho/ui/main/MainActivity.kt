package com.assingment.etho.ui.main

import android.Manifest
import android.content.IntentFilter
import android.provider.Telephony
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.assingment.etho.R
import com.assingment.etho.base.BaseActivity
import com.assingment.etho.databinding.ActivityMainBinding
import com.assingment.etho.receivers.SMSBroadcastReceiver
import com.assingment.etho.ui.main.adapter.MainViewPagerAdapter
import com.assingment.etho.utils.MyPhoneStateListener
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    @Inject
    lateinit var viewPagerAdapter: MainViewPagerAdapter

    @Inject
    lateinit var phoneStateListener: MyPhoneStateListener

    override fun init() {
        binding.viewPager.offscreenPageLimit = 2
        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Server"
                1 -> "Client"
                else -> ""
            }
        }.attach()

        permissionsRequestLauncher.launch(
            arrayOf(
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.RECEIVE_SMS
            )
        )
    }

    override fun initObservers() {

    }


    private fun initSmsListener() {
        val receiver = SMSBroadcastReceiver()
        val intentFilter = IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
        registerReceiver(receiver, intentFilter)
    }

    private fun initCallListener() {
        val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE)
    }

    private val permissionsRequestLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[Manifest.permission.READ_CALL_LOG] == true) {
                initCallListener()
            }
            if (permissions[Manifest.permission.RECEIVE_SMS] == true) {
                initSmsListener()
            }
        }

    override val viewModel: MainViewModel by viewModels()
    override val layoutResource = R.layout.activity_main

}