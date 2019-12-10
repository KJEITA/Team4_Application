package bonch.dev.team4_application.ui.test

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import bonch.dev.team4_application.R
import bonch.dev.team4_application.model.Constants


class TestActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager

    private lateinit var viewPager:ViewPager
    private lateinit var toolBar:Toolbar

    private lateinit var numberLesson:String
    private lateinit var titleSubject:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        setToolBarBackNavigation()


        initData()
        initViews()
        initViewPager()


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setToolBarBackNavigation() {
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
    }

    private fun initData() {
        numberLesson=intent.getStringExtra(Constants.NUMBER_LESSON_TAG)
        titleSubject=intent.getStringExtra(Constants.TITLE_SUBJECT_TAG)
    }

    private fun initViewPager() {
        val testAdapterViewPager = TestViewPagerAdapter(fragmentManager)
        testAdapterViewPager.addFragment(QuestionFragment.newInstance(numberLesson,titleSubject))
        testAdapterViewPager.addFragment(AnswerFragment.newInstance(numberLesson,titleSubject))
        viewPager.adapter=testAdapterViewPager
    }

    private fun initViews() {

        viewPager=findViewById<ViewPager>(R.id.view_pager)

    }
}
