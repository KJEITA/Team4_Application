package bonch.dev.team4_application.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import bonch.dev.team4_application.R
import bonch.dev.team4_application.ui.fragments.StaticFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var mReference: DatabaseReference

    private lateinit var myName: TextView
    private lateinit var myEmail: TextView

    private lateinit var staticButton: Button
    private lateinit var nastrButton: ImageView
    private lateinit var aboutAppButton: Button

    private lateinit var avatarImage: ImageView

    var dialogStat = StaticFragment()
    val dialogNastr = NastrFragment()
    val dialogAboutApp = AboutAppFragment()


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        myName = root.findViewById(R.id.name_text)
        myEmail = root.findViewById(R.id.email_text_view)

        staticButton = root.findViewById(R.id.static_button)
        nastrButton = root.findViewById(R.id.imageButton_nastr)
        aboutAppButton = root.findViewById(R.id.about_app_button)

        avatarImage = root.findViewById(R.id.avatar_image_view)

        mAuth = FirebaseAuth.getInstance()

        val user = mAuth.currentUser!!.uid

        mDatabase = FirebaseDatabase.getInstance()
        mReference = mDatabase.reference.child("Users").child(user)

        mReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                myName.text = "${p0.child("name").value as String} " +
                        "${p0.child("secondName").value as String}"
                myEmail.text = p0.child("email").value as String
            }

            override fun onCancelled(p0: DatabaseError) {
                //
            }
        })

        staticButton.setOnClickListener {
            dialogStat.show(fragmentManager!!, "StaticFragment")
        }

        nastrButton.setOnClickListener {
            dialogNastr.show(fragmentManager!!, "NastrFragment")
        }

        aboutAppButton.setOnClickListener {
            dialogAboutApp.show(fragmentManager!!, "AboutAppFragment")
        }

        avatarImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        return root
    }
}