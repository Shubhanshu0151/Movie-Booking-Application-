package com.example.moviebookingapp.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviebookingapp.R
import com.example.moviebookingapp.RecyleView.BannerViewPageAdapter
import com.example.moviebookingapp.RecyleView.OnItemClickListener
import com.example.moviebookingapp.RecyleView.ViewPageAdapter
import com.example.moviebookingapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home), OnItemClickListener {
    private var _binding: FragmentHomeBinding? = null

    //    private val binding get() = _binding!!
    private val binding get() = retrieveBinding()

    private val autoSwipeHandler = Handler(Looper.getMainLooper())
    private lateinit var autoSwipeRunnable: Runnable
    private val autoSwipeInterval = 3000L // Auto swipe interval in milliseconds

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val image = listOf(
            R.drawable.lives,
            R.drawable.minion,
            R.drawable.nope,
            R.drawable.super_pets,
            R.drawable.thor
        )
        val imageTexts = listOf(
            "Thirteen Lives",
            "Minions: The Rise of Gru",
            "Nope",
            "DC League of Super-Pets",
            "Thor: Love and Thunder"
        )
        val horrorMovie = listOf(
            R.drawable.evil_return,
            R.drawable.old,
            R.drawable.london,
            R.drawable.pari
        )
        val horrorMovieText = listOf(
            "1920 Evil Return",
            "1920",
            "1920 London",
            "Pari"
        )
        val prices = listOf(
            "200₹ ",
            "300₹",
            "350₹",
            "450₹",
            "250₹"
        )
        val banner = listOf(
            R.drawable.banner_1,
            R.drawable.banner_2,
            R.drawable.banner_3
        )

        val adapter1 = ViewPageAdapter(image, imageTexts,this,prices)
        binding.viewPager1.adapter = adapter1

        val adapter2 = ViewPageAdapter(horrorMovie, horrorMovieText,this,prices)
        binding.viewPager2.adapter = adapter2

        val adapter3 = ViewPageAdapter(image, imageTexts,this,prices)
        binding.viewPager3.adapter = adapter3


        val Banner = BannerViewPageAdapter(banner)
        binding.vpBanner.adapter = Banner
        startAutoSwiping()


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        stopAutoSwiping()
        _binding = null
    }

    private fun startAutoSwiping() {
        autoSwipeRunnable = Runnable {
            val currentItem = retrieveBinding().vpBanner.currentItem
            val nextItem =
                (currentItem + 1) % retrieveBinding().vpBanner.adapter?.itemCount.orZero()
            retrieveBinding().vpBanner.setCurrentItem(nextItem, true)
            autoSwipeHandler.postDelayed(autoSwipeRunnable, autoSwipeInterval)
        }
        autoSwipeHandler.postDelayed(autoSwipeRunnable, autoSwipeInterval)
    }

    private fun retrieveBinding(): FragmentHomeBinding {
        return _binding
            ?: throw IllegalStateException("Attempt to access the binding when FragmentHomeBinding is null.")
    }


    private fun stopAutoSwiping() {
        autoSwipeHandler.removeCallbacks(autoSwipeRunnable)
    }

    private fun Int?.orZero() = this ?: 0
    override fun onItemClick(position: Int) {
        navigateToTimingFragment()
    }

    private fun navigateToTimingFragment() {
        val timingFragment = TimingFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, timingFragment)
        transaction.addToBackStack(null) // Add to back stack to allow back navigation
        transaction.commit()
    }
}