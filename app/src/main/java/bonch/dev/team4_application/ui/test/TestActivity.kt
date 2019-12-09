package bonch.dev.team4_application.ui.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bonch.dev.team4_application.R

class TestActivity : AppCompatActivity() {

    val fragmentManager = supportFragmentManager
    val fragment = fragment_question()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        var args: Bundle

        var s = intent.getStringExtra()

        args.putString("name", )

        fragmentManager.beginTransaction().add(R.id.fragment, fragment)
            .commit()

    }
}
