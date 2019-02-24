package itesm.com.tecstore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class Feed : AppCompatActivity() {

    private val RIPPLE_DURATION: Long = 250

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
    }
}
