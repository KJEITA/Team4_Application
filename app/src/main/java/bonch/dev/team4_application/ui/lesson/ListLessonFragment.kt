package bonch.dev.team4_application.ui.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.team4_application.R

class ListLessonFragment : Fragment() {
    private lateinit var LessonRecyclerView: RecyclerView
    lateinit var lessonRecyclerItems: Lesson_Recycler_item


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_lesson, container, false)

        LessonRecyclerView = view.findViewById(R.id.Lesson_recycler_view)
        val linearLayoutManager = LinearLayoutManager(container!!.context)
        linearLayoutManager.stackFromEnd = true
        LessonRecyclerView.layoutManager = linearLayoutManager
        lessonRecyclerItems = Lesson_Recycler_item()
        LessonRecyclerView.adapter = lessonRecyclerItems

        return view
    }
}