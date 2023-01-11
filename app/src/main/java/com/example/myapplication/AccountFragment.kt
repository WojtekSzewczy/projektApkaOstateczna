package com.example.myapplication

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import com.example.myapplication.databaseHandlers.models.User
import com.example.myapplication.databaseHandlers.repositories.UserRepository
import com.example.myapplication.databinding.FragmentAccountBinding
import com.example.myapplication.databinding.FragmentRegisterBinding


class AccountFragment : Fragment() {
    private lateinit var view: FragmentAccountBinding
    private lateinit var userRepository: UserRepository

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userRepository = UserRepository(requireContext())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view = FragmentAccountBinding.inflate(inflater, container, false)



        return view.root
    }
}