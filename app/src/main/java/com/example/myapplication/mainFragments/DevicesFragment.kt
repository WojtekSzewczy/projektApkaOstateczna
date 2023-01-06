package com.example.myapplication.mainFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.myapplication.Adapters.DeviceAdapter
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.Scanner
import com.example.myapplication.databinding.FragmentDevicesBinding
import com.example.myapplication.mainFragments.viewModels.DevicesViewModel


class DevicesFragment : Fragment() {

    private lateinit var view: FragmentDevicesBinding
    private val viewModel : DevicesViewModel by viewModels()
    private val adapter = DeviceAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MainActivity.instance.bottomNavigationView.visibility = View.VISIBLE

        view =FragmentDevicesBinding.inflate(inflater, container, false)

        view.devicesList.adapter=adapter

        view.scanButton.setOnClickListener {
            viewModel.switchScanning()
        }
        observeViewModelState()

//        view.device.setOnClickListener {
//            Navigation.findNavController(view.root).navigate(R.id.action_devicesFragment_to_newDeviceFragment)
//
//        }

        return view.root
    }

    override fun onPause() {
        super.onPause()
        Scanner.clearScanList()
    }

    private fun observeViewModelState() {
        viewModel.devices.observe(viewLifecycleOwner) { devices ->
            adapter.apply {
                submitList(devices)
            }
        }
        viewModel.isScanning.observe(viewLifecycleOwner) { currentState ->
            view.scanButton.text =
                if (currentState) getString(R.string.stop) else getString(R.string.scan)
        }
    }


}