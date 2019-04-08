package itesm.com.tecstore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView


class ArticleList : AppCompatActivity() {
    lateinit var simpleList: ListView
    var countryList = arrayOf("Pelcuhe", "Playera", "Termo", "Gorra", "Bufanda", "Sudadera")
    var flags = intArrayOf(R.drawable.peluche, R.drawable.playera_tec, R.drawable.termo, R.drawable.gorra, R.drawable.bufanda, R.drawable.sudadera)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)
        simpleList = findViewById<ListView>(R.id.userlist) as ListView
        val customAdapter = Article_adapter(applicationContext, countryList, flags)
        simpleList.adapter = customAdapter
        
    }
}
