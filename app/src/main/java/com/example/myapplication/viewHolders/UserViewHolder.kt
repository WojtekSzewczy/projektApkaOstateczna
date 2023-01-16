package com.example.myapplication.viewHolders

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapters.UsersAdapter
import com.example.myapplication.MainApplication
import com.example.myapplication.databaseHandlers.models.MyRoom
import com.example.myapplication.databaseHandlers.models.User
import com.example.myapplication.databaseHandlers.repositories.UserRepository
import com.example.myapplication.databinding.RoomLayoutBinding
import com.example.myapplication.databinding.UserLayoutBinding
import com.example.myapplication.mainFragments.RoomsFragmentDirections

class UserViewHolder(private val binding: UserLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @RequiresApi(Build.VERSION_CODES.O)
    val userRepository=UserRepository(MainApplication.appContext)
    private lateinit var adapter :UsersAdapter


    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(user : User) {

        setText(user)
        binding.imageView.setOnClickListener {
            if(user.UserID==MainApplication.adminID){
                Toast.makeText(MainApplication.appContext,"user cant be removed",Toast.LENGTH_SHORT).show()
            }
            else{
                userRepository.deleteUser(user)
                adapter.submitList(userRepository.getAllUsers())
                adapter.notifyDataSetChanged()

            }


        }
    }




    @RequiresApi(Build.VERSION_CODES.O)
    private fun setText(user : User) {
        binding.userName.text=user.Name
        binding.userSurname.text=user.Surname
        binding.userId.text=user.UserID.toString()
    }
    fun getAdapter(adapter: UsersAdapter){
        this.adapter=adapter
    }

}