package itesm.com.tecstore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ApplicationInfo
import android.net.Uri
import kotlinx.android.synthetic.main.activity_search.*


class Menu_tecstore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        myAppliedPostsConstraintLayout.setOnClickListener {
            val intent = Intent(this@Menu_tecstore, Feed::class.java)
            intent.putExtra("article", applicationContext.getString(R.string.ropa))
            startActivity(intent)
        }

        findJobsConstraintLayout.setOnClickListener{
            val intent = Intent(this@Menu_tecstore, Feed::class.java)
            intent.putExtra("article", applicationContext.getString(R.string.regalos))
            startActivity(intent)
        }

        recommendedJobsConstraintLayout.setOnClickListener {
            val intent = Intent(this@Menu_tecstore, Feed::class.java)
            intent.putExtra("article", applicationContext.getString(R.string.sports))
            startActivity(intent)
        }

        myNetworkConstraintLayout.setOnClickListener {
            val intent = Intent(this@Menu_tecstore, Feed::class.java)
            intent.putExtra("article", applicationContext.getString(R.string.escuela))
            startActivity(intent)
        }

        linkConstraintLayout.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/TECstoremx/")))
        }

        logOutConstraintLayout.setOnClickListener {
            startActivity(Intent(this@Menu_tecstore, MainActivity::class.java))
            finish()
        }

    }

}
