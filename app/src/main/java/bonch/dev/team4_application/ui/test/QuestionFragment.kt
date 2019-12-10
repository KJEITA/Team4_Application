package bonch.dev.team4_application.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import bonch.dev.team4_application.R
import com.bumptech.glide.Glide
import com.google.firebase.database.*

class QuestionFragment : Fragment() {
    private var numberLesson: String? = null
    private var titleSubj: String? = null
    private lateinit var mDataBase: FirebaseDatabase
    private lateinit var mReference: DatabaseReference
    private lateinit var questImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            numberLesson = it.getString("PARAM1")
            titleSubj = it.getString("PARAM2")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initRealtimeDB()
        loadQuestiomFromDB()

    }

    private fun initViews(view: View) {
        questImage=view.findViewById(R.id.testQuestionImageView)
    }


    private fun loadQuestiomFromDB() {

        mReference.addValueEventListener(
            object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var questUrl = p0.child("questUrl").value as String
                    Glide
                        .with(this@QuestionFragment)
                        .load(questUrl)
                        .into(questImage)

                }
            })

    }

    private fun initRealtimeDB() {
        mDataBase = FirebaseDatabase.getInstance()
        mReference =
            mDataBase.reference.child("Exact sciences").child(titleSubj.toString()).child(numberLesson.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionFragment().apply {
                arguments = Bundle().apply {
                    putString("PARAM1", param1)
                    putString("PARAM2", param2)
                }
            }
    }
}