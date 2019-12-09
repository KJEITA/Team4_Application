package bonch.dev.team4_application.ui.theory

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import bonch.dev.team4_application.R
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


        mReference.addValueEventListener(
            object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var t = p0.child("Theory").value as String
                    view_theory.text = t
                }
            })
    }
}
