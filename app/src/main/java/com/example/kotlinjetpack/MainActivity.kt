package com.example.kotlinjetpack

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.MenuItem

import com.google.android.material.bottomnavigation.BottomNavigationView

import androidx.fragment.app.Fragment
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log

import com.example.kotlinjetpack.ui.fragment.DetailFragment
import com.example.kotlinjetpack.ui.fragment.ListFragment
import com.example.kotlinjetpack.ui.fragment.MeFragment


class MainActivity : AppCompatActivity() {
    private lateinit var botttomNavigationView: BottomNavigationView
    private var lastIndex: Int = 0
    val mFragments = ArrayList<Fragment>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initWidget()
        initBottomNavigationView()
        // 初始化展示MessageFragment
        setFragmentPosition(0)
    }

    private fun initBottomNavigationView() {

        botttomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            Log.e("MainActivity",""+item.itemId)
            when (item.itemId) {
                R.id.infoFragment -> setFragmentPosition(0)
                R.id.marketFragment -> setFragmentPosition(1)
                R.id.meFragment -> setFragmentPosition(2)
                else -> {
                }
            }
            // 这里注意返回true,否则点击失效
            true
        })
    }

    private fun initWidget() {
        mFragments.add(DetailFragment())
        mFragments.add(ListFragment())
        mFragments.add(MeFragment())
        botttomNavigationView = findViewById(R.id.bnv_view);
    }

    private fun setFragmentPosition(i: Int) {
        val ft = supportFragmentManager.beginTransaction()
        val currentFragment = mFragments.get(i)
        val lastFragment = mFragments.get(lastIndex)
        lastIndex = i
        if (lastFragment != null) {
            ft.hide(lastFragment)
        }
        if (currentFragment != null) {
            if (!currentFragment.isAdded) {
                supportFragmentManager.beginTransaction().remove(currentFragment).commit()
                ft.add(R.id.my_nav_host_fragment, currentFragment)
            }
        }
        if (currentFragment != null) {
            ft.show(currentFragment)
        }
        ft.commitAllowingStateLoss()
    }
}
