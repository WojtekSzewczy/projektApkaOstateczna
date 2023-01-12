package com.example.myapplication.secondaryFragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.Adapters.DeviceInRoomAdapter
import com.example.myapplication.Adapters.UsersInRoomAdapter
import com.example.myapplication.MainActivity
import com.example.myapplication.MainApplication
import com.example.myapplication.databaseHandlers.models.Device
import com.example.myapplication.databaseHandlers.models.MyRoom
import com.example.myapplication.databaseHandlers.models.User
import com.example.myapplication.databaseHandlers.models.UserRoom
import com.example.myapplication.databaseHandlers.repositories.*
import com.example.myapplication.databinding.FragmentSelectedRoomBinding

@RequiresApi(Build.VERSION_CODES.O)
class SelectedRoomFragment : Fragment() {

    private lateinit var view: FragmentSelectedRoomBinding
    private val args: SelectedRoomFragmentArgs by navArgs()
    private lateinit var myRoom: MyRoom
    private lateinit var roomRepository: MyRoomRepository
    private lateinit var userRoomRepository: UserRoomRepository
    private lateinit var roomHistoryRepository: RoomHistoryRepository
    private lateinit var deviceRepository: DeviceRepository
    private lateinit var userRepository: UserRepository
    private val usersList = mutableListOf<User>()
    private val devicesList = mutableListOf<Device>()
    private val usersAdapter = UsersInRoomAdapter()
    private val devicesAdapter = DeviceInRoomAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        roomRepository = MyRoomRepository(requireContext())
        userRoomRepository = UserRoomRepository(requireContext())
        roomHistoryRepository = RoomHistoryRepository(requireContext())
        userRepository = UserRepository(requireContext())
        deviceRepository = DeviceRepository(requireContext())

        myRoom = roomRepository.getRoom(args.roomID)

        getUsers()
        getDevices()


        Log.v("SelectedRoomFragment", myRoom.name)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view.root.findNavController().navigateUp()
            }
        })
    }

    private fun getUsers() {
        var usersID: List<Int> = userRoomRepository.getUsersID(args.roomID)
        usersList.clear()
        usersID.forEach {
            usersList.add(userRepository.getUserByID(it))
        }
    }

    private fun getDevices() {
        val devicesID: List<Int> = roomHistoryRepository.getDevicesId(args.roomID)
        devicesID.forEach {
            devicesList.add(deviceRepository.getDeviceById(it))
        }

    }

    //TODO haslo do pokoju limit osób

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        MainActivity.instance.bottomNavigationView.visibility = View.GONE

        // Inflate the layout for this fragment
        view = FragmentSelectedRoomBinding.inflate(inflater, container, false)

        usersAdapter.submitList(usersList)
        devicesAdapter.submitList(devicesList)

        view.devicesList.adapter = devicesAdapter
        view.userList.adapter = usersAdapter

        removeRoom()

        assignSelfToRoom()

        return view.root
    }


    private fun assignSelfToRoom() {
        view.assingSelfToRoomButton.setOnClickListener {

            if (myRoom.password == view.roomPasswordField.text.toString()) {
                val userRoomRepository = UserRoomRepository(requireContext())
                val userRoom = UserRoom(MainApplication.currentUserID, myRoom.roomID)
                if (!userRoomRepository.checkIfUserRoomExists(userRoom.userID, userRoom.roomID)) {
                    userRoomRepository.addUserRoom(userRoom)
                    Toast.makeText(
                        requireContext(), "You've been assigned to this room", Toast.LENGTH_SHORT
                    ).show()
                    getUsers()
                    usersAdapter.submitList(usersList)
                    usersAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(
                        requireContext(), "You are already in this room", Toast.LENGTH_SHORT
                    ).show()
                }


            } else {
                Toast.makeText(requireContext(), "Wrong password", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun removeRoom() {
        view.removeRoomButton.setOnClickListener {
            if (MainApplication.isAdmin || MainApplication.currentUserID == myRoom.ownerID) {
                roomRepository.deleteRoom(myRoom)
                view.root.findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "No admin privileges for you", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    //write a function displaying information about room

}