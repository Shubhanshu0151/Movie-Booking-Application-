import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebookingapp.R

class TimingAdapter(private val theaterNames: List<String>) :
    RecyclerView.Adapter<TimingAdapter.TimingViewHolder>() {

    private var filteredTheaterNames: List<String> = theaterNames

    // Keep track of selected radio button position for each row
    private var selectedRadioButtonPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_timing, parent, false)
        return TimingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimingViewHolder, position: Int) {
        val theaterName = theaterNames[position]
        holder.bind(theaterName)

        // Set click listeners for radio buttons
        holder.radioButton1.setOnClickListener {
            handleRadioButtonClick(holder, 1)
        }

        holder.radioButton2.setOnClickListener {
            handleRadioButtonClick(holder, 2)
        }

        holder.radioButton3.setOnClickListener {
            handleRadioButtonClick(holder, 3)
        }

        holder.radioButton4.setOnClickListener {
            handleRadioButtonClick(holder, 4)
        }

        holder.radioButton5.setOnClickListener {
            handleRadioButtonClick(holder, 5)
        }

        holder.radioButton6.setOnClickListener {
            handleRadioButtonClick(holder, 6)
        }

        holder.radioButton7.setOnClickListener {
            handleRadioButtonClick(holder, 7)
        }

        holder.radioButton8.setOnClickListener {
            handleRadioButtonClick(holder, 8)
        }

        holder.radioButton9.setOnClickListener {
            handleRadioButtonClick(holder, 9)
        }

        holder.radioButton10.setOnClickListener {
            handleRadioButtonClick(holder, 10)
        }

        holder.radioButton11.setOnClickListener {
            handleRadioButtonClick(holder, 11)
        }

        holder.radioButton12.setOnClickListener {
            handleRadioButtonClick(holder, 12)
        }
    }

    private fun handleRadioButtonClick(holder: TimingViewHolder, clickedButtonIndex: Int) {
        // Uncheck all radio buttons
        holder.radioButton1.isChecked = false
        holder.radioButton2.isChecked = false
        holder.radioButton3.isChecked = false
        holder.radioButton4.isChecked = false
        holder.radioButton5.isChecked = false
        holder.radioButton6.isChecked = false
        holder.radioButton7.isChecked = false
        holder.radioButton8.isChecked = false
        holder.radioButton9.isChecked = false
        holder.radioButton10.isChecked = false
        holder.radioButton11.isChecked = false
        holder.radioButton12.isChecked = false

        // Check the clicked radio button
        val clickedRadioButton = getRadioButton(holder, clickedButtonIndex)
        clickedRadioButton.isChecked = true

        Toast.makeText(
            holder.itemView.context,
            "Option $clickedButtonIndex clicked",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun getRadioButton(holder: TimingViewHolder, index: Int): RadioButton {
        return when (index) {
            1 -> holder.radioButton1
            2 -> holder.radioButton2
            3 -> holder.radioButton3
            4 -> holder.radioButton4
            5 -> holder.radioButton5
            6 -> holder.radioButton6
            7 -> holder.radioButton7
            8 -> holder.radioButton8
            9 -> holder.radioButton9
            10 -> holder.radioButton10
            11 -> holder.radioButton11
            12 -> holder.radioButton12
            else -> throw IllegalArgumentException("Invalid radio button index: $index")
        }
    }

    override fun getItemCount(): Int {
        return theaterNames.size
    }

    fun filter(query: String) {
        filteredTheaterNames = if (query.isBlank()) {
            theaterNames
        } else {
            theaterNames.filter { it.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }

    inner class TimingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val radioButton1: RadioButton = itemView.findViewById(R.id.rd930)
        val radioButton2: RadioButton = itemView.findViewById(R.id.rd1030)
        val radioButton3: RadioButton = itemView.findViewById(R.id.rd1130)
        val radioButton4: RadioButton = itemView.findViewById(R.id.rd1230)
        val radioButton5: RadioButton = itemView.findViewById(R.id.rd1130)
        val radioButton6: RadioButton = itemView.findViewById(R.id.rd230)
        val radioButton7: RadioButton = itemView.findViewById(R.id.rd330)
        val radioButton8: RadioButton = itemView.findViewById(R.id.rd430)
        val radioButton9: RadioButton = itemView.findViewById(R.id.rd530)
        val radioButton10: RadioButton = itemView.findViewById(R.id.rd630)
        val radioButton11: RadioButton = itemView.findViewById(R.id.rd730)
        val radioButton12: RadioButton = itemView.findViewById(R.id.rd830)
        val theaterNameTextView: TextView = itemView.findViewById(R.id.tvTheaterName)

        fun bind(theaterName: String) {
            theaterNameTextView.text = theaterName
        }
    }
}
