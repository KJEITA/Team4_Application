package bonch.dev.team4_application.ui.subject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.team4_application.R
import bonch.dev.team4_application.adapters.SubjSectAdapter
import bonch.dev.team4_application.model.Constants
import bonch.dev.team4_application.model.SubjSect
import bonch.dev.team4_application.model.Subject
import bonch.dev.team4_application.ui.lesson.LessonActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class SubjectFragment : Fragment() {

    private lateinit var subjSectRV: RecyclerView
    private lateinit var subjSectList: MutableList<SubjSect>

    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var mReference: DatabaseReference
    private lateinit var mAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_subject, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //auth()
        initDB()
        initViews(view)
        initSubjSectList()

    }

    private fun auth() {
        val email: String = "kirill_test@gmail.com"
        val password: String = "1234qwer"
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.e("Auth", "Success")

            } else {
                Log.e("Auth", "Dinied")
            }
        }

    }

    private fun initDB() {
        mDatabase = FirebaseDatabase.getInstance()
        mReference = mDatabase.reference.child("Sciences")

    }

    private fun initViews(view: View) {
        subjSectRV = view.findViewById(R.id.subjectsSectionRV)
    }

    private fun initSubjSectList() {
        subjSectList = mutableListOf()
        mReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(subjSectListData: DataSnapshot) {
                for (subjSectData in subjSectListData.getChildren()) {

                    val subjList: MutableList<Subject> = mutableListOf()
                    for (subjData in subjSectData.child("subjList").children) {
                        val subj: Subject? = subjData.getValue(Subject::class.java)
                        if (subj != null) {
                            subjList.add(subj)
                        }

                    }
                    this@SubjectFragment.subjSectList.add(
                        SubjSect(
                            subjSectData.child("subjSectTitle").value as String,
                            (subjSectData.child("subjSectType").value as Long).toInt(),
                            subjList
                        )
                    )

                }

                setRvData()

            }

            override fun onCancelled(p0: DatabaseError) {
                Log.e("ERROR DATABASE", p0.toException().toString())

            }

        })
    }

    private fun setRvData() {
        subjSectRV.layoutManager = LinearLayoutManager(view!!.context)
        val subjSectAdapter = SubjSectAdapter(subjSectList)
        subjSectAdapter.onItemClick = { subjItem ->

            val intent = Intent(context, LessonActivity::class.java)
            intent.putExtra(Constants.TITLE_SUBJECT_TAG, subjItem.subjTitle)
            startActivity(intent)
        }
        subjSectRV.adapter = subjSectAdapter

    }
}

