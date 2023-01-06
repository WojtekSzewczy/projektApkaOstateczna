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
    private val viewModel : RoomsViewModel by viewModels()


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
        view = FragmentRoomsBinding.inflate(inflater, container, false)
        adapter.submitList(MainApplication.rooms)

        view.roomsList.adapter=adapter

        view.addRoomButton.setOnClickListener {
            viewModel.addRoom()
        }

        observeViewModelState()


        val newUser = User("Paweł","Kowalski","Developer","pk@gmail.com","pk","123")

        val db = DatabaseHelper(requireContext())
        db.addUser(newUser)


        return view.root
    }

    private fun observeViewModelState() {
        viewModel.roomsLiveData.observe(viewLifecycleOwner){rooms ->
            adapter.apply { submitList(rooms) }
        }
    }

}


