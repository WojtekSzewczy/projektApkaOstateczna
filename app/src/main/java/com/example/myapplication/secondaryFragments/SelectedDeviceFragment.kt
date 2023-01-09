package com.example.myapplication.secondaryFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.FragmentSelectedDeviceBinding

class SelectedDeviceFragment : Fragment() { // TODO dodac kolejny fragment w

    private lateinit var view: FragmentSelectedDeviceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view.root.findNavController().navigateUp()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MainActivity.instance.bottomNavigationView.visibility = View.GONE
        view.spinnerRooms.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //TODO przypisuje urzadzenie do wybranej sali, tworzy roomHistoy


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }


        // Inflate the layout for this fragment
        view= FragmentSelectedDeviceBinding.inflate(inflater, container, false)
        view.StartTime
        view.AcceptReservation.setOnClickListener {
            addReservation()
        }

        return view.root
    }

    private fun addReservation() {
        TODO("Not yet implemented")

    }

}