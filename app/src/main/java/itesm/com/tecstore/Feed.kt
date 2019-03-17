package itesm.com.tecstore

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.*
import itesm.com.tecstore.R.id.configurationButton_feed

class Feed : AppCompatActivity() {
    var count=0

    private var mDatabase: DatabaseReference? = null
    private var mMessageReference: DatabaseReference? = null
// ...

    private fun retrieveStuffFromDatabase(){

        FirebaseDatabase.getInstance().reference.child("Inventario").addValueEventListener(object: ValueEventListener{

            override fun onDataChange(p0: DataSnapshot?) {
                println("This is the value of snapshot: " + p0!!.value)
            }

            override fun onCancelled(p0: DatabaseError?) {
                println("Something went wrong when retrieving data!")
            }

        })

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        val configurationButton_feed = findViewById<ImageView>(R.id.configurationButton_feed) as ImageView
        val buy_button = findViewById<Button>(R.id.button_article1) as Button

        mDatabase = FirebaseDatabase.getInstance().reference
        mMessageReference = FirebaseDatabase.getInstance().getReference("Testing")




        buy_button!!.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/

            mDatabase!!.child("Testing").child("Ha comprado").setValue(count )
            count++

            retrieveStuffFromDatabase()
        }

        // set on-click listener
        configurationButton_feed!!.setOnClickListener { startActivity(Intent(this@Feed, Menu::class.java)) }

    }
}
