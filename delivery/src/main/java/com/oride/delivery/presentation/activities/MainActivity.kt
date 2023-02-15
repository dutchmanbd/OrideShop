package com.oride.delivery.presentation.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.oride.extensions.context.findNavController
import com.oride.delivery.R
import com.oride.delivery.databinding.ActivityMainBinding
import com.oride.delivery.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val navController: NavController by lazy {
        findNavController(binding.navHostFragment.id)
    }

    private val fragmentIds by lazy {
        setOf(
            R.id.nav_home, R.id.nav_order, R.id.nav_earnings, R.id.nav_settings
        )
    }

    private val appBarConfiguration by lazy {
        AppBarConfiguration(fragmentIds)
    }

    override fun initializeViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        setListeners()
    }

    private fun initUI() {
        val navView: BottomNavigationView = binding.navView
        navView.itemIconTintList = null
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Set destination change listener
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            //Set back arrow icon
            binding.toolbar.apply {
                if (destination.id !in fragmentIds) {
                    setNavigationIcon(R.drawable.ic_baseline_arrow_back)
                }
            }
            when (destination.id) {
                R.id.welcomeFragment, R.id.loginFragment, R.id.biometricAuthFragment, R.id.facialCameraFragment, R.id.facialRecognitionFragment -> {
                    binding.appBar.visibility = View.GONE
                    binding.navView.visibility = View.GONE
                } R.id.chatFragment -> {
                    binding.navView.visibility = View.GONE
                } else -> {
                    binding.appBar.visibility = View.VISIBLE
                    binding.navView.visibility = View.VISIBLE
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setListeners() {
        var isTopSheetVisible = false
        binding.ivProfilePhoto.setOnClickListener {
            isTopSheetVisible = !isTopSheetVisible
            if (isTopSheetVisible) {
                binding.topSheet.root.visibility = View.VISIBLE
            } else {
                binding.topSheet.root.visibility = View.GONE
            }
        }

        binding.topSheet.llTopSheet.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    isTopSheetVisible = !isTopSheetVisible
                    binding.topSheet.root.visibility = View.GONE
                }
            }
            return@setOnTouchListener true
        }

        binding.topSheet.tvShift.setOnClickListener {
            isTopSheetVisible = !isTopSheetVisible
            binding.topSheet.root.visibility = View.GONE
            navController.navigate(R.id.shiftFragment)
        }
    }
}