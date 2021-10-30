package com.alpstein.myadapplication

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.alpstein.myadapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigation_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        /*handler = Handler()

        val runnable: Runnable = Runnable {
            run {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.main_container, BlankFragment.newInstance())
                transaction.commit()
            }
        }


         */
        //handler.postDelayed(runnable, 9000)

    }
}