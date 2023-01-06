package com.example.myapplication.secondaryFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.MainActivity
import com.example.myapplication.MainApplication
import com.example.myapplication.R
import com.example.myapplication.data.AddedDevice
import com.example.myapplication.databinding.FragmentNewDeviceBinding
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener

class NewDeviceFragment : Fragment() {


    private lateinit var view: FragmentNewDeviceBinding
    private val args: NewDeviceFragmentArgs by navArgs()
    private lateinit var deviceType: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view.root.findNavController().navigateUp()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MainActivity.instance.bottomNavigationView.visibility = View.GONE

        // Inflate the layout for this fragment
        view= FragmentNewDeviceBinding.inflate(inflater, container, false)
        val addedDevice= AddedDevice(args.scanResult)

        view.editTextTextPersonName.setText(addedDevice.name)

        view.textview.text=addedDevice.address
        view.spinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                deviceType=parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        view.button2.setOnClickListener{
            if(view.editTextTextPassword3.text.toString()==view.editTextTextPassword4.text.toString()){
                addedDevice.setPassword(view.editTextTextPassword3.text.toString())
                addedDevice.name=view.editTextTextPersonName.text.toString()
                addedDevice.type=deviceType

                MainApplication.currentUser.addDevice(addedDevice)
                Navigation.findNavController(view.root).navigateUp()
            }
        }

        return view.root
    }

}