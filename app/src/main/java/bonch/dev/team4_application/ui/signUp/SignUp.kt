package bonch.dev.team4_application.ui.signUp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import bonch.dev.team4_application.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.net.HttpRetryException


class SignUp : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var name: EditText
    lateinit var secondName: EditText
    lateinit var phoneNumber: EditText
    lateinit var password: EditText
    lateinit var passwordConfirm: EditText

    lateinit var signUpBtn: Button

    private lateinit var mReference: DatabaseReference
    private lateinit var mDataBase: FirebaseDatabase

    private var bEmail = false;
    private var bPassword = false;
    private var bPasswordConfirm = false;
    private var bName = false;
    private var bSecondName = false;
    private var bPhone = false;


    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bonch.dev.team4_application.R.layout.activity_signup)

        email = findViewById(bonch.dev.team4_application.R.id.et_Email)
        name = findViewById(bonch.dev.team4_application.R.id.et_Name)
        secondName = findViewById(bonch.dev.team4_application.R.id.et_SecondName)
        phoneNumber = findViewById(bonch.dev.team4_application.R.id.et_PhoneNumber)
        password = findViewById(bonch.dev.team4_application.R.id.et_Password_signUp)
        passwordConfirm = findViewById(bonch.dev.team4_application.R.id.et_Password2_signUp)
        signUpBtn = findViewById(bonch.dev.team4_application.R.id.btn_SignUp)

        mDataBase = FirebaseDatabase.getInstance()
        mAuth = FirebaseAuth.getInstance()
        mReference = mDataBase.reference.child("Users")

        checkFields()
        //setSpinner()
    }

    fun signUp(view: View) {
        if (isOnline(applicationContext)) {
            if (bEmail && bName && bPassword && bPasswordConfirm && bPhone && bSecondName)
                try {
                    mAuth.createUserWithEmailAndPassword(
                        email.text.toString(),
                        password.text.toString()
                    ).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = mAuth.currentUser!!.uid
                            val currentUserDb = mReference.child(user)
                            currentUserDb.child("email").setValue(email.text.toString())
                            currentUserDb.child("name").setValue(name.text.toString())
                            currentUserDb.child("secondName")
                                .setValue(secondName.text.toString())
                            currentUserDb.child("password").setValue(password.text.toString())
                            currentUserDb.child("phone").setValue(phoneNumber.text.toString())


                            val intent = Intent(SignUpActivity@ this, MainActivity::class.java)
                            startActivity(intent)
                        } else {

                        }
                    }
                } catch (err: HttpRetryException) {

                }else
                Toast.makeText(
                    applicationContext,
                    "Не верно заполненны поля",
                    Toast.LENGTH_SHORT
                ).show()
        } else
            Toast.makeText(
                applicationContext,
                "Отсутсвует подключение к интернету",
                Toast.LENGTH_SHORT
            ).show()
    }

    private fun checkFields() {
        email.apply {
            addTextChangedListener {
                if (email.text.toString().contains('@')) {
                    email.error = null
                    bEmail = true
                } else {
                    email.error = "Некорректный Email"
                    bEmail = false
                }
            }
        }
        name.apply {
            addTextChangedListener {
                if (name.text.toString().length > 1) {
                    name.error = null
                    bName = true
                } else {
                    name.error = "Имя не может быть короче одного символа"
                    bName = false
                }
            }
        }
        secondName.apply {
            addTextChangedListener {
                if (secondName.text.toString().length > 1) {
                    secondName.error = null
                    bSecondName = true
                } else {
                    secondName.error = "Фамлия не может быть короче одного символа"
                    bSecondName = false
                }
            }
        }
        phoneNumber.apply {
            addTextChangedListener {
                if (phoneNumber.text.toString().length > 1) {
                    phoneNumber.error = null
                    bPhone = true
                } else {
                    phoneNumber.error = "Некорректный номер"
                    bPhone = false
                }
            }
        }
        password.apply {
            addTextChangedListener {
                if (password.text.toString().length > 5) {
                    password.error = null
                    bPassword = true
                } else {
                    password.error = "Пароль должен быть больше 5 символов"
                    bPassword = false
                }
            }
        }
        passwordConfirm.apply {
            addTextChangedListener {
                if (passwordConfirm.text.toString().equals(password.text.toString())) {
                    passwordConfirm.error = null
                    bPasswordConfirm = true
                } else {
                    passwordConfirm.error = "Пароли не совпадают"
                    bPasswordConfirm = false
                }
            }
        }
    }

    private fun setSpinner() {
        var spinner = findViewById<Spinner>(bonch.dev.team4_application.R.id.spinner)

        val plants = arrayOf(
            "1 Класс",
            "2 Класс",
            "3 Класс",
            "4 Класс",
            "5 Класс",
            "6 Класс",
            "7 Класс",
            "8 Класс",
            "9 Класс",
            "10 Класс",
            "11 Класс"
        )

        val spinnerArrayAdapter = ArrayAdapter(
            this, bonch.dev.team4_application.R.layout.spiner_item, plants
        )
        spinnerArrayAdapter.setDropDownViewResource(bonch.dev.team4_application.R.layout.spiner_item)
        spinner.adapter = spinnerArrayAdapter
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}