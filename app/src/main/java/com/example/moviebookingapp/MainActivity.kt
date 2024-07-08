package com.example.moviebookingapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.moviebookingapp.databinding.ActivityMainBinding
import com.example.moviebookingapp.fragment.FilterFragment
import com.example.moviebookingapp.fragment.HomeFragment
import com.example.moviebookingapp.fragment.PercentageFragment
import com.example.moviebookingapp.fragment.ProfileFragment
import com.example.moviebookingapp.fragment.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        replaceWithFragment(HomeFragment())

        binding.bnv.setOnItemSelectedListener  {
            when (it.itemId) {
                R.id.home -> replaceWithFragment(HomeFragment())
                R.id.search -> replaceWithFragment(SearchFragment())
                R.id.filter -> replaceWithFragment(FilterFragment())
                R.id.percentage -> replaceWithFragment(PercentageFragment())
                R.id.profile -> replaceWithFragment(ProfileFragment())
                else -> {

                }
            }
            true
        }
    }

    private fun replaceWithFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()

    }

}