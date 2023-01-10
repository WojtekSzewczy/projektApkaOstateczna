package com.example.myapplication.LoginRegisterFragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import com.example.myapplication.MainActivity
import com.example.myapplication.MainApplication
import com.example.myapplication.R
import com.example.myapplication.databaseHandlers.repositories.UserRepository
import com.example.myapplication.databinding.FragmentMainBinding
import java.security.MessageDigest
import java.util.Base64


class LoginFragment : Fragment() {

    private lateinit var view: FragmentMainBinding
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
        view = FragmentMainBinding.inflate(inflater, container, false)

        view.button.setOnClickListener {

            val login = view.editTextTextLogin.text.toString()
            val password = view.editTextNumberPassword.text.toString()
            val user = userRepository.getUser(login, password)
            if (user != null) {
                MainApplication.currentUser = user
                MainApplication.currentUserID=user.UserID

                if(user.UserID==MainApplication.adminID){
                    MainApplication.isAdmin=true
                }

                Navigation.findNavController(view.root).navigate(R.id.action_mainFragment3_to_devicesFragment)

                MainActivity.instance.bottomNavigationView.visibility = View.VISIBLE
            } else {
                Toast.makeText(requireContext(), "No user with given email or password", Toast.LENGTH_SHORT).show()
            }
        }

        view.rejestrujTextView.setOnClickListener {
            Navigation.findNavController(view.root)
                .navigate(R.id.action_mainFragment3_to_registerFragment)

        }
        view.passwordChange.setOnClickListener {
            Navigation.findNavController(view.root)
                .navigate(R.id.action_mainFragment3_to_changePassword)
        }

        return view.root
    }

}