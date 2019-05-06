package itesm.com.tecstore

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.Menu
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_feed.*
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.rowland.cartcounter.view.CartCounterActionView

class Feed : AppCompatActivity() {
    var count=0
    private val TAG = "MainActivity"
    private var mDatabase: DatabaseReference? = null
    var adapter1: ArrayAdapter<String>? = null
    val data = arrayOf("Mochila", "Peluche", "Audifonos", "Playera", "Gorra", "Termo", "Bufanda", "Libreta")
    private var cartCount: Int = 0;
    var borde_verde_playera = false
    var borde_verde_peluche = false
    var borde_verde_cuaderno = false
    var borde_verde_mochila = false
    var borde_verde_bufanda = false
    var borde_verde_termo = false
    var borde_verde_sudadera = false
    var borde_verde_gorra = false
    var playeras_cantidad = 0
    var gorras_cantidad = 0
    var peluches_cantidad = 0
    var cuadernos_cantidad = 0
    var mochila_cantidad = 0
    var bufanda_cantidad = 0
    var termo_cantidad = 0
    var sudadera_cantidad = 0
    var currentuser = FirebaseAuth.getInstance().currentUser!!.uid

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
        val constraint_playera = findViewById<ConstraintLayout>(R.id.constraint_playera) as ConstraintLayout
        val constraint_gorra = findViewById<ConstraintLayout>(R.id.constraint_gorra) as ConstraintLayout
        val constraint_mochila = findViewById<ConstraintLayout>(R.id.constraint_mochila) as ConstraintLayout
        val constraint_peluche = findViewById<ConstraintLayout>(R.id.constraint_peluche) as ConstraintLayout
        val constraint_termo = findViewById<ConstraintLayout>(R.id.constraint_termo) as ConstraintLayout
        val constraint_sudadera = findViewById<ConstraintLayout>(R.id.constraint_sudadera) as ConstraintLayout
        val constraint_cuaderno = findViewById<ConstraintLayout>(R.id.constraint_libreta) as ConstraintLayout
        val constraint_bufanda = findViewById<ConstraintLayout>(R.id.constraint_bufanda) as ConstraintLayout
        CartCounterActionView.setCountStep(this,0)

        println("CURRENT USER FEED 1:"+currentuser)

        try {
            var bundle: Bundle? = intent.extras
            var message = bundle!!.getString("article") // 1
            var strUser: String = intent.getStringExtra("article") // 2

            if(message==applicationContext.getString(R.string.ropa))
            {
                first_article.visibility = View.VISIBLE
                second_article.visibility = View.GONE
                third_article.visibility = View.VISIBLE
                fourth_article.visibility = View.GONE
                fifth_article.visibility = View.VISIBLE
                sixth_article.visibility = View.VISIBLE
                seventh_article.visibility = View.GONE
                eigth_article.visibility = View.GONE
            }
            else if(message==applicationContext.getString(R.string.escuela)){
                first_article.visibility = View.GONE
                second_article.visibility = View.GONE
                third_article.visibility = View.GONE
                fourth_article.visibility = View.GONE
                fifth_article.visibility = View.GONE
                sixth_article.visibility = View.GONE
                seventh_article.visibility = View.VISIBLE
                eigth_article.visibility = View.VISIBLE

            }
            else if(message==applicationContext.getString(R.string.regalos)) {
                first_article.visibility = View.GONE
                second_article.visibility = View.VISIBLE
                third_article.visibility = View.GONE
                fourth_article.visibility = View.GONE
                fifth_article.visibility = View.GONE
                sixth_article.visibility = View.GONE
                seventh_article.visibility = View.GONE
                eigth_article.visibility = View.GONE
            }
            else if(message==applicationContext.getString(R.string.sports)) {
                first_article.visibility = View.GONE
                second_article.visibility = View.GONE
                third_article.visibility = View.GONE
                fourth_article.visibility = View.VISIBLE
                fifth_article.visibility = View.GONE
                sixth_article.visibility = View.GONE
                seventh_article.visibility = View.GONE
                eigth_article.visibility = View.GONE
            }

        }catch (e:Exception)
        {

            println("ERROR TRATANDO DE HACER GET EXTRA")
        }

        search_editText!!.setOnClickListener {
            startActivity(Intent(this@Feed, Search::class.java))
        }



        mDatabase = FirebaseDatabase.getInstance().reference
        mMessageReference = FirebaseDatabase.getInstance().getReference("Testing")


        buy_button_1.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/
            ++cartCount
            CartCounterActionView.setCountStep(this, 1)
            playeras_cantidad++
            mDatabase!!.child(currentuser.toString()).child("Playera").setValue(playeras_cantidad )
            count++
            constraint_playera.setBackgroundResource(R.drawable.borde_verde)
            borde_verde_playera=true
            println("borde verde playera click = " + borde_verde_playera)

            Toast.makeText(this@Feed,"Playeras compradas: "+playeras_cantidad,Toast.LENGTH_SHORT).show()
            retrieveStuffFromDatabase()
        }

        buy_button_2.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/
            ++cartCount
            peluches_cantidad++
            CartCounterActionView.setCountStep(this, 1)
            mDatabase!!.child(currentuser.toString()).child("Peluche").setValue(peluches_cantidad )
            count++
            constraint_peluche.setBackgroundResource(R.drawable.borde_verde)
            borde_verde_peluche=true
            Toast.makeText(this@Feed,"Peluches comprados: "+peluches_cantidad,Toast.LENGTH_SHORT).show()


            retrieveStuffFromDatabase()
        }

        buy_button_3.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/
            ++cartCount
            CartCounterActionView.setCountStep(this, 1)
            gorras_cantidad++
            mDatabase!!.child(currentuser.toString()).child("Gorra").setValue(gorras_cantidad )
            count++
            constraint_gorra.setBackgroundResource(R.drawable.borde_verde)
            borde_verde_gorra=true
            Toast.makeText(this@Feed,"Gorras compradas: "+gorras_cantidad,Toast.LENGTH_SHORT).show()


            retrieveStuffFromDatabase()
        }

        buy_button_4.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/
            ++cartCount
            CartCounterActionView.setCountStep(this, 1)
            termo_cantidad++

            mDatabase!!.child(currentuser.toString()).child("Termo").setValue(termo_cantidad )
            count++
            constraint_termo.setBackgroundResource(R.drawable.borde_verde)
            borde_verde_termo=true
            Toast.makeText(this@Feed,"Termos comprados: "+termo_cantidad,Toast.LENGTH_SHORT).show()


            retrieveStuffFromDatabase()
        }

        buy_button_5.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/
            ++cartCount
            CartCounterActionView.setCountStep(this, 1)
            bufanda_cantidad++

            mDatabase!!.child(currentuser.toString()).child("Bufanda").setValue(bufanda_cantidad )
            count++
            constraint_bufanda.setBackgroundResource(R.drawable.borde_verde)
            borde_verde_bufanda=true
            Toast.makeText(this@Feed,"Bufandas compradas: "+bufanda_cantidad,Toast.LENGTH_SHORT).show()


            retrieveStuffFromDatabase()
        }

        buy_button_6.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/
            ++cartCount
            CartCounterActionView.setCountStep(this, 1)
            sudadera_cantidad++

            mDatabase!!.child(currentuser.toString()).child("Sudadera").setValue(sudadera_cantidad )
            count++
            constraint_sudadera.setBackgroundResource(R.drawable.borde_verde)
            borde_verde_sudadera=true
            Toast.makeText(this@Feed,"Sudaderas compradas: "+sudadera_cantidad,Toast.LENGTH_SHORT).show()


            retrieveStuffFromDatabase()
        }

        buy_button_7.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/
            ++cartCount
            CartCounterActionView.setCountStep(this, 1)
            mochila_cantidad++

            mDatabase!!.child(currentuser.toString()).child("Mochila").setValue(mochila_cantidad )
            count++
            constraint_mochila.setBackgroundResource(R.drawable.borde_verde)
            borde_verde_mochila=true
            Toast.makeText(this@Feed,"Mochilas compradas: "+mochila_cantidad,Toast.LENGTH_SHORT).show()


            retrieveStuffFromDatabase()
        }

        buy_button_8.setOnClickListener {
            //Crea tabla
            /*mDatabase!!.child("Vladimir").setValue("24")
            Toast.makeText(this@Feed,"Testing",Toast.LENGTH_SHORT).show()*/
            ++cartCount
            CartCounterActionView.setCountStep(this, 1)
            cuadernos_cantidad++

            mDatabase!!.child(currentuser.toString()).child("Cuaderno").setValue(cuadernos_cantidad )
            count++
            constraint_libreta.setBackgroundResource(R.drawable.borde_verde)
            borde_verde_cuaderno=true
            Toast.makeText(this@Feed,"Cuadernos comprados: "+cuadernos_cantidad,Toast.LENGTH_SHORT).show()


            retrieveStuffFromDatabase()
        }


        constraint_playera.setOnClickListener {
            println("borde verde playera 1 = " + borde_verde_playera)

            if(borde_verde_playera==true) {
                println("borde verde playera 2 = " + borde_verde_playera)
                --cartCount
                playeras_cantidad--
                mDatabase!!.child(currentuser.toString()).child("Playera").setValue(playeras_cantidad )

                CartCounterActionView.setCountStep(this, -1)
                if(playeras_cantidad==0) {
                    constraint_playera.setBackgroundResource(R.drawable.borde_gris)
                    borde_verde_playera=false
                    Toast.makeText(this@Feed,"Playeras comprados: "+playeras_cantidad,Toast.LENGTH_SHORT).show()

                }

            }
        }

        constraint_peluche.setOnClickListener {
            if(borde_verde_peluche==true) {
                peluches_cantidad--
                --cartCount
                mDatabase!!.child(currentuser.toString()).child("Peluche").setValue(peluches_cantidad )

                CartCounterActionView.setCountStep(this, -1)
                if(peluches_cantidad==0) {
                    constraint_peluche.setBackgroundResource(R.drawable.borde_gris)
                    borde_verde_peluche=false
                    Toast.makeText(this@Feed,"Peluches comprados: "+peluches_cantidad,Toast.LENGTH_SHORT).show()

                }
            }
        }

        constraint_gorra.setOnClickListener {
            if(borde_verde_gorra==true) {
                gorras_cantidad--
                --cartCount
                mDatabase!!.child(currentuser.toString()).child("Gorra").setValue(gorras_cantidad )

                CartCounterActionView.setCountStep(this, -1)
                if(gorras_cantidad==0) {
                    constraint_gorra.setBackgroundResource(R.drawable.borde_gris)
                    borde_verde_gorra=false
                    Toast.makeText(this@Feed,"Gorras compradas: "+gorras_cantidad,Toast.LENGTH_SHORT).show()

                }
            }
        }

        constraint_termo.setOnClickListener {
            if(borde_verde_termo==true) {

                termo_cantidad--
                --cartCount
                mDatabase!!.child(currentuser.toString()).child("Termo").setValue(termo_cantidad )

                CartCounterActionView.setCountStep(this, -1)
                if(termo_cantidad==0) {
                    constraint_termo.setBackgroundResource(R.drawable.borde_gris)
                    borde_verde_termo=false
                    Toast.makeText(this@Feed,"Termos comprados: "+termo_cantidad,Toast.LENGTH_SHORT).show()

                }
            }
        }

        constraint_bufanda.setOnClickListener {
            if(borde_verde_bufanda==true) {

                bufanda_cantidad--
                --cartCount
                mDatabase!!.child(currentuser.toString()).child("Bufanda").setValue(bufanda_cantidad )

                CartCounterActionView.setCountStep(this, -1)
                if(bufanda_cantidad==0) {
                    constraint_bufanda.setBackgroundResource(R.drawable.borde_gris)
                    borde_verde_bufanda=false
                    Toast.makeText(this@Feed,"Bufandas compradas: "+bufanda_cantidad,Toast.LENGTH_SHORT).show()

                }
            }
        }

        constraint_sudadera.setOnClickListener {
            if(borde_verde_sudadera==true) {

                sudadera_cantidad--
                --cartCount
                mDatabase!!.child(currentuser.toString()).child("Sudadera").setValue(sudadera_cantidad )

                CartCounterActionView.setCountStep(this, -1)
                if(sudadera_cantidad==0) {
                    constraint_sudadera.setBackgroundResource(R.drawable.borde_gris)
                    borde_verde_sudadera=false
                    Toast.makeText(this@Feed,"Sudaderas compradas: "+sudadera_cantidad,Toast.LENGTH_SHORT).show()

                }
            }
        }

        constraint_mochila.setOnClickListener {
            if(borde_verde_mochila==true) {

                mochila_cantidad--
                mDatabase!!.child(currentuser.toString()).child("Mochila").setValue(mochila_cantidad )

                constraint_mochila.setBackgroundResource(R.drawable.borde_gris)
                --cartCount
                CartCounterActionView.setCountStep(this, -1)
                if(mochila_cantidad==0) {
                    constraint_mochila.setBackgroundResource(R.drawable.borde_gris)
                    borde_verde_mochila=false
                    Toast.makeText(this@Feed,"Mochilas compradas: "+mochila_cantidad,Toast.LENGTH_SHORT).show()

                }
            }
        }

        constraint_libreta.setOnClickListener {
            if(borde_verde_cuaderno==true) {
                cuadernos_cantidad--
                --cartCount
                mDatabase!!.child(currentuser.toString()).child("Cuaderno").setValue(cuadernos_cantidad )

                CartCounterActionView.setCountStep(this, -1)
                if(cuadernos_cantidad==0) {
                    constraint_libreta.setBackgroundResource(R.drawable.borde_gris)
                    borde_verde_cuaderno=false
                    Toast.makeText(this@Feed,"Cuadernos comprados: "+cuadernos_cantidad,Toast.LENGTH_SHORT).show()

                }
            }
        }



        // set on-click listener
        configurationButton_feed.setOnClickListener { startActivity(Intent(this@Feed, Menu_tecstore::class.java)) }

    }


}
