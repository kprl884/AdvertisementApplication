package com.alpstein.myadapplication.view
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.alpstein.myadapplication.R
import com.alpstein.myadapplication.utils.Run
import java.util.*

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Run.after(11000) { nextScreen() }
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