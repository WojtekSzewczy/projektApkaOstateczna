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
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRegisterBinding
import com.example.myapplication.databaseModels.User
import com.example.myapplication.databaseHandlers.DatabaseHelper


class RegisterFragment : Fragment() {

    private lateinit var view: FragmentRegisterBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = DatabaseHelper(requireContext())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view = FragmentRegisterBinding.inflate(inflater, container, false)

        view.button3.setOnClickListener {
            val login = view.Login.text.toString()
            val name = view.Name.text.toString()
            val surname = view.Surname.text.toString()
            val position = view.Position.text.toString()
            val email = view.editTextTextEmailAddress.text.toString()
            val password = view.Password.text.toString()
            val password2 = view.RepeatPassword.text.toString()

            // TODO add more fields to register

            if (login == "" || password == "" || password2 == "" || name == "" || surname == "" || position == "" || email == "") {
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
            } else if (password != password2) {
                Toast.makeText(requireContext(), "Passwords are not the same", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "User created", Toast.LENGTH_SHORT).show()
                val user = User(name, surname, position, email, login, password)
                db.addUser(user)
                Toast.makeText(requireContext(), "User created", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view.root)
                    .navigate(R.id.action_registerFragment_to_mainFragment3)
            }
        }

        return view.root
    }

}