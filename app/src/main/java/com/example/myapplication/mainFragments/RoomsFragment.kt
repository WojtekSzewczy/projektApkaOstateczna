package com.example.myapplication.mainFragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.myapplication.Adapters.AddedDeviceAdapter
import com.example.myapplication.Adapters.RoomsAdapter
import com.example.myapplication.MainActivity
import com.example.myapplication.MainApplication
import com.example.myapplication.R
import com.example.myapplication.databaseHandlers.DatabaseHelper
import com.example.myapplication.databaseModels.User
import com.example.myapplication.databinding.FragmentRoomsBinding
import com.example.myapplication.mainFragments.viewModels.DevicesViewModel
import com.example.myapplication.mainFragments.viewModels.RoomsViewModel


class RoomsFragment : Fragment() {
   private lateinit var view: FragmentRoomsBinding
    private val adapter = RoomsAdapter()
   // private val  : RoomsViewModel by viewModels()
    private lateinit var db: DatabaseHelper



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        })
        db = DatabaseHelper(requireContext())


    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MainActivity.instance.bottomNavigationView.visibility = View.VISIBLE

        // Inflate the layout for this fragment
        view = FragmentRoomsBinding.inflate(inflater, container, false)

        adapter.submitList(db.getAllRooms())

        view.roomsList.adapter=adapter

        view.addRoomButton.setOnClickListener {
            Navigation.findNavController(view.root).navigate(R.id.action_roomsFragment_to_newRoomFragment2)


            //val action = RoomsFragmentDirections.actionRoomsFragmentToNewRoom()

           // Navigation.findNavController(view.root).navigate(action)

        }

        return view.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        adapter.submitList(db.getAllRooms())


    }



}


