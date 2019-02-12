package itesm.com.tecstore

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by vladimir on 12/02/19.
 */

/**
 * Created by vladimir on 6/12/17.
 */
class Page(fm: FragmentManager) : FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        when (position) {

            0 -> return Capa1()
            1-> return Capa2()
            2-> return Capa3()

            else -> return Capa1()
        }
    }

    override fun getCount(): Int {
        return 3
    }

}