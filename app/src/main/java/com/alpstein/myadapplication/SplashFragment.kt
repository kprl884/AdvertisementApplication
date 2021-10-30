package com.alpstein.myadapplication
d
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import java.util.*

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("Alp", "up")
        Run.after(9000) { nextScreen() }
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun nextScreen() {
        NavHostFragment.findNavController(this@SplashFragment).navigate(
            R.id.action_blankFragment_to_homeFragment,
            null,
            navOptions {
                anim {
                    enter = android.R.animator.fade_in
                    exit = android.R.animator.fade_out
                }
            }
        )
    }
}