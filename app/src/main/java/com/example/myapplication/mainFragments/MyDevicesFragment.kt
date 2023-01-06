package com.example.myapplication.mainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.example.myapplication.Adapters.AddedDeviceAdapter
import com.example.myapplication.Adapters.DeviceAdapter
import com.example.myapplication.MainActivity
import com.example.myapplication.MainApplication
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMyDevicesBinding


class MyDevicesFragment : Fragment() {
    private lateinit var view :FragmentMyDevicesBinding
    private val adapter = AddedDeviceAdapter()


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

        // Inflate the layout for this fragment
        view =  FragmentMyDevicesBinding.inflate(inflater, container, false)
        adapter.submitList(MainApplication.currentUser.userDevices)
        view.devicesList.adapter=adapter


//        view.button5.setOnClickListener {
//            Navigation.findNavController(view.root).navigate(R.id.action_myDevicesFragment_to_selectedDeviceFragment)
//        }

        return view.root
    }

}