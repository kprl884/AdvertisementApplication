package com.alpstein.myadapplication.view

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.alpstein.myadapplication.R
import com.alpstein.myadapplication.databinding.ActivityMainBinding
import com.flurry.android.FlurryAgent
import com.flurry.android.FlurryPerformance
import com.google.firebase.installations.FirebaseInstallations


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigation_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        //Integrate Flurry SDK for Android
        FlurryAgent.Builder()
            .withLogEnabled(true)
            .withDataSaleOptOut(false) //CCPA - the default value is false
            .withCaptureUncaughtExceptions(true)
            .withIncludeBackgroundSessionsInMetrics(true)
            .withLogLevel(Log.VERBOSE)
            .withPerformanceMetrics(FlurryPerformance.ALL)
            .build(this, "57XYPG3PYDSKKV9ZWG27")

        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))

        FirebaseInstallations.getInstance().id.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("Installations", "Installation ID: " + task.result)
            } else {
                Log.e("Installations", "Unable to get Installation ID")
            }
        }
    }
}