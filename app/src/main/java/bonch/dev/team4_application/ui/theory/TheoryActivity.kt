package bonch.dev.team4_application.ui.theory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import bonch.dev.team4_application.R
import kotlinx.android.synthetic.main.activity_theory.*

class TheoryActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theory)



        initViews()
        initViewPagerAdapter()


        /*var nameScence =  intent.getStringExtra ("Science")

        val view_nameScence = findViewById<TextView>(R.id.nameLessonTheory)

        view_nameScence.text = nameScence*/
    }

    private fun initViewPagerAdapter() {

        val adapter: TheoryPagerAdapter = TheoryPagerAdapter(supportFragmentManager)
        adapter.addFragment(TheoryFragment(),"Theory")
        adapter.addFragment(TestFragment(),"Test")
        viewPager.adapter=adapter
        tabs.setupWithViewPager(viewPager)

    }

    private fun initViews() {
        viewPager = findViewById(R.id.viewPager)
    }
}
