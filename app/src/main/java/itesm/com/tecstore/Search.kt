package itesm.com.tecstore

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*
import itesm.com.tecstore.R.id.configurationButton_feed
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_feed.*
import android.text.Editable
import android.text.TextWatcher
import android.R.attr.data
import android.R.attr.data
import kotlinx.android.synthetic.main.activity_search.*

class Search : AppCompatActivity() {
    var count=0
    private val TAG = "MainActivity"
    private var mDatabase: DatabaseReference? = null
    var adapter1: ArrayAdapter<String>? = null
    val data = arrayOf("Mochila", "Peluche", "Audifonos", "Playera", "Gorra", "Termo", "Bufanda", "Libreta")

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
        setContentView(R.layout.activity_search)
        val list = findViewById<View>(R.id.theList) as ListView
        adapter1 = ArrayAdapter(this@Search, android.R.layout.simple_list_item_1, data)
        list.setAdapter(adapter1);
        list.setTextFilterEnabled(true);

        search_editText_activity.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                // TODO Auto-generated method stub
                this@Search.adapter1!!.getFilter().filter(arg0)
            }

            override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int,
                                           arg3: Int) {
                // TODO Auto-generated method stub

            }

            override fun afterTextChanged(arg0: Editable) {
                // TODO Auto-generated method stub

            }
        })


        search_editText_activity.setOnClickListener {
            list.visibility=View.VISIBLE
        }



        mDatabase = FirebaseDatabase.getInstance().reference
        mMessageReference = FirebaseDatabase.getInstance().getReference("Testing")




        /*buy_button!!.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/

            mDatabase!!.child("Testing").child("Ha comprado").setValue(count )
            count++

            retrieveStuffFromDatabase()
        }*/

        // set on-click listener

    }
}
