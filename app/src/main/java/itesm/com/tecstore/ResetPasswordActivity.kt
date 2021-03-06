package itesm.com.tecstore

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
import com.google.firebase.auth.FirebaseAuth
import itesm.com.tecstore.R

import itesm.com.tecstore.R.layout.activity_reset_password

class ResetPasswordActivity : AppCompatActivity() {

    private var inputEmail: EditText? = null
    private var btnReset: Button? = null
    private var auth: FirebaseAuth? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        inputEmail = findViewById<EditText>(R.id.email) as EditText
        btnReset = findViewById<Button>(R.id.btn_reset_password) as Button
        progressBar = findViewById<ProgressBar>(R.id.progressBar) as ProgressBar

        auth = FirebaseAuth.getInstance()


        btnReset!!.setOnClickListener(View.OnClickListener {
            val email = inputEmail!!.text.toString().trim { it <= ' ' }

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(application, R.string.enter_email, Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            progressBar!!.visibility = View.VISIBLE
            auth!!.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this@ResetPasswordActivity, R.string.email_sent, Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@ResetPasswordActivity, "Error", Toast.LENGTH_SHORT).show()
                        }

                        progressBar!!.visibility = View.GONE
                    }
        })
    }

}