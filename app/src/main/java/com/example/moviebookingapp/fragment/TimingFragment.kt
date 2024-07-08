package com.example.moviebookingapp.fragment

import TimingAdapter
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviebookingapp.R
//import com.example.moviebookingapp.RecyleView.TimingAdapter
import com.example.moviebookingapp.databinding.FragmentTimingBinding


class TimingFragment : Fragment(R.layout.fragment_timing) {
    private var _binding: FragmentTimingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimingBinding.inflate(inflater, container, false)

        binding.spMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    context,
                    "You Selected ${parent?.getItemAtPosition(position).toString()}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }


        }
        binding.spDays.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    context,
                    "You Selected ${parent?.getItemAtPosition(position).toString()}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }


        }

//        binding.btnContinue.setOnClickListener {
//            val fragmentManager = requireActivity().supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.frameLayout, SeatBooking()) // Replace YourNewFragment with the actual fragment class you want to navigate to
//            fragmentTransaction.addToBackStack(null) // This allows the user to press the back button to navigate back to the previous fragment
//            fragmentTransaction.commit()
//        }
        binding.btnContinue.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.custom_alert_dialog, null)
            val dialogBuilder = AlertDialog.Builder(requireContext())
                .setView(dialogView)
            val alertDialog = dialogBuilder.create()
            alertDialog.show()

            // Now you can find views and set click listeners for the buttons
            val btnAccept = dialogView.findViewById<Button>(R.id.btnAccept)
            val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)

            btnAccept.setOnClickListener {
                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(
                    R.id.frameLayout,
                    SeatBooking()
                )
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
                alertDialog.dismiss()
            }

            btnCancel.setOnClickListener {
                alertDialog.dismiss()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sample data
        val theaterNames = listOf(
            "INOX Cinemas",
            "Cinepolis Miraj",
            "Cinemas",
            "Wave Cinemas"
        )

        val adapter = TimingAdapter(theaterNames)
        binding.rvTiming.adapter = adapter
        binding.rvTiming.layoutManager = LinearLayoutManager(requireContext())


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}