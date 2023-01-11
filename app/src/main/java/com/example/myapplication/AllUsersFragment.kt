package com.example.myapplication

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import com.example.myapplication.Adapters.AddedDeviceAdapter
import com.example.myapplication.Adapters.UsersAdapter
import com.example.myapplication.databaseHandlers.repositories.DeviceRepository
import com.example.myapplication.databaseHandlers.repositories.UserRepository
import com.example.myapplication.databinding.FragmentAllUsersBinding
import com.example.myapplication.databinding.FragmentMyDevicesBinding

class AllUsersFragment : Fragment() {

    private lateinit var view : FragmentAllUsersBinding
    private val adapter = UsersAdapter()
    private lateinit var usersRepository: UserRepository


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        })
        usersRepository = UserRepository(requireContext())
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MainActivity.instance.bottomNavigationView.visibility = View.VISIBLE

        // Inflate the layout for this fragment
        view =  FragmentAllUsersBinding.inflate(inflater, container, false)
        adapter.submitList(usersRepository.getAllUsers())
        view.usersList.adapter=adapter




        return view.root
    }
    }
