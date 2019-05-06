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
import org.json.JSONObject
import com.google.firebase.database.DataSnapshot






class ArticleList : AppCompatActivity(), BillingProcessor.IBillingHandler {
    internal var bp: BillingProcessor? = null
    lateinit var textView: TextView
    private var mDatabase: DatabaseReference? = null
    var currentuser = FirebaseAuth.getInstance().currentUser!!.uid
    private var mMessageReference: DatabaseReference? = null
    var cantidadPlayeras=0
    var cantidadPeluches=0
    var cantidadTermos=0
    var cantidadGorras=0
    var cantidadBufandas=0
    var cantidadSudadera=0
    var cantidadLibreta=0
    var cantidadMochila=0


    var playera_boolean=false
    var peluche_boolean=false
    var termo_boolean=false
    var gorra_boolea=false
    var bufanda_boolean=false
    var sudadera_boolean=false
    var libreta_boolean=false
    var mochila_boolean=false

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

                val descripciones: List<String>
                descripciones = ArrayList()

                val countryList: List<String>
                countryList = ArrayList()

                val flags: List<Int>
                flags = ArrayList()

                /*descripciones.add("1")
                descripciones.add("2")
                descripciones.add("3")
                descripciones.add("4")
                descripciones.add("5")
                descripciones.add("6")
                descripciones.add("7")
                descripciones.add("8")


                countryList.add("1")
                countryList.add("2")
                countryList.add("3")
                countryList.add("4")
                countryList.add("5")
                countryList.add("6")
                countryList.add("7")
                countryList.add("8")*/


                //var descripciones = arrayOf(peluche_descripcion,playera_descripcion,termo_descripcion,gorra_descripcion,bufanda_descripcion,sudadera_descripcion,cuaderno_descripcion,mochila_descripcion)
                //var countryList = arrayOf("Pelcuhe", "Playera", "Termo", "Gorra", "Bufanda", "Sudadera", "Cuaderno","Mochila")
                //var flags = intArrayOf(R.drawable.peluche, R.drawable.playera_tec, R.drawable.termo, R.drawable.gorra, R.drawable.bufanda, R.drawable.sudadera, R.drawable.cuaderno,R.drawable.mochila)




                FirebaseDatabase.getInstance().reference.child(currentuser.toString()).addValueEventListener(object: ValueEventListener{

                    override fun onDataChange(p0: DataSnapshot?) {
                        for (`val` in p0!!.getChildren()) {
                            //I am not sure what record are you specifically looking for
                            //This is if you are getting the Key which is the record ID for your Coupon Object
                            if (`val`.getKey().contains("Playera")) {
                                cantidadPlayeras= `val`.getValue().toString().toInt()

                            }

                            if (`val`.getKey().contains("Peluche")) {
                                cantidadPeluches= `val`.getValue().toString().toInt()

                            }

                            if (`val`.getKey().contains("Termo")) {
                                cantidadTermos= `val`.getValue().toString().toInt()

                            }

                            if (`val`.getKey().contains("Bufanda")) {
                                cantidadBufandas= `val`.getValue().toString().toInt()

                            }

                            if (`val`.getKey().contains("Cuaderno")) {
                                cantidadLibreta= `val`.getValue().toString().toInt()

                            }

                            if (`val`.getKey().contains("Mochila")) {
                                cantidadMochila= `val`.getValue().toString().toInt()

                            }

                            if (`val`.getKey().contains("Gorra")) {
                                cantidadGorras= `val`.getValue().toString().toInt()

                            }

                            if (`val`.getKey().contains("Sudadera")) {
                                cantidadSudadera= `val`.getValue().toString().toInt()

                            }


                            //This is if your are querying for the hotel child
                            /*if (`val`.child("Playera").value.toString().toInt()>0) {
                                println("me mega mame x2")
                            }*/
                        }
                        println("cantidad de playera es=" + cantidadPlayeras)
                        println("cantidad de bufandas es=" + cantidadBufandas)
                        println("cantidad de gorras es=" + cantidadGorras)
                        println("cantidad de libreta es=" + cantidadLibreta)
                        println("cantidad de mochila es=" + cantidadMochila)
                        println("cantidad de peluche es=" + cantidadPeluches)
                        println("cantidad de sudadera es=" + cantidadSudadera)
                        println("cantidad de termos es=" + cantidadTermos)

                        if(cantidadPlayeras>0){
                            descripciones.add(playera_descripcion)
                            countryList.add(applicationContext.getString(R.string.Playera))
                            flags.add(R.drawable.playera_tec)
                        }

                        if(cantidadBufandas>0){
                            descripciones.add(bufanda_descripcion)
                            countryList.add(applicationContext.getString(R.string.Bufanda))
                            flags.add(R.drawable.bufanda)
                        }

                        if(cantidadPeluches>0){
                            descripciones.add(peluche_descripcion)
                            countryList.add(applicationContext.getString(R.string.Peluche))
                            flags.add(R.drawable.peluche)
                        }

                        if(cantidadGorras>0){
                            descripciones.add(gorra_descripcion)
                            countryList.add(applicationContext.getString(R.string.Gorra))
                            flags.add(R.drawable.gorra)
                        }

                        if(cantidadLibreta>0){
                            descripciones.add(cuaderno_descripcion)
                            countryList.add(applicationContext.getString(R.string.Libreta))
                            flags.add(R.drawable.cuaderno)
                        }

                        if(cantidadMochila>0){
                            descripciones.add(mochila_descripcion)
                            countryList.add(applicationContext.getString(R.string.Mochila))
                            flags.add(R.drawable.mochila)
                        }

                        if(cantidadSudadera>0){
                            descripciones.add(sudadera_descripcion)
                            countryList.add(applicationContext.getString(R.string.Sudadera))
                            flags.add(R.drawable.sudadera)
                        }

                        if(cantidadTermos>0){
                            descripciones.add(termo_descripcion)
                            countryList.add(applicationContext.getString(R.string.Termo))
                            flags.add(R.drawable.termo)
                        }

                        val customAdapter = Article_adapter(applicationContext, countryList, flags, descripciones)
                        simpleList.adapter = customAdapter

                    }

                    override fun onCancelled(p0: DatabaseError?) {
                        println("Something went wrong when retrieving data!")
                    }

                })


                FirebaseDatabase.getInstance().reference.child(currentuser.toString()).child("Playera").addValueEventListener(object: ValueEventListener{

                        override fun onDataChange(p0: DataSnapshot?) {
                            println("This is the value of PLAYERA: " + p0!!.value)




                        }

                        override fun onCancelled(p0: DatabaseError?) {
                            println("Something went wrong when retrieving data!")
                        }

                })


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
        mDatabase = FirebaseDatabase.getInstance().reference




        button2.setOnClickListener {
            bp!!.purchase(this@ArticleList, "android.test.refunded")
            println("BILLING PROCESS= "+bp)
            mDatabase!!.child(currentuser.toString()).child("Playera").setValue(0)
            mDatabase!!.child(currentuser.toString()).child("Peluche").setValue(0)
            mDatabase!!.child(currentuser.toString()).child("Gorra").setValue(0)
            mDatabase!!.child(currentuser.toString()).child("Termo").setValue(0)
            mDatabase!!.child(currentuser.toString()).child("Bufanda").setValue(0)
            mDatabase!!.child(currentuser.toString()).child("Sudadera").setValue(0)
            mDatabase!!.child(currentuser.toString()).child("Mochila").setValue(0)
            mDatabase!!.child(currentuser.toString()).child("Cuaderno").setValue(0)


        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (!bp!!.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
