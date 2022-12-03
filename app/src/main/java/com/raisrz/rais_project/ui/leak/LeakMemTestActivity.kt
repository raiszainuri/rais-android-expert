package com.raisrz.rais_project.ui.leak

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raisrz.rais_project.R
import com.raisrz.rais_project.databinding.ActivityLeakMemTestBinding

class LeakMemTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeakMemTestBinding
    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeakMemTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun registerBroadCastReceiver() {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (intent.action) {
                    Intent.ACTION_POWER_CONNECTED -> {
                        binding.tvPowerStatus.text = getString(R.string.str_charger_connected)
                    }
                    Intent.ACTION_POWER_DISCONNECTED -> {
                        binding.tvPowerStatus.text = getString(R.string.str_charger_disconnected)
                    }
                }
            }
        }
        val intentFilter = IntentFilter()
        intentFilter.apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onStart() {
        super.onStart()
        registerBroadCastReceiver()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }
}