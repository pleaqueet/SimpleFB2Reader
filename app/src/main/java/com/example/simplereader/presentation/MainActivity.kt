package com.example.simplereader.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.simplereader.R
import com.example.simplereader.databinding.ActivityMainBinding
import com.example.simplereader.presentation.add.AddBooksFragment
import com.example.simplereader.presentation.main.MainFragment
import com.example.simplereader.presentation.menu.MenuFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(MainFragment())
        title = "Главная"


        binding.mainBottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.add_item -> {
                    replaceFragment(AddBooksFragment())
                    title = "Добавить книгу"
                }
                R.id.main_item -> {
                    replaceFragment(MainFragment())
                    title = "Главная"
                }
                R.id.menu_item -> {
                    replaceFragment(MenuFragment())
                    title = "Меню"
                }
                else -> {
                    replaceFragment(MainFragment())
                    title = "Главная"
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}


