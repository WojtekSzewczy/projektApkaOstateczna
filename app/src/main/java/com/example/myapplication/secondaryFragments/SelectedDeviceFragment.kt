package com.example.myapplication.secondaryFragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.MainActivity
import com.example.myapplication.MainApplication
import com.example.myapplication.databaseHandlers.models.RoomHistory
import com.example.myapplication.databaseHandlers.repositories.*
import com.example.myapplication.databinding.FragmentSelectedDeviceBinding
import java.text.SimpleDateFormat
import java.util.*

class SelectedDeviceFragment : Fragment() {

    private lateinit var view: FragmentSelectedDeviceBinding
    private lateinit var myRoomRepository: MyRoomRepository
    private lateinit var deviceRepository: DeviceRepository
    private lateinit var roomHistoryRepository: RoomHistoryRepository
    private lateinit var reservationRepository: ReservationRepository
    private lateinit var reservationDeviceRepository : ReservationDeviceRepository
    private val args: SelectedDeviceFragmentArgs by navArgs()
    private var selectedRoomId =-1
    var cal = Calendar.getInstance()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view.root.findNavController().navigateUp()
            }
        })
        myRoomRepository= MyRoomRepository(requireContext())
        deviceRepository= DeviceRepository(requireContext())
        roomHistoryRepository= RoomHistoryRepository(requireContext())
        reservationRepository = ReservationRepository(requireContext())
        reservationDeviceRepository = ReservationDeviceRepository(requireContext())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MainActivity.instance.bottomNavigationView.visibility = View.GONE
        view= FragmentSelectedDeviceBinding.inflate(inflater, container, false)

        val allRooms=myRoomRepository.getAllRooms()
        val roomsName= mutableListOf<Int>()
        allRooms.forEach {
            roomsName.add(it.roomID)
        }
        view.deviceName.text= deviceRepository.getDeviceById(args.deviceID).description


        val arrayAdapter=ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,roomsName)

        val mTimePicker: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)

        val mTimePickerEnd: TimePickerDialog
        val hourEnd = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minuteEnd = mcurrentTime.get(Calendar.MINUTE)

        mTimePicker = TimePickerDialog(requireContext(), object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(timePicker: TimePicker?, hourOfDay: Int, minute: Int) {
                view.startTime.setText(String.format("%d : %d", hourOfDay, minute))
            }
        }, hour, minute, false)

        view.startTimeButton.setOnClickListener { v ->
            mTimePicker.show()
        }

        mTimePickerEnd = TimePickerDialog(requireContext(), object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(timePicker: TimePicker?, hourOfDay: Int, minute: Int) {
                view.endTime.setText(String.format("%d : %d", hourOfDay, minute))
            }
        }, hourEnd, minuteEnd, false)

        view.startTimeButton.setOnClickListener { v ->
            mTimePicker.show()
        }

        view.endTimeButton.setOnClickListener { v ->
            mTimePickerEnd.show()
        }


        // Inflate the layout for this fragment

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateStart()
            }
        }
        val dateSetListenerEnd = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateEnd()
            }
        }
        view.startDateButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(requireContext(),
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })

        view.EndDateButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View) {
                DatePickerDialog(requireContext(),
                    dateSetListenerEnd,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })

        view.spinnerRooms.adapter=arrayAdapter
        view.spinnerRooms.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedRoomId=parent?.getItemAtPosition(position).toString().toInt()


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedRoomId=parent?.getItemAtPosition(0).toString().toInt()
            }

        }
        view.AcceptReservation.setOnClickListener {
            if(view.devicePassword.text.toString()==deviceRepository.getDevicePassword(args.deviceID)){
                addReservation()
                view.root.findNavController().navigateUp()
            }
            else{
                Toast.makeText(requireContext(),"wrong password", Toast.LENGTH_SHORT).show()
            }
        }

        return view.root
    }

    private fun updateDateEnd() {
        val myFormat = "yyyy/dd/MM" // mention the format you need
        val sdf = SimpleDateFormat(myFormat)
        view.EndDate.text = sdf.format(cal.time)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addReservation() {

        val  endString = view.EndDate.text.toString()+"T"+view.endTime.text.toString()
        val startString= view.startDate.text.toString()+"T"+view.startTime.text.toString()

        val roomHistory= RoomHistory(selectedRoomId,args.deviceID)
        roomHistoryRepository.addNew(roomHistory)
        reservationRepository.addReservation(MainApplication.currentUserID,startString,endString)
        val reservationId=reservationRepository.getReservationId(MainApplication.currentUserID,startString,endString)

        reservationDeviceRepository.addReservationDeice(reservationId,args.deviceID)


    }
    private fun updateDateStart() {
        val myFormat = "yyyy/dd/MM" // mention the format you need
        val sdf = SimpleDateFormat(myFormat)
        view.startDate.text = sdf.format(cal.time)
    }

}