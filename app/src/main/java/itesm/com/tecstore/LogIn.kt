package itesm.com.tecstore

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
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

import itesm.com.tecstore.R
import itesm.com.tecstore.ResetPasswordActivity

import itesm.com.tecstore.R.id.btn_signup
import itesm.com.tecstore.R.string.auth_failed
import itesm.com.tecstore.R.string.minimum_password

class LogIn : AppCompatActivity() {

    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null
    private var auth: FirebaseAuth? = null
    private var progressBar: ProgressBar? = null
    private var btnSignup: Button? = null
    private var btnLogin: Button? = null
    private var btnReset: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance()



        // set the view now
        setContentView(R.layout.activity_log_in)



        inputEmail = findViewById<EditText>(R.id.email) as EditText
        inputPassword = findViewById<EditText>(R.id.password) as EditText
        progressBar = findViewById<ProgressBar>(R.id.progressBar) as ProgressBar
        btnSignup = findViewById<Button>(R.id.btn_signup) as Button
        btnLogin = findViewById<Button>(R.id.btn_login) as Button
        btnReset = findViewById<Button>(R.id.btn_reset_password) as Button

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance()

        btnSignup!!.setOnClickListener { startActivity(Intent(this@LogIn, Register::class.java)) }

        btnReset!!.setOnClickListener { startActivity(Intent(this@LogIn, ResetPasswordActivity::class.java)) }

        btnLogin!!.setOnClickListener(View.OnClickListener {
            val email = inputEmail!!.text.toString().trim()
            val password = inputPassword!!.text.toString().trim()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, R.string.enter_email, Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, R.string.enter_password, Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            progressBar!!.visibility = View.VISIBLE

            //authenticate user
            auth!!.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this@LogIn) { task ->
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        progressBar!!.visibility = View.GONE
                        if (!task.isSuccessful) {
                            // there was an error
                            if (password.length < 6) {
                                inputPassword!!.error = getString(R.string.minimum_password)
                            } else {
                                Toast.makeText(this@LogIn, getString(R.string.auth_failed), Toast.LENGTH_LONG).show()
                            }
                        } else {
                            val intent = Intent(this@LogIn, Feed::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
        })
    }
}
