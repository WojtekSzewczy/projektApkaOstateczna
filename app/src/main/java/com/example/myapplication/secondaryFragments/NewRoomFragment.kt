package com.example.myapplication.secondaryFragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import com.example.myapplication.MainActivity
import com.example.myapplication.MainApplication
import com.example.myapplication.databaseHandlers.models.MyRoom
import com.example.myapplication.databaseHandlers.repositories.MyRoomRepository
import com.example.myapplication.databinding.FragmentNewRoomBinding
import java.util.*

class NewRoomFragment : Fragment() {

    private lateinit var view: FragmentNewRoomBinding
    private lateinit var myRoomRepository: MyRoomRepository


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view.root.findNavController().navigateUp()
            }
        })
        myRoomRepository = MyRoomRepository(requireContext())

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MainActivity.instance.bottomNavigationView.visibility = View.GONE
        val calendar = Calendar.getInstance()
        val currentDate = calendar.time.toString()

        val roomName = view.roomName.text.toString()
        val roomPassword =view.roomPassword.text.toString()
        val roomMax= view.roomMax.text.toString().toInt()
        val myRoom= MyRoom(MainApplication.currentUserID,roomName,roomPassword,roomMax,currentDate,currentDate)

        view= FragmentNewRoomBinding.inflate(inflater, container, false)
        view.addRoomToDatabaseButton.setOnClickListener {
            myRoomRepository.addRoom(myRoom)
            view.root.findNavController().navigateUp()

        }

        return view.root
    }

}