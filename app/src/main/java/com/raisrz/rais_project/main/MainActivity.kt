package com.raisrz.rais_project.main

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.raisrz.rais_project.MyApplication
import com.raisrz.rais_project.R
import com.raisrz.rais_project.core.data.Resource
import com.raisrz.rais_project.core.ui.SportAdapter
import com.raisrz.rais_project.core.ui.ViewModelFactory
import com.raisrz.rais_project.databinding.ActivityMainBinding
import com.raisrz.rais_project.detail.DetailActivity
import com.raisrz.rais_project.favorite.FavoriteActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels {
        factory
    }
    private lateinit var binding: ActivityMainBinding

    private lateinit var menuItem: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sportAdapter = SportAdapter()
        sportAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            Toast.makeText(this, selectedData.strSport + "", Toast.LENGTH_SHORT).show()
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        mainViewModel.sports.observe(this) { sports ->
            Log.d(TAG, "onCreate: " + sports.data)
            if (sports != null) {
                when (sports) {
                    is Resource.Loading -> binding.pbMain.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.pbMain.visibility = View.GONE
                        sportAdapter.setData(sports.data)
                    }
                    is Resource.Error -> {
                        binding.pbMain.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                        binding.tvError.text =
                            sports.message ?: getString(R.string.str_something_wrong)
                    }
                }
            }
        }

        with(binding.rvMain) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = sportAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater

        inflater.inflate(R.menu.option_favorite, menu)
        menuItem = menu

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favorite -> {
                startActivity(Intent(this@MainActivity, FavoriteActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}