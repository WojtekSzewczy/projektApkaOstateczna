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
import com.example.myapplication.MainActivity
import com.example.myapplication.MainApplication
import com.example.myapplication.databaseHandlers.models.MyRoom
import com.example.myapplication.databaseHandlers.models.UserRoom
import com.example.myapplication.databaseHandlers.repositories.MyRoomRepository
import com.example.myapplication.databaseHandlers.repositories.UserRoomRepository
import com.example.myapplication.databinding.FragmentSelectedRoomBinding

@RequiresApi(Build.VERSION_CODES.O)
class SelectedRoomFragment : Fragment() {

    private lateinit var view: FragmentSelectedRoomBinding
    private val args: SelectedRoomFragmentArgs by navArgs()
    private lateinit var myRoom: MyRoom
    private lateinit var roomRepository: MyRoomRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        roomRepository = MyRoomRepository(requireContext())
        myRoom = roomRepository.getRoom(args.roomID)
        Log.v("SelectedRoomFragment", myRoom.name)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view.root.findNavController().navigateUp()
            }
        })
    }

//TODO sprawdzenie czy jest autorem user, i w zaleznosci od tego edycja
    //TODO dolaczanie do pokoju ewentualnie wyswietlic urządzenia
    //TODO haslo do pokoju limit osób

    //TODO urzadzenia w pokoju
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MainActivity.instance.bottomNavigationView.visibility = View.GONE

        // Inflate the layout for this fragment
        view= FragmentSelectedRoomBinding.inflate(inflater, container, false)

        view.removeRoomButton.setOnClickListener {
            if (MainApplication.isAdmin || MainApplication.currentUserID == myRoom.ownerID)
            {
                roomRepository.deleteRoom(myRoom)
                view.root.findNavController().navigateUp()
            }
            else
            {
                Toast.makeText(requireContext(), "No admin privileges for you", Toast.LENGTH_SHORT).show()
            }

        }

        view.assingSelfToRoomButton.setOnClickListener {

            if (myRoom.password == view.roomPasswordField.text.toString())
            {
                val userRoomRepository = UserRoomRepository(requireContext())
                val userRoom = UserRoom(MainApplication.currentUserID, myRoom.roomID)
                if (!userRoomRepository.checkIfUserRoomExists(userRoom.userID, userRoom.roomID))
                {
                    userRoomRepository.addUserRoom(userRoom)
                    Toast.makeText(requireContext(), "You've been assigned to this room", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(requireContext(), "You are already in this room", Toast.LENGTH_SHORT).show()
                }


            }
            else
            {
                Toast.makeText(requireContext(), "Wrong password", Toast.LENGTH_SHORT).show()
            }

        }

        return view.root
    }

    //write a function displaying information about room

}