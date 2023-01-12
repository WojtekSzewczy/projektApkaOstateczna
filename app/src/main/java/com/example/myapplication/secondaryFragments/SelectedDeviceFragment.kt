package com.example.myapplication.secondaryFragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.Adapters.AddedDeviceAdapter
import com.example.myapplication.Adapters.DeviceInRoomAdapter
import com.example.myapplication.Adapters.UsersInRoomAdapter
import com.example.myapplication.MainActivity
import com.example.myapplication.databaseHandlers.models.RoomHistory
import com.example.myapplication.databaseHandlers.repositories.DeviceRepository
import com.example.myapplication.databaseHandlers.repositories.MyRoomRepository
import com.example.myapplication.databaseHandlers.repositories.RoomHistoryRepository
import com.example.myapplication.databinding.FragmentSelectedDeviceBinding

class SelectedDeviceFragment : Fragment() {

    private lateinit var view: FragmentSelectedDeviceBinding
    private lateinit var myRoomRepository: MyRoomRepository
    private lateinit var deviceRepository: DeviceRepository
    private lateinit var roomHistoryRepository: RoomHistoryRepository
    private val args: SelectedDeviceFragmentArgs by navArgs()
    private var selectedRoomId =-1


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view.root.findNavController().navigateUp()
            }
        })
        myRoomRepository= MyRoomRepository(requireContext())
        deviceRepository= DeviceRepository(requireContext())
        roomHistoryRepository= RoomHistoryRepository(requireContext())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MainActivity.instance.bottomNavigationView.visibility = View.GONE

        val allRooms=myRoomRepository.getAllRooms()
        val roomsName= mutableListOf<Int>()
        allRooms.forEach {
            roomsName.add(it.roomID)
        }


        val arrayAdapter=ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,roomsName)



        // Inflate the layout for this fragment
        view= FragmentSelectedDeviceBinding.inflate(inflater, container, false)
        view.StartTime

        view.spinnerRooms.adapter=arrayAdapter
        view.spinnerRooms.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedRoomId=parent?.getItemAtPosition(position).toString().toInt()


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        view.AcceptReservation.setOnClickListener {
            if(view.devicePassword.text.toString()==deviceRepository.getDevicePassword(args.deviceID)){
                addReservation()
            }
            else{
                Toast.makeText(requireContext(),"wrong password", Toast.LENGTH_SHORT).show()
            }
        }

        return view.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addReservation() {
        val roomHistory= RoomHistory(selectedRoomId,args.deviceID)
        roomHistoryRepository.addNew(roomHistory)
        Log.v("SelectedDeviceFragment","addReservation")

    }

}