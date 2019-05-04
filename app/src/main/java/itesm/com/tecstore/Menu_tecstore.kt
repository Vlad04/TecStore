package itesm.com.tecstore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ApplicationInfo
import android.net.Uri


class Menu_tecstore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        linkConstraintLayout.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/TECstoremx/")))
        }

        logOutConstraintLayout.setOnClickListener {
            startActivity(Intent(this@Menu_tecstore, MainActivity::class.java))
            finish()
        }

    }

}
