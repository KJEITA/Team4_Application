package bonch.dev.team4_application.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import bonch.dev.team4_application.MainActivity
import bonch.dev.team4_application.R
import bonch.dev.team4_application.ui.signUp.SignUp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import java.net.HttpRetryException

class LoginActivity : AppCompatActivity() {

    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText
    private lateinit var loading:ProgressBar

    private lateinit var mDataBase: FirebaseDatabase
    private lateinit var mReference: DatabaseReference
    private lateinit var mAuth: FirebaseAuth

    private var bEmail = false;
    private var bPassword = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailText = findViewById(R.id.username)
        passwordText = findViewById(R.id.password)
        loading = findViewById(R.id.loading)


        mDataBase = FirebaseDatabase.getInstance()
        mReference = mDataBase.reference.child("Users")
        mAuth = FirebaseAuth.getInstance()

        checkFields()
    }

    fun signIn(view: View) {
        val email = emailText.text.toString()
        val password = passwordText.text.toString()
        if (bEmail && bPassword) {
            loading.visibility = ProgressBar.VISIBLE
            try {

                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(SignInActivity@ this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Неверный логин или пароль",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        loading.visibility = ProgressBar.INVISIBLE
                    }
            } catch (err: HttpRetryException) {

            }
        }
    }

    fun signUp(view: View) {
        val intent = Intent(SignInActivity@ this, SignUp::class.java)
        startActivity(intent)
    }

    private fun checkFields() {
        emailText.apply {
            addTextChangedListener {
                if (emailText.text.toString().contains('@')) {
                    emailText.error = null
                    bEmail = true
                } else {
                    emailText.error = "Not valid Email"
                    bEmail = false
                }
            }
        }
        passwordText.apply {
            addTextChangedListener {
                if (passwordText.text.toString().length > 5) {
                    passwordText.error = null
                    bPassword = true
                } else {
                    passwordText.error = "Пароль должен быть больше 5 символов"
                    bPassword = false
                }
            }
        }
    }
}