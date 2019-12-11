package bonch.dev.team4_application.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.team4_application.R

class StaticFragment : DialogFragment() {

    private lateinit var staticsRecyclerView: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_statistics, container, false)

//        staticsRecyclerView = view.findViewById(R.id.article_recycler_view)
//        staticsRecyclerView.layoutManager = LinearLayoutManager(container!!.context)
//        staticsRecyclerView.adapter = StatisticsAdapter()

        return view
    }
}