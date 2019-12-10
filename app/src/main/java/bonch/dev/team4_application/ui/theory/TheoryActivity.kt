package bonch.dev.team4_application.ui.theory

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import bonch.dev.team4_application.R
import bonch.dev.team4_application.model.Constants
import bonch.dev.team4_application.ui.test.TestActivity
import com.google.firebase.database.*

class TheoryActivity : AppCompatActivity() {

    private lateinit var mDataBase: FirebaseDatabase
    private lateinit var mReference: DatabaseReference
    private lateinit var questURL: String
    private lateinit var ansverURL: String
    private lateinit var solutionURL: String
    private lateinit var titleSubj:String
    private lateinit var titleLesson:String
    private lateinit var numberLesson:String

    private lateinit var theoryTextView:TextView
    private lateinit var titleLessonTextView:TextView
    private lateinit var numberLessonTextView:TextView



    init {


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theory)
        setToolBarBackNavigation()
        initDataFromIntent()
        initViews()

        mReference.addValueEventListener(
            object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var theoryText = p0.child("Theory").value as String
                    theoryTextView.text = theoryText
                    titleLessonTextView.text = p0.child("Name").value as String
                    numberLessonTextView.text = numberLesson
                }
            })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setToolBarBackNavigation() {
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
    }

    private fun initViews() {
        theoryTextView = findViewById<TextView>(R.id.theory)
        titleLessonTextView = findViewById<TextView>(R.id.nameDisc)
        numberLessonTextView = findViewById<TextView>(R.id.numTest)

    }

    private fun initDataFromIntent() {

        titleSubj = intent.getStringExtra(Constants.TITLE_SUBJECT_TAG)
        titleLesson= intent.getStringExtra(Constants.TITLE_LESSON_TAG)
        numberLesson = intent.getStringExtra(Constants.NUMBER_LESSON_TAG)

        mDataBase = FirebaseDatabase.getInstance()
        mReference =
            mDataBase.reference.child("Exact sciences").child(titleSubj).child(numberLesson)
    }

    fun startTestActivity(view: View) {
        val intent = Intent(TheoryActivity@ this, TestActivity::class.java)
        intent.putExtra(Constants.TITLE_SUBJECT_TAG, titleSubj)
        intent.putExtra(Constants.NUMBER_LESSON_TAG, numberLesson)
        startActivity(intent)
    }
}
