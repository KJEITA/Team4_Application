package bonch.dev.team4_application.ui.theory

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import bonch.dev.team4_application.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_test.*

class TestFragment : Fragment() {

    private lateinit var testCard: View
    private lateinit var imageTestCard: ImageView
    private lateinit var textView: TextView
    private lateinit var btnSolution: Button
    private lateinit var btnRight: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        loadImgQuestoin()
        setOnClickListeners()
    }

    private fun loadImgQuestoin() {
        Glide
            .with(this)
            .load("https://firebasestorage.googleapis.com/v0/b/team4application.appspot.com/o/%D0%9F%D1%80%D0%BE%D0%B8%D0%B7%D0%B2%D0%BE%D0%B4%D0%BD%D0%B0%D1%8F%20%D0%B4%D0%BB%D1%8F%20%D1%84%D1%83%D0%BD%D0%BA%D1%86%D0%B8%D0%B8%20F(x)%20%3D%EF%80%BD4%D1%85%20-%201.png?alt=media&token=f39d066f-f616-49d4-b0d5-4046adaf86fc")
            .into(imageTestCard)
    }

    private fun setOnClickListeners() {
        testCard.setOnClickListener(View.OnClickListener {
            Glide
                .with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/team4application.appspot.com/o/%D0%9E%D1%82%D0%B2%D0%B5%D1%82%204.png?alt=media&token=19ce944c-8eb3-4b94-9b08-4d62016ea649")
                .into(imageTestCard)
            textView.visibility = View.INVISIBLE
            btnRight.visibility = View.VISIBLE
            btnSolution.visibility = View.VISIBLE
        })

        btnRight.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, "Krasavchik!", Toast.LENGTH_LONG).show()

            //To write result in DB

        })

        btnSolution.setOnClickListener(View.OnClickListener {
            btnSolution.visibility=View.INVISIBLE
            Glide
                .with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/team4application.appspot.com/o/%D0%A0%D0%B5%D1%88%D0%B5%D0%BD%D0%B8%D0%B5_%20(4%C2%B7x-1)'%20%3D%204.png?alt=media&token=2b946912-0f29-4f21-a4b5-ec392b7588c0")
                .into(imageTestCard)
        })
    }

    private fun initViews(view: View) {
        testCard = view.findViewById(R.id.test_card)
        imageTestCard = view.findViewById(R.id.imageViewTestCard)
        textView = view.findViewById(R.id.textViewHint)
        btnRight = view.findViewById(R.id.buttonRight)
        btnSolution = view.findViewById(R.id.buttonSolution)
    }
}
