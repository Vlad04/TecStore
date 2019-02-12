package itesm.com.tecstore

import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.animation.AlphaAnimation
import java.util.regex.Pattern

/**
 * Created by vladimir on 12/02/19.
 */
class Default {

    companion object {

        /**
         * method is used for checking valid email id format.
         *
         * @param email
         * @return boolean true for valid false for invalid
         */
        fun isEmailValid(email: String): Boolean {
            //println("checking...")
            val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
            val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(email)
            return matcher.matches()
        }

        private var rect: Rect? = null
        private var cancelTrigger: Boolean = true

        fun setOnClickFeedback(view: View, callback: () -> Unit){
            view.setOnTouchListener { v, motionEvent ->


                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        val alpha = AlphaAnimation(0.5f, 0.5f)
                        alpha.duration = 0
                        alpha.fillAfter = true
                        view.startAnimation(alpha)
                        rect = Rect(v.left, v.top, v.right, v.bottom)
                        cancelTrigger = false
                    }

                    MotionEvent.ACTION_CANCEL -> {
                        val alpha = AlphaAnimation(1f, 1f)
                        alpha.duration = 0
                        alpha.fillAfter = true
                        view.startAnimation(alpha)
                    }

                    MotionEvent.ACTION_MOVE -> {
                        if(rect != null) {
                            if (!rect!!.contains(v.left + motionEvent.x.toInt(), v.top + motionEvent.y.toInt())) {
                                cancelTrigger = true
                                val alpha = AlphaAnimation(1f, 1f)
                                alpha.duration = 0
                                alpha.fillAfter = true
                                view.startAnimation(alpha)
                                rect = null
                            }
                        }
                    }

                    MotionEvent.ACTION_UP -> {
                        val alpha = AlphaAnimation(1f, 1f)
                        alpha.duration = 0
                        alpha.fillAfter = true
                        view.startAnimation(alpha)
                        if(!cancelTrigger)
                            callback()
                    }
                }
                true
            }
        }
    }
}