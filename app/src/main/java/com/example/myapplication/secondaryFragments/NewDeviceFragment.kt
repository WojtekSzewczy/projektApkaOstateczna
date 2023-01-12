package com.example.myapplication.secondaryFragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.MainActivity
import com.example.myapplication.MainApplication
import com.example.myapplication.Scanner
import com.example.myapplication.data.AddedDevice
import com.example.myapplication.databaseHandlers.models.Device
import com.example.myapplication.databaseHandlers.repositories.DeviceRepository
import com.example.myapplication.databaseHandlers.repositories.MyRoomRepository
import com.example.myapplication.databinding.FragmentNewDeviceBinding

class NewDeviceFragment : Fragment() {


    private lateinit var view: FragmentNewDeviceBinding
    private val args: NewDeviceFragmentArgs by navArgs()
    private lateinit var deviceType: String
    private lateinit var addedDevice :AddedDevice
    private lateinit var myRoomRepository: MyRoomRepository
    private lateinit var deviceRepository: DeviceRepository



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view.root.findNavController().navigateUp()
            }
        })
        myRoomRepository = MyRoomRepository(requireContext())
        deviceRepository = DeviceRepository(requireContext())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MainActivity.instance.bottomNavigationView.visibility = View.GONE

        // Inflate the layout for this fragment
        view= FragmentNewDeviceBinding.inflate(inflater, container, false)

        addedDevice= AddedDevice(args.scanResult)

        view.editTextTextPersonName.setText(addedDevice.name)
        val roomsArray= myRoomRepository.getAllRooms()

        view.textview.text=Scanner.uuids

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
                val password = view.editTextTextPassword3.text.toString()
                val description =view.editTextTextPersonName.text.toString()

                val device = Device(MainApplication.currentUserID,password,deviceType,description,1,"very high quality my friend",1,"Tylko huta",addedDevice.address)
                deviceRepository.addDevice(device)
                addedDevice.setPassword(password)
                addedDevice.name=description
                addedDevice.type=deviceType

                MainApplication.currentUser?.addDevice(addedDevice)
                Navigation.findNavController(view.root).navigateUp()
            }
        }

        return view.root
    }

}