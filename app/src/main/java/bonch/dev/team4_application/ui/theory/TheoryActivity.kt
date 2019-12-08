package bonch.dev.team4_application.ui.theory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import bonch.dev.team4_application.R

class TheoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theory)

        var nameScence =  intent.getStringExtra ("Science")

        val view_nameScence = findViewById<TextView>(R.id.nameLessonTheory)

        view_nameScence.text = nameScence
    }
}
