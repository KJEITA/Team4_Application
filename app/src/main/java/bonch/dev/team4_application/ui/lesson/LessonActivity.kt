package bonch.dev.team4_application.ui.lesson

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.team4_application.model.Constants
import bonch.dev.team4_application.ui.theory.TheoryActivity


class LessonActivity : AppCompatActivity() {

    private lateinit var LessonRecyclerView: RecyclerView
    private lateinit var titleSubject:String
    lateinit var lessonRecyclerItems: Lesson_Recycler_item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bonch.dev.team4_application.R.layout.activity_lesson)

        setToolBarBackNavigation()
        initViews()
        setRV()




    }

    private fun setRV() {
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.stackFromEnd = true
        LessonRecyclerView.layoutManager = linearLayoutManager

        titleSubject = intent.getStringExtra(Constants.TITLE_SUBJECT_TAG)

        lessonRecyclerItems = Lesson_Recycler_item(titleSubject)
        LessonRecyclerView.adapter = lessonRecyclerItems
    }

    private fun initViews() {
        LessonRecyclerView = findViewById(bonch.dev.team4_application.R.id.Lesson_recycler_view)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setToolBarBackNavigation() {
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
    }

    fun CkickLesson(view: View) {
        val intent = Intent(LessonActivity@ this, TheoryActivity::class.java)
        val titleLesson = view.findViewById<TextView>(bonch.dev.team4_application.R.id.nameLesson)
        val numberLesson = view.findViewById<TextView>(bonch.dev.team4_application.R.id.numberLesson)
        intent.putExtra(Constants.TITLE_LESSON_TAG, titleLesson.text.toString())
        intent.putExtra(Constants.NUMBER_LESSON_TAG, numberLesson.text.toString())
        intent.putExtra(Constants.TITLE_SUBJECT_TAG, titleSubject)
        startActivity(intent)
    }
}
