package bonch.dev.team4_application.ui.lesson

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.team4_application.ui.home.HomeViewModel
import bonch.dev.team4_application.ui.theory.TheoryActivity


class LessonActivity : AppCompatActivity() {
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var LessonRecyclerView: RecyclerView
    lateinit var lessonRecyclerItems: Lesson_Recycler_item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bonch.dev.team4_application.R.layout.activity_lesson)

        LessonRecyclerView = findViewById(bonch.dev.team4_application.R.id.Lesson_recycler_view)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.stackFromEnd = true
        LessonRecyclerView.layoutManager = linearLayoutManager

        var n = intent.getStringExtra("NameScience")

        lessonRecyclerItems = Lesson_Recycler_item(n)
        LessonRecyclerView.adapter = lessonRecyclerItems

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun CkickLesson(view: View) {
        val intent = Intent(LessonActivity@ this, TheoryActivity::class.java)
        val nameScience = view.findViewById<TextView>(bonch.dev.team4_application.R.id.nameLesson)
        intent.putExtra("Science", nameScience.text.toString())
        startActivity(intent)
    }
}
