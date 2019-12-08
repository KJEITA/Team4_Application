package bonch.dev.team4_application.ui.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.team4_application.R

class Lesson_Recycler_item : RecyclerView.Adapter<Lesson_Recycler_item.LessonHolder>() {

    public val messageLab = LessonObj.LessonLab()


    var messageList = messageLab.messageList


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LessonHolder {
        var view: View



        messageLab.setRecc(this)
        //messageList = mutableListOf()

        view = if (viewType == 0)
            LayoutInflater.from(parent.context)
                .inflate(R.layout.header_list_lesson, parent, false)
        else if(viewType == 1)
            LayoutInflater.from(parent.context)
                .inflate(R.layout.active_list_lesson, parent, false)
        else
            LayoutInflater.from(parent.context)
                .inflate(R.layout.noactive_list_lesson, parent, false)

        return LessonHolder(view)
    }

    public fun updateData() {
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

    inner class LessonHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {

            if (messageList[position].lessonType == 0) {
                val nameTextView = itemView.findViewById<TextView>(R.id.headerLesson)
                val allTextView = itemView.findViewById<TextView>(R.id.headerAll)
                val openTextView = itemView.findViewById<TextView>(R.id.headerOpen)
                val progressTextView = itemView.findViewById<TextView>(R.id.headerProgress)

                nameTextView.text = messageList[position].lessonName
                allTextView.text = messageList[position].lessonAll
                openTextView.text = messageList[position].lessonOpen
                progressTextView.text = messageList[position].lessonProgress
            }else{
                val nameTextView = itemView.findViewById<TextView>(R.id.nameLesson)
                val numberTextView = itemView.findViewById<TextView>(R.id.numberLesson)

                nameTextView.text = messageList[position].lessonName
                numberTextView.text = messageList[position].lessonId.toString()
            }
        }
    }
}