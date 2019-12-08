package bonch.dev.team4_application.ui.lesson

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import bonch.dev.team4_application.R
import bonch.dev.team4_application.ui.theory.TheoryActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class LessonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        /*val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)*/

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment_lesson)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_personal_area, R.id.navigation_home, R.id.navigation_awards))
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun CkickLesson(view: View) {
        val intent = Intent(LessonActivity@ this, TheoryActivity::class.java)
        val nameScience = view.findViewById<TextView>(R.id.nameLesson)
        intent.putExtra("Science", nameScience.text.toString())
        startActivity(intent)
    }
}
