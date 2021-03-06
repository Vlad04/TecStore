package itesm.com.tecstore

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

import itesm.com.tecstore.MainActivity
import itesm.com.tecstore.ResetPasswordActivity

import itesm.com.tecstore.R.id.sign_up_button

class Register : AppCompatActivity() {

    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null
    private var btnSignUp: Button? = null
    private var btnResetPassword: Button? = null
    private var progressBar: ProgressBar? = null
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance()

        btnSignUp = findViewById<Button>(R.id.sign_up_button) as Button
        inputEmail = findViewById<EditText>(R.id.email) as EditText
        inputPassword = findViewById<EditText>(R.id.password) as EditText
        progressBar = findViewById<ProgressBar>(R.id.progressBar) as ProgressBar
        btnResetPassword = findViewById<Button>(R.id.btn_reset_password) as Button

        btnResetPassword!!.setOnClickListener { startActivity(Intent(this@Register, LogIn::class.java)) }


        btnSignUp!!.setOnClickListener(View.OnClickListener {
            val email = inputEmail!!.text.toString().trim { it <= ' ' }
            val password = inputPassword!!.text.toString().trim { it <= ' ' }

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, R.string.enter_email, Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, R.string.enter_password, Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(applicationContext, R.string.min_characters, Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            progressBar!!.visibility = View.VISIBLE
            //create user
            auth!!.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this@Register) { task ->
                        progressBar!!.visibility = View.GONE
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful) {
                        } else {
                            startActivity(Intent(this@Register, MainActivity::class.java))
                            finish()
                        }
                    }
        })
    }

    override fun onResume() {
        super.onResume()
        progressBar!!.visibility = View.GONE
    }
}