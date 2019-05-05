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
import com.google.android.gms.internal.lv
import android.R.layout.list_content
import android.widget.TextView
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener



class Search : AppCompatActivity() {
    var count=0
    private val TAG = "MainActivity"
    private var mDatabase: DatabaseReference? = null
    var adapter1: ArrayAdapter<Any>? = null

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
        val data = arrayOf(applicationContext.getString(R.string.ropa),applicationContext.getString(R.string.escuela),applicationContext.getString(R.string.gifts),applicationContext.getString(R.string.sports),applicationContext.getString(R.string.tecnologia))

        setContentView(R.layout.activity_search)
        val list = findViewById<View>(R.id.theList) as ListView
        adapter1 = ArrayAdapter(this@Search, android.R.layout.simple_list_item_1, data)
        list.setAdapter(adapter1);
        list.setTextFilterEnabled(true);

        theList.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val selectedFromList = theList.getItemAtPosition(position)
            val intent = Intent(this@Search, Feed::class.java)
            intent.putExtra("article", selectedFromList.toString())
            startActivity(intent)

            println("Chosen item = : " + selectedFromList.toString())
        })


        search_editText_activity.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                // TODO Auto-generated method stub
                this@Search.adapter1!!.getFilter().filter(arg0)

                theList.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
                    val selectedFromList = theList.getItemAtPosition(position)
                    val intent = Intent(this@Search, Feed::class.java)
                    intent.putExtra("article", selectedFromList.toString())
                    startActivity(intent)

                    println("Chosen item = : " + selectedFromList.toString())
                })

            }

            override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int,
                                           arg3: Int) {
                theList.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
                    val selectedFromList = theList.getItemAtPosition(position)
                    val intent = Intent(this@Search, Feed::class.java)
                    intent.putExtra("article", selectedFromList.toString())
                    startActivity(intent)

                    println("Chosen item = : " + selectedFromList.toString())
                })
            }

            override fun afterTextChanged(arg0: Editable) {
                theList.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
                    val selectedFromList = theList.getItemAtPosition(position)
                    val intent = Intent(this@Search, Feed::class.java)
                    intent.putExtra("article", selectedFromList.toString())
                    startActivity(intent)

                    println("Chosen item = : " + selectedFromList.toString())
                })
            }
        })


        search_editText_activity.setOnClickListener {
            search_editText_activity.hint=""
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
