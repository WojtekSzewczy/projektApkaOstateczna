package com.example.myapplication.viewHolders

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databaseHandlers.models.MyRoom
import com.example.myapplication.databinding.RoomLayoutBinding
import com.example.myapplication.mainFragments.RoomsFragmentDirections

class RoomViewHolder(private val binding: RoomLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(myRoom: MyRoom) {
        onClick(myRoom)
        setText(myRoom)
    }

    private fun onClick(myRoom: MyRoom) {
        binding.room.setOnClickListener {
            val action = RoomsFragmentDirections.actionRoomsFragmentToSelectedRoomFragment(myRoom.roomID)
            Navigation.findNavController(binding.root).navigate(action)
        }
    }


    private fun setText(myRoom: MyRoom) {
        binding.roomAddress.text = myRoom.name
    }

}