package com.raisrz.rais_project.detail

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.raisrz.rais_project.R
import com.raisrz.rais_project.core.data.source.local.entity.SportEntity
import com.raisrz.rais_project.core.ui.ViewModelFactory
import com.raisrz.rais_project.databinding.ActivityDetailBinding
import com.raisrz.rais_project.favorite.FavoriteViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding

    private lateinit var sportId: String

    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val detailSport = intent.getParcelableExtra<SportEntity>(EXTRA_DATA)
        showDetail(detailSport)

        detailViewModel.getFavSport(sportId).observe(this) {
            Log.d(TAG, "onCreateDetail: " + it.toString())
            if(it.isNotEmpty()){
                isFavorite = true
                setFavoriteState()
            }
        }
    }

    private fun showDetail(detail: SportEntity?) {
        detail?.let {
            supportActionBar?.title = detail.strSport
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            Glide.with(this@DetailActivity)
                .load(detail.strSportThumb)
                .into(binding.ivImage)

            Glide.with(this@DetailActivity)
                .load(detail.strSportIconGreen)
                .into(binding.ivIcon)

            sportId = detail.idSport
            tvSportName.text = detail.strSport
            tvDesc.text = detail.strSportDescription
            tvFormatSport.text = detail.strFormat
            isFavorite = detail.isFavorite

            setFavoriteState()

            fabFavorite.setOnClickListener {
                if (!isFavorite) {
                    showToast(getString(R.string.str_added))
                } else {
                    showToast(getString(R.string.str_removed))
                }

                detailViewModel.setFavSport(detail, !isFavorite)
                isFavorite = !isFavorite

                setFavoriteState()
            }
        }
    }

    private fun setFavoriteState() {
        if (isFavorite)
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        else
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_outline
                )
            )
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}