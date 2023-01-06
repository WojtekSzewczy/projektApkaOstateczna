package com.example.myapplication.viewHolders

import android.util.Log
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Room
import com.example.myapplication.databinding.RoomLayoutBinding
import com.example.myapplication.mainFragments.RoomsFragmentDirections

class RoomViewHolder(private val binding: RoomLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(room: Room) {
        onClick(room)
        setText(room)
    }

    private fun onClick(room: Room) {
        binding.room.setOnClickListener {

            Log.v("click", room.id.toString())
            val action = RoomsFragmentDirections.actionRoomsFragmentToSelectedRoomFragment(room.id)
            Navigation.findNavController(binding.root).navigate(action)


        }
    }


    private fun setText(room: Room) {
        binding.roomAddress.text = room.id.toString()
    }

}