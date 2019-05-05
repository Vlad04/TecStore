package itesm.com.tecstore

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.TransactionDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_article_list.*


class ArticleList : AppCompatActivity(), BillingProcessor.IBillingHandler {
    internal var bp: BillingProcessor? = null
    lateinit var textView: TextView

    override fun onBillingInitialized() {
    }

    override fun onPurchaseHistoryRestored() {
    }

    override fun onProductPurchased(productId: String, details: TransactionDetails?) {
    }

    override fun onBillingError(errorCode: Int, error: Throwable?) {
    }

    lateinit var simpleList: ListView
    var ref = FirebaseDatabase.getInstance().reference
    var currentuser = FirebaseAuth.getInstance().currentUser!!.uid

    var descripciones_Ref = FirebaseDatabase.getInstance().getReference("Descripciones")



    fun retrieveStuffFromDatabase(){

        FirebaseDatabase.getInstance().reference.child("Descripciones").addValueEventListener(object: ValueEventListener{

            override fun onDataChange(p0: DataSnapshot?) {
                println("This is the value of snapshot: " + p0!!.child("Playera").value).toString()
                println("This is the value of snapshot: " + p0!!.child("Peluche").value).toString()
                println("This is the value of snapshot: " + p0!!.child("Termo").value).toString()
                println("This is the value of snapshot: " + p0!!.child("Sudadera").value).toString()
                println("This is the value of snapshot: " + p0!!.child("Gorra").value).toString()
                println("This is the value of snapshot: " + p0!!.child("Bufanda").value).toString()
                println("This is the value of snapshot: " + p0!!.child("Cuaderno").value).toString()
                println("This is the value of snapshot: " + p0!!.child("Mochila").value).toString()

                var playera_descripcion=p0!!.child("Playera").value.toString()
                var peluche_descripcion=p0!!.child("Peluche").value.toString()
                var termo_descripcion=p0!!.child("Termo").value.toString()
                var sudadera_descripcion=p0!!.child("Sudadera").value.toString()
                var gorra_descripcion=p0!!.child("Gorra").value.toString()
                var bufanda_descripcion=p0!!.child("Bufanda").value.toString()
                var cuaderno_descripcion=p0!!.child("Cuaderno").value.toString()
                var mochila_descripcion=p0!!.child("Mochila").value.toString()

                var descripciones = arrayOf(peluche_descripcion,playera_descripcion,termo_descripcion,gorra_descripcion,bufanda_descripcion,sudadera_descripcion,cuaderno_descripcion,mochila_descripcion)
                var countryList = arrayOf("Pelcuhe", "Playera", "Termo", "Gorra", "Bufanda", "Sudadera", "Cuaderno","Mochila")
                var flags = intArrayOf(R.drawable.peluche, R.drawable.playera_tec, R.drawable.termo, R.drawable.gorra, R.drawable.bufanda, R.drawable.sudadera, R.drawable.cuaderno,R.drawable.mochila)

                val customAdapter = Article_adapter(applicationContext, countryList, flags, descripciones)
                simpleList.adapter = customAdapter


            }

            override fun onCancelled(p0: DatabaseError?) {
                println("Something went wrong when retrieving data!")
            }

        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retrieveStuffFromDatabase()
        setContentView(R.layout.activity_article_list)
        simpleList = findViewById<ListView>(R.id.userlist) as ListView
        bp = BillingProcessor(this, null, this)

        button2.setOnClickListener {
            bp!!.purchase(this@ArticleList, "android.test.refunded")
            println("BILLING PROCESS= "+bp)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (!bp!!.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
