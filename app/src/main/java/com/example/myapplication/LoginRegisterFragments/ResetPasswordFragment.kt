package com.example.myapplication.LoginRegisterFragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import com.example.myapplication.databaseHandlers.repositories.UserRepository
import com.example.myapplication.databinding.FragmentResetPasswordBinding

import com.mailslurp.models.*
import com.mailslurp.infrastructure.*
import com.mailslurp.apis.*

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.util.*



class ResetPasswordFragment : Fragment() {
    private lateinit var view: FragmentResetPasswordBinding
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

        view = FragmentResetPasswordBinding.inflate(inflater, container, false)
        val email = view.Email.text.toString()
        val verificationCode: Int = (1000..9999).random()

        view.sendEmailButton.setOnClickListener {
            if (userRepository.checkIfUserExistsByEmail(view.Email.text.toString())) {
                Log.v("Email", userRepository.checkIfUserExistsByEmail(view.Email.text.toString()).toString())

                GlobalScope.launch {
                    try {
                        val inboxController =
                            InboxControllerApi("3003dc4735923fd2116018182dc39ae8323714a78b4e4fe135f9c0d128f9a2e7")
                        val inbox = inboxController.createInbox(null, null, null, null, null, null, null, null, null)

                        inboxController.sendEmail(
                            inbox.id!!,
                            SendEmailOptions(
                                to = listOf(view.Email.text.toString()),
                                subject = "Reset Password",
                                body = "Verification code: $verificationCode",
                            )
                        )
                    } catch (e: Exception) {
                        Log.d("Error", e.toString())
                    }
                }
            }
            else
            {
                Toast.makeText(requireContext(), "No user with given email", Toast.LENGTH_SHORT).show()
            }
        }

        view.button4.setOnClickListener {
            if (verificationCode == view.EmailCode.text.toString().toInt()) {
                if (view.newPassword.text.toString() == view.repeatPassword.text.toString()) {

                    var newPassword = view.newPassword.text.toString()

                    val md = MessageDigest.getInstance("SHA-256")
                    val passwordBytes = newPassword.toByteArray(Charsets.UTF_8)
                    val hash = md.digest(passwordBytes)
                    newPassword = Base64.getEncoder().encodeToString(hash)

                    userRepository.updatePassword(view.Email.text.toString(), newPassword)

                    Toast.makeText(requireContext(), "Password changed", Toast.LENGTH_SHORT).show()
                    view.root.findNavController().navigateUp()
                }
                else
                {
                    Toast.makeText(requireContext(), "Passwords don't match", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(requireContext(), "Wrong verification code", Toast.LENGTH_SHORT).show()
            }
        }

        return view.root
    }


}