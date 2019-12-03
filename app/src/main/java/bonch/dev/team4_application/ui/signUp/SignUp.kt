package bonch.dev.team4_application.ui.signUp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import bonch.dev.team4_application.MainActivity
import bonch.dev.team4_application.R
//import bonch.dev.team4_application.ui.login.afterTextChanged
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
        setContentView(R.layout.activity_signup)

        email = findViewById(R.id.et_Email)
        name = findViewById(R.id.et_Name)
        secondName = findViewById(R.id.et_SecondName)
        phoneNumber = findViewById(R.id.et_PhoneNumber)
        password = findViewById(R.id.et_Password_signUp)
        passwordConfirm = findViewById(R.id.et_Password2_signUp)
        signUpBtn = findViewById(R.id.btn_SignUp)

        mDataBase = FirebaseDatabase.getInstance()
        mAuth = FirebaseAuth.getInstance()
        mReference = mDataBase.reference.child("Users")

        checkFields()
    }

    fun signUp(view: View) {
        if(bEmail&&bName&&bPassword&&bPasswordConfirm&&bPhone&&bSecondName)
        try {
            mAuth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser!!.uid
                        val currentUserDb = mReference.child(user)
                        currentUserDb.child("email").setValue(email.text.toString())
                        currentUserDb.child("name").setValue(name.text.toString())
                        currentUserDb.child("secondName").setValue(secondName.text.toString())
                        currentUserDb.child("password").setValue(password.text.toString())
                        currentUserDb.child("phone").setValue(phoneNumber.text.toString())


                        val intent = Intent(SignUpActivity@ this, MainActivity::class.java)
                        startActivity(intent)
                    } else {

                    }
                }
        } catch (err: HttpRetryException) {

        }
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
}