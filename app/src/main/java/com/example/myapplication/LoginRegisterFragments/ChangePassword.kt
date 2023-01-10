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
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databaseHandlers.databases.MyAppDatabase
import com.example.myapplication.databaseHandlers.repositories.UserRepository
import com.example.myapplication.databinding.FragmentChangePasswordBinding
import com.example.myapplication.databinding.FragmentRegisterBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChangePassword.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChangePassword : Fragment() {
    private lateinit var view: FragmentChangePasswordBinding
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

        view = FragmentChangePasswordBinding.inflate(inflater, container, false)
        val email = view.Email.text.toString()

        val password1 = view.newPassword.text.toString()
        val password2 = view.repeatPassword.text.toString()
        var finalPassword:String= ""

        view.button4.setOnClickListener {
            if (password1.equals(password2)){
                finalPassword=password1
            }

            //TODO change password in database
            //TODO mail

            view.root.findNavController().navigateUp()
        }





        return view.root
    }


}