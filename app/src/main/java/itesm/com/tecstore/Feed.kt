package itesm.com.tecstore

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_feed.*
import android.view.MenuItem
import android.widget.*
import com.rowland.cartcounter.view.CartCounterActionView

class Feed : AppCompatActivity() {
    var count=0
    private val TAG = "MainActivity"
    private var mDatabase: DatabaseReference? = null
    var adapter1: ArrayAdapter<String>? = null
    val data = arrayOf("Mochila", "Peluche", "Audifonos", "Playera", "Gorra", "Termo", "Bufanda", "Libreta")
    private var cartCount: Int = 0;

    private var mMessageReference: DatabaseReference? = null
// ...
    private var countTextView: TextView? = null;


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val itemData = menu.findItem(R.id.action_addcart)
        val actionView = itemData.actionView as CartCounterActionView
        actionView.setItemData(menu, itemData)
        actionView.setCount(cartCount)
        return super.onPrepareOptionsMenu(menu)
    }

    // Do actions based on selected menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_addcart -> {
                startActivity(Intent(this@Feed, ArticleList::class.java))
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

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
        val buy_button_1 = findViewById<Button>(R.id.button_article1) as Button
        val buy_button_2 = findViewById<Button>(R.id.button_article2) as Button
        val buy_button_3 = findViewById<Button>(R.id.button_article3) as Button
        val buy_button_4 = findViewById<Button>(R.id.button_article4) as Button
        val buy_button_5 = findViewById<Button>(R.id.button_article5) as Button
        val buy_button_6 = findViewById<Button>(R.id.button_article6) as Button
        val buy_button_7 = findViewById<Button>(R.id.button_article7) as Button
        val buy_button_8 = findViewById<Button>(R.id.button_article8) as Button




        search_editText!!.setOnClickListener { startActivity(Intent(this@Feed, Search::class.java)) }



        mDatabase = FirebaseDatabase.getInstance().reference
        mMessageReference = FirebaseDatabase.getInstance().getReference("Testing")




        buy_button_1.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/
            ++cartCount
            CartCounterActionView.setCountStep(this, 1)
            mDatabase!!.child("Testing").child("Ha comprado").setValue(count )
            count++

            retrieveStuffFromDatabase()
        }

        buy_button_2.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/
            ++cartCount
            CartCounterActionView.setCountStep(this, 1)
            mDatabase!!.child("Testing").child("Ha comprado").setValue(count )
            count++

            retrieveStuffFromDatabase()
        }

        buy_button_3.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/
            ++cartCount
            CartCounterActionView.setCountStep(this, 1)
            mDatabase!!.child("Testing").child("Ha comprado").setValue(count )
            count++

            retrieveStuffFromDatabase()
        }

        buy_button_4.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/
            ++cartCount
            CartCounterActionView.setCountStep(this, 1)
            mDatabase!!.child("Testing").child("Ha comprado").setValue(count )
            count++

            retrieveStuffFromDatabase()
        }

        buy_button_5.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/
            ++cartCount
            CartCounterActionView.setCountStep(this, 1)
            mDatabase!!.child("Testing").child("Ha comprado").setValue(count )
            count++

            retrieveStuffFromDatabase()
        }

        buy_button_6.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/
            ++cartCount
            CartCounterActionView.setCountStep(this, 1)
            mDatabase!!.child("Testing").child("Ha comprado").setValue(count )
            count++

            retrieveStuffFromDatabase()
        }

        buy_button_7.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/
            ++cartCount
            CartCounterActionView.setCountStep(this, 1)
            mDatabase!!.child("Testing").child("Ha comprado").setValue(count )
            count++

            retrieveStuffFromDatabase()
        }

        buy_button_8.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/
            ++cartCount
            CartCounterActionView.setCountStep(this, 1)
            mDatabase!!.child("Testing").child("Ha comprado").setValue(count )
            count++

            retrieveStuffFromDatabase()
        }

        // set on-click listener
        configurationButton_feed.setOnClickListener { startActivity(Intent(this@Feed, Menu_tecstore::class.java)) }

    }


}
