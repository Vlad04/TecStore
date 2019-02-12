package itesm.com.tecstore


import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(){

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    var time=0

    /*private lateinit var callbackManager: CallbackManager
    private lateinit var shareDialog: ShareDialog*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*callbackManager = CallbackManager.Factory.create()
        shareDialog = ShareDialog(this@Main)*/

        setContentView(R.layout.activity_main)

        val adapter = Page(supportFragmentManager)
        val scale = resources.displayMetrics.density
        val maxY : Float = -70f * scale
        val maxWidth : Int = Image_logo.layoutParams.width
        val maxHeight : Int = Image_logo.layoutParams.height
        val minWidth : Int = (maxWidth * 0.8).toInt()
        val startTime = System.nanoTime()

        container.adapter = adapter

        container.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {



            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if(position == 0) {
                    Image_logo.y = maxY * positionOffset
                    //imageView4.y = maxY * positionOffset

                    var actualWidth = (maxWidth * (1 - (positionOffset * 0.2) )).toInt()
                    if(actualWidth > minWidth) {
                        var actualHeight = (maxHeight * (1.65 - (positionOffset * 0.2) )).toInt()
                        Image_logo.layoutParams.width = actualWidth
                        Image_logo.layoutParams.height = actualHeight
                        Image_logo.requestLayout()
                    }
                }
            }


            override fun onPageSelected(position: Int) {

            }


        }

        )


        tabLayout.setupWithViewPager(container)


        Default.setOnClickFeedback(LogIn_Button,{
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        })

        Default.setOnClickFeedback(Register_Button,{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)


        })



    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }*/



}