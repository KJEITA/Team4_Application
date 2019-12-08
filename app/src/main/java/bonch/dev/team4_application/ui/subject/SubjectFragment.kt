package bonch.dev.team4_application.ui.subject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.team4_application.R
import bonch.dev.team4_application.adapters.SubjSectAdapter
import bonch.dev.team4_application.model.SubjSect
import bonch.dev.team4_application.model.Subject
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
        auth()
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

            override fun onDataChange(p0: DataSnapshot) {
                for (p1 in p0.getChildren()) {

                    val subjList: MutableList<Subject> = mutableListOf()
                    for (p2 in p1.child("subjList").children) {
                        val subj: Subject? = p2.getValue(Subject::class.java)
                        if (subj != null) {
                            subjList.add(subj)
                        }

                    }
                    subjSectList.add(
                        SubjSect(
                            p1.child("subjSectTitle").value as String,
                            (p1.child("subjSectType").value as Long).toInt(),
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
            Log.e("RequestTitle", subjItem.subjtTitleRequest)
        }
        subjSectRV.adapter = subjSectAdapter

    }
}
