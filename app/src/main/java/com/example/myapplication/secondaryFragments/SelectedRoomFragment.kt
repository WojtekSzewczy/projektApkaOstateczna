package com.example.myapplication.secondaryFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.FragmentSelectedRoomBinding

class SelectedRoomFragment : Fragment() {

    private lateinit var view: FragmentSelectedRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view.root.findNavController().navigateUp()
            }
        })
    }
//TODO sprawdzenie czy jest autorem user, i w zaleznosci od tego edycja
    //TODO dolaczanie do pokoju ewentualnie wyswietlic urządzenia
    //TODO haslo do pokoju limit osób
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MainActivity.instance.bottomNavigationView.visibility = View.GONE

        // Inflate the layout for this fragment
        view= FragmentSelectedRoomBinding.inflate(inflater, container, false)

        return view.root
    }

}