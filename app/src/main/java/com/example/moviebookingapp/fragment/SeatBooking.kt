package com.example.moviebookingapp.fragment

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.moviebookingapp.R
import com.example.moviebookingapp.databinding.FragmentSeatBookingBinding

class SeatBooking : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSeatBookingBinding

    private val seats =
        "_UUUUUUAAAAARRRR_/" +
                "_________________/" +
                "UU__AAAARRRRR__RR/" +
                "UU__UUUAAAAAA__AA/" +
                "AA__AAAAAAAAA__AA/" +
                "AA__AARUUUURR__AA/" +
                "UU__UUUA_RRRR__AA/" +
                "AA__AAAA_RRAA__UU/" +
                "AA__AARR_UUUU__RR/" +
                "AA__UUAA_UURR__RR/" +
                "_________________/" +
                "UU_AAAAAAAUUUU_RR/" +
                "RR_AAAAAAAAAAA_AA/" +
                "AA_UUAAAAAUUUU_AA/" +
                "AA_AAAAAAUUUUU_AA/" +
                "_________________/"

    private val seatViewList = mutableListOf<TextView>()
    private val seatSize = 100
    private val seatGaping = 10

    private val STATUS_AVAILABLE = 1
    private val STATUS_BOOKED = 2
    private val STATUS_RESERVED = 3
    private val seatPrice = 200 // Price per seat
    private var selectedIds = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeatBookingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeSeatLayout()
        val btnBook: Button = view.findViewById(R.id.btnBook)
        btnBook.setOnClickListener {
            // Inflate the layout file for the confirmation screen
            val confirmationView = layoutInflater.inflate(R.layout.confirmticket, null)

            // Find the TextView in the confirmation layout to display the seat selection status
            val seatStatusTextView = confirmationView.findViewById<TextView>(R.id.totalPrice)

            // Determine the seat selection status based on the selectedIds
            val seatSelectionStatus = if (selectedIds.isNotEmpty()) "Yes" else "No"

            // Set the seat selection status in the TextView
            seatStatusTextView.text = "Seat Selection: $seatSelectionStatus"

            // Find the TextView for displaying the total price
            val totalPriceTextView = confirmationView.findViewById<TextView>(R.id.totalPrice)

            // Calculate the total price based on the selected seats
            val selectedSeatCount = selectedIds.split(",").filter { it.isNotEmpty() }.size
            val totalPrice = selectedSeatCount * seatPrice

            // Set the total price in the TextView
            totalPriceTextView.text = "$totalPrice rs"

            // Open the layout file in a dialog
            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder.setView(confirmationView)
            val dialog = dialogBuilder.create()
            dialog.show()
        }

    }

    private fun initializeSeatLayout() {
        val seatsLayout = "/" + seats

        var layout: LinearLayout? = null
        var count = 0

        for (index in seatsLayout.indices) {
            when {
                seatsLayout[index] == '/' -> {
                    // Create a new LinearLayout for each row
                    layout = LinearLayout(requireContext())
                    layout.orientation = LinearLayout.HORIZONTAL
                    binding.layoutSeat.addView(layout)
                }

                seatsLayout[index] == 'U' -> {
                    count++
                    val view = createSeatTextView(count, STATUS_BOOKED)
                    layout?.addView(view)
                    seatViewList.add(view)
                }

                seatsLayout[index] == 'A' -> {
                    count++
                    val view = createSeatTextView(count, STATUS_AVAILABLE)
                    layout?.addView(view)
                    seatViewList.add(view)
                }

                seatsLayout[index] == 'R' -> {
                    count++
                    val view = createSeatTextView(count, STATUS_RESERVED)
                    layout?.addView(view)
                    seatViewList.add(view)
                }

                seatsLayout[index] == '_' -> {
                    val view = createEmptyTextView()
                    layout?.addView(view)
                }
            }
        }
    }


    private fun createSeatTextView(count: Int, status: Int): TextView {
        val view = TextView(requireContext())
        val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
        layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
        view.layoutParams = layoutParams
        view.setPadding(0, 0, 0, 2 * seatGaping)
        view.id = count
        view.gravity = Gravity.CENTER
        view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9f)
        view.setTextColor(if (status == STATUS_BOOKED || status == STATUS_RESERVED) Color.WHITE else Color.BLACK)
        view.setBackgroundResource(
            when (status) {
                STATUS_AVAILABLE -> R.drawable.ic_seats_book
                STATUS_BOOKED -> R.drawable.ic_seats_booked
                STATUS_RESERVED -> R.drawable.ic_seats_reserved
                else -> R.drawable.ic_seats_book
            }
        )
        view.text = count.toString()
        view.tag = status
        view.setOnClickListener(this)


        return view
    }

    private fun createEmptyTextView(): TextView {
        val view = TextView(requireContext())
        val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
        layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
        view.layoutParams = layoutParams
        view.setBackgroundColor(Color.TRANSPARENT)
        view.text = ""
        return view
    }

    override fun onClick(view: View) {
        when (view.tag as Int) {
            STATUS_AVAILABLE -> {
                if (selectedIds.contains("${view.id},")) {
                    selectedIds = selectedIds.replace("${view.id},", "")
                    view.setBackgroundResource(R.drawable.ic_seats_book)
                } else {
                    selectedIds += "${view.id},"
                    view.setBackgroundResource(R.drawable.ic_seats_selected)
                }
            }

            STATUS_BOOKED -> Toast.makeText(
                requireContext(),
                "Seat ${view.id} is Booked",
                Toast.LENGTH_SHORT
            ).show()

            STATUS_RESERVED -> Toast.makeText(
                requireContext(),
                "Seat ${view.id} is Reserved",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Calculate total price whenever seat selection changes
        calculateTotalPrice()
    }

    private fun calculateTotalPrice() {
        val selectedSeatCount = selectedIds.split(",").filter { it.isNotEmpty() }.size
        val totalPrice = selectedSeatCount * seatPrice
        binding.tvPrice.text = "$totalPrice rs" // Update the text of the TextView
    }
}
