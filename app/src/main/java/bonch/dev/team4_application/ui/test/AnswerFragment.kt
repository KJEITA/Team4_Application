package bonch.dev.team4_application.ui.test

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView

import bonch.dev.team4_application.R
import com.bumptech.glide.Glide
import com.google.firebase.database.*

class AnswerFragment : Fragment() {

    private var numberLesson: String? = null
    private var titleSubj: String? = null
    private lateinit var mDataBase: FirebaseDatabase
    private lateinit var mReference: DatabaseReference
    private lateinit var answerImage: ImageView
    private lateinit var btnRight: Button
    private lateinit var btnSolution: Button
    private lateinit var answerUrl:String
    private lateinit var solutionUrl:String

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
        setOnClickListeners()
        initRealtimeDB()
        loadQuestiomFromDB()

    }

    private fun setOnClickListeners() {
        btnRight.setOnClickListener(View.OnClickListener {

            var count:Int=0

                    mReference = mDataBase.reference.child("Users").child("5i7rzeCv3Sbo4QPjbfqnBo6zGDw1").
                        child("Progress").child(titleSubj.toString())

            mReference.addValueEventListener(
                object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        for(p1 in p0.getChildren()){

                            count++
                            Log.e("DA",count.toString())}
                        mReference.child((count+1).toString()).setValue(numberLesson?.toInt())
                    }
                })






        })
        btnSolution.setOnClickListener(View.OnClickListener {
            btnSolution.isClickable=false
            Glide
                .with(this@AnswerFragment)
                .load(solutionUrl)
                .into(answerImage)

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_answer, container, false)
    }

    private fun initViews(view: View) {
        answerImage=view.findViewById(R.id.testQuestionImageView)
        btnRight=view.findViewById(R.id.btn_right)
        btnSolution=view.findViewById(R.id.btn_solution)
    }


    private fun loadQuestiomFromDB() {

        mReference.addValueEventListener(
            object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    answerUrl = p0.child("answerUrl").value as String
                    solutionUrl = p0.child("solutionUrl").value as String
                    Glide
                        .with(this@AnswerFragment)
                        .load(answerUrl)
                        .into(answerImage)

                }
            })

    }

    private fun initRealtimeDB() {
        mDataBase = FirebaseDatabase.getInstance()
        mReference =
            mDataBase.reference.child("Exact sciences").child(titleSubj.toString()).child(numberLesson.toString())
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AnswerFragment().apply {
                arguments = Bundle().apply {
                    putString("PARAM1", param1)
                    putString("PARAM2", param2)
                }
            }
    }
}

