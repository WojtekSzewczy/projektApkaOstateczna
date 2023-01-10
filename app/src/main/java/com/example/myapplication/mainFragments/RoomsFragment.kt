package com.example.myapplication.mainFragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import com.example.myapplication.Adapters.RoomsAdapter
import com.example.myapplication.MainActivity
import com.example.myapplication.databaseHandlers.repositories.MyRoomRepository
import com.example.myapplication.databinding.FragmentRoomsBinding


class RoomsFragment : Fragment() {
   private lateinit var view: FragmentRoomsBinding
    private val adapter = RoomsAdapter()
   // private val  : RoomsViewModel by viewModels()
    private lateinit var myRoomRepository: MyRoomRepository



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        })
        myRoomRepository = MyRoomRepository(requireContext())


    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MainActivity.instance.bottomNavigationView.visibility = View.VISIBLE

        // Inflate the layout for this fragment
        view = FragmentRoomsBinding.inflate(inflater, container, false)
        adapter.submitList(myRoomRepository.getAllRooms())

        view.roomsList.adapter=adapter

        view.addRoomButton.setOnClickListener {

            val action = RoomsFragmentDirections.actionRoomsFragmentToNewRoom()
            Navigation.findNavController(view.root).navigate(action)

        }

        return view.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        adapter.submitList(myRoomRepository.getAllRooms())
    }
}


