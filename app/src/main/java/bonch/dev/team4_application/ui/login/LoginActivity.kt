package bonch.dev.team4_application.ui.login

import android.content.Context
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

import java.net.HttpRetryException
import android.graphics.Paint
import android.net.ConnectivityManager
import android.widget.Button


class LoginActivity : AppCompatActivity() {

    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText
    private lateinit var loading: ProgressBar

    private lateinit var underText: Button

    private lateinit var mAuth: FirebaseAuth

    private var bEmail = false;
    private var bPassword = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bonch.dev.team4_application.R.layout.activity_login)

        emailText = findViewById(bonch.dev.team4_application.R.id.username)
        passwordText = findViewById(bonch.dev.team4_application.R.id.password)
        loading = findViewById(bonch.dev.team4_application.R.id.loading)

        mAuth = FirebaseAuth.getInstance()

        checkFields()

        underText = findViewById(R.id.signUp)

        underText.setPaintFlags(underText.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

        if (!isOnline(applicationContext))
            Toast.makeText(
                applicationContext,
                "Отсутсвует подключение к интернету",
                Toast.LENGTH_SHORT
            ).show()
    }

    fun signIn(view: View) {
        if (isOnline(applicationContext)) {
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
        }else
            Toast.makeText(
                applicationContext,
                "Отсутсвует подключение к интернету",
                Toast.LENGTH_SHORT
            ).show()
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

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}