package com.example.myapplication

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.navigation.findNavController
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
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view.root.findNavController().navigateUp()
            }
        })
        userRepository = UserRepository(requireContext())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view = FragmentAccountBinding.inflate(inflater, container, false)

        view.logOut.setOnClickListener {
            MainApplication.currentUser = null
            MainApplication.currentUserID = -1
            MainApplication.isAdmin = false
            MainActivity.instance.bottomNavigationView.visibility = View.INVISIBLE
            Navigation.findNavController(view.root).navigate(R.id.action_accountFragment2_to_mainFragment3)
        }

        view.changePassword.setOnClickListener {
            if(view.Password.text.toString() == view.RepeatPassword.text.toString()){
                userRepository.updatePassword(MainApplication.currentUser!!.Email, view.Password.text.toString())
                Toast.makeText(requireContext(), "Password changed", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(requireContext(), "Passwords don't match", Toast.LENGTH_SHORT).show()
            }
        }

        view.removeAccount.setOnClickListener {
            userRepository.deleteUser(MainApplication.currentUser!!)
            MainApplication.currentUser = null
            MainApplication.currentUserID = -1
            MainApplication.isAdmin = false
            MainActivity.instance.bottomNavigationView.visibility = View.INVISIBLE
            Navigation.findNavController(view.root).navigate(R.id.action_accountFragment2_to_mainFragment3)
        }

        view.changeEmail.setOnClickListener {
            if(view.newEmail.text.toString() != ""){
                userRepository.updateEmail(MainApplication.currentUser!!.Email, view.newEmail.text.toString())
                Toast.makeText(requireContext(), "Email changed", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(requireContext(), "Email can't be empty", Toast.LENGTH_SHORT).show()
            }
        }


        return view.root
    }
}