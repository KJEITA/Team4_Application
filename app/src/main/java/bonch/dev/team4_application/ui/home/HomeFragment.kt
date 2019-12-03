package bonch.dev.team4_application.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bonch.dev.team4_application.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var mReference: DatabaseReference

    private lateinit var myName: TextView
    private lateinit var myEmail: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })


        myName = root.findViewById(R.id.name_text)
        myEmail = root.findViewById(R.id.email_text_view)


        mAuth = FirebaseAuth.getInstance()

        val user = mAuth.currentUser!!.uid

        mDatabase = FirebaseDatabase.getInstance()
        mReference = mDatabase.reference.child("Users").child(user)

        mReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                myName.text = p0.child("name").value as String
                myEmail.text = p0.child("email").value as String
            }

            override fun onCancelled(p0: DatabaseError) {
                //
            }
        })



        return root
    }
}