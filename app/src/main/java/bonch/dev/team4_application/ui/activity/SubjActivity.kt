package bonch.dev.team4_application.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import bonch.dev.team4_application.R
import bonch.dev.team4_application.ui.lesson.LessonActivity
import kotlinx.android.synthetic.main.activity_login.view.*

class SubjActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subj)
        supportActionBar?.hide()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_subject,
                R.id.navigation_personal_area,
                R.id.navigation_awards
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //val intent = Intent(SubjActivity@ this, LessonActivity::class.java)
        //startActivity(intent)
    }
    fun onClickSubJect(view: View){
        val intent = Intent(SubjActivity@ this, LessonActivity::class.java)
        val t = view.findViewById<TextView>(R.id.subjTitleTextView)
        intent.putExtra("NameScience", t.toString())
        startActivity(intent)
    }
}
