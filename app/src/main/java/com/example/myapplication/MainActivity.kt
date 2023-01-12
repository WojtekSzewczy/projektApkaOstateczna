package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigationView:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)
        instance=this
        bottomNavigationView = findViewById(R.id.bottomNavigation)

        bottomNavigationView.visibility=View.GONE
        bottomNavigationView.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView5) as NavHostFragment

        val navController = navHostFragment.navController

        bottomNavigationView.setupWithNavController(navController)

    }

    companion object {
        lateinit var instance: MainActivity
    }
}