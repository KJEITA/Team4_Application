package bonch.dev.team4_application.ui.theory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

// 1
class TheoryPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager){

    private val fragmentList : MutableList<Fragment> = ArrayList()
    private val titleFragmentList : MutableList<String> = ArrayList()

    // 2
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    // 3
    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment, title:String){
        fragmentList.add(fragment)
        titleFragmentList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleFragmentList[position]
    }
}