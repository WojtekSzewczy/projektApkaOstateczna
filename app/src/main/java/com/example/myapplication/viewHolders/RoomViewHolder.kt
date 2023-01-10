package com.example.myapplication.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databaseHandlers.models.MyRoom
import com.example.myapplication.databinding.RoomLayoutBinding

class RoomViewHolder(private val binding: RoomLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(myRoom: MyRoom) {
        onClick(myRoom)
        setText(myRoom)
    }

    private fun onClick(myRoom: MyRoom) {
        binding.room.setOnClickListener {

           // Log.v("click", room.id.toString())
            //val action = RoomsFragmentDirections.actionRoomsFragmentToSelectedRoomFragment(room.id)
            //Navigation.findNavController(binding.root).navigate(action)


        }
    }


    private fun setText(myRoom: MyRoom) {
        binding.roomAddress.text = "dupa"
    }

}