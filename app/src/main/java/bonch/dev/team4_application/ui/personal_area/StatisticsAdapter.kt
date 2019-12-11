package bonch.dev.team4_application.ui.personal_area

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.team4_application.R
import bonch.dev.team4_application.models.StatisticsLab

class StatisticsAdapter : RecyclerView.Adapter<StatisticsAdapter.StatisticsHolder>() {


    val statisticsList = StatisticsLab().statisticsList

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StatisticsHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.statistics_item, parent, false)
        return StatisticsHolder(view)
    }

    override fun getItemCount(): Int = statisticsList.size

    override fun onBindViewHolder(holder: StatisticsHolder, position: Int) {

        holder.bind(position)

    }

    inner class StatisticsHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int) {
            val objectTextView = itemView.findViewById<TextView>(R.id.object_text_view)
            objectTextView.text = statisticsList[position].titleObject
            val progressTextView = itemView.findViewById<TextView>(R.id.progress_text_view)
            progressTextView.text = statisticsList[position].progress
        }

    }


}