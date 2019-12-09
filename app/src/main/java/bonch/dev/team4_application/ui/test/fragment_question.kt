package bonch.dev.team4_application.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.team4_application.R
import bonch.dev.team4_application.ui.home.HomeViewModel
import bonch.dev.team4_application.ui.lesson.Lesson_Recycler_item

class fragment_question : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var LessonRecyclerView: RecyclerView
    lateinit var lessonRecyclerItems: Lesson_Recycler_item

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_home, container, false)



        return view
    }
}