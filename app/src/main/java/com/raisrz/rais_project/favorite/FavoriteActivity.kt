package com.raisrz.rais_project.favorite

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.raisrz.rais_project.MyApplication
import com.raisrz.rais_project.R
import com.raisrz.rais_project.core.ui.SportAdapter
import com.raisrz.rais_project.core.ui.ViewModelFactory
import com.raisrz.rais_project.databinding.ActivityFavoriteBinding
import com.raisrz.rais_project.detail.DetailActivity
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.str_favorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sportAdapter = SportAdapter()

        favoriteViewModel.favSport.observe(this) { sports ->
            Log.d(ContentValues.TAG, "onCreate: $sports")
            if (sports != null) {
                sportAdapter.setData(sports)
            }
        }

        sportAdapter.onLongItemClick = { data ->
            val alertDialog = this.let {
                val builder = androidx.appcompat.app.AlertDialog.Builder(this@FavoriteActivity)
                builder.apply {
                    setTitle(context.getString(R.string.str_confirmation))
                    setMessage(context.getString(R.string.str_confirmation_desc))
                    setPositiveButton(
                        R.string.str_yes
                    ) { _, _ ->
                        favoriteViewModel.removeFavSport(data)
                    }
                    setNegativeButton(
                        R.string.str_no
                    ) { _, _ -> }
                }

                builder.create()
            }
            alertDialog.show()
        }

        sportAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            Toast.makeText(this, selectedData.strSport + "", Toast.LENGTH_SHORT).show()
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        with(binding.rvMain) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = sportAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}