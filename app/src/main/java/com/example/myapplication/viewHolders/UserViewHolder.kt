package com.example.myapplication.viewHolders

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databaseHandlers.models.MyRoom
import com.example.myapplication.databaseHandlers.models.User
import com.example.myapplication.databinding.RoomLayoutBinding
import com.example.myapplication.databinding.UserLayoutBinding
import com.example.myapplication.mainFragments.RoomsFragmentDirections

class UserViewHolder(private val binding: UserLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(user : User) {

        setText(user)
    }




    @RequiresApi(Build.VERSION_CODES.O)
    private fun setText(user : User) {
        binding.userName.text=user.Name
        binding.userSurname.text=user.Surname
        binding.userId.text=user.UserID.toString()
    }

}