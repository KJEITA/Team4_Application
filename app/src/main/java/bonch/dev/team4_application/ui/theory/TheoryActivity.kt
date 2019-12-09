package bonch.dev.team4_application.ui.theory

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import bonch.dev.team4_application.R
import bonch.dev.team4_application.ui.test.TestActivity
import com.google.firebase.database.*

class TheoryActivity : AppCompatActivity() {

    private var mDataBase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var mReference: DatabaseReference

    init {
        mReference =
            mDataBase.reference.child("Exact sciences").child("Алгебра").child("1")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theory)

        var nameScence = intent.getStringExtra("Science")

        //val view_nameScence = findViewById<TextView>(R.id.nameLessonTheory)

        //view_nameScence.text = nameScence
        val view_theory = findViewById<TextView>(R.id.theory)
        val view_disc = findViewById<TextView>(R.id.nameDisc)
        val view_numTest = findViewById<TextView>(R.id.numTest)

        mReference.addValueEventListener(
            object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var t = p0.child("Theory").value as String
                    view_theory.text = t
                    view_disc.text = "Алгебра"
                    view_numTest.text = "1"
                }
            })
    }

    fun startTest(view: View) {
        val intent = Intent(TheoryActivity@ this, TestActivity::class.java)
        val nameScience = view.findViewById<TextView>(bonch.dev.team4_application.R.id.nameLesson)
        intent.putExtra("Name", nameScience.text.toString())
        intent.putExtra("Num", nameScience.text.toString())
        startActivity(intent)
    }
}
