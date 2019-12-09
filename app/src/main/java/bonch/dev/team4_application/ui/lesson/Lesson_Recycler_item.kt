package bonch.dev.team4_application.ui.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Lesson_Recycler_item(s:String) : RecyclerView.Adapter<Lesson_Recycler_item.LessonHolder>() {
    val messageLab = LessonObj.LessonLab(s)

    var messageList = messageLab.messageList
    init {
    messageLab.setRecc(this)

}



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LessonHolder {
        var view: View


        view = if (viewType == 0)
            LayoutInflater.from(parent.context)
                .inflate(bonch.dev.team4_application.R.layout.header_list_lesson, parent, false)
        else if (viewType == 1)
            LayoutInflater.from(parent.context)
                .inflate(bonch.dev.team4_application.R.layout.active_list_lesson, parent, false)
        else
            LayoutInflater.from(parent.context)
                .inflate(bonch.dev.team4_application.R.layout.noactive_list_lesson, parent, false)

        return LessonHolder(view)
    }

    fun updateData() {
        messageList = messageLab.messageList
    }

    override fun getItemViewType(position: Int): Int {
        return messageList[position].lessonType
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: LessonHolder, position: Int) {
        holder.bind(position)
    }

    inner class LessonHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(position: Int) {
            if (messageList[position].lessonType == 0) {
                val nameTextView =
                    itemView.findViewById<TextView>(bonch.dev.team4_application.R.id.headerLesson)
                val allTextView =
                    itemView.findViewById<TextView>(bonch.dev.team4_application.R.id.headerAll)
                val openTextView =
                    itemView.findViewById<TextView>(bonch.dev.team4_application.R.id.headerOpen)
                val progressTextView =
                    itemView.findViewById<TextView>(bonch.dev.team4_application.R.id.headerProgress)

                nameTextView.text = messageList[position].lessonName
                allTextView.text = messageList[position].lessonAll
                openTextView.text = messageList[position].lessonOpen
                progressTextView.text = messageList[position].lessonProgress
            } else {
                val nameTextView =
                    itemView.findViewById<TextView>(bonch.dev.team4_application.R.id.nameLesson)
                val numberTextView =
                    itemView.findViewById<TextView>(bonch.dev.team4_application.R.id.numberLesson)

                nameTextView.text = messageList[position].lessonName
                numberTextView.text = messageList[position].lessonId.toString()
            }

        }
    }
}