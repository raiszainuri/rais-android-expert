package com.raisrz.rais_project.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.raisrz.rais_project.R
import com.raisrz.rais_project.core.domain.model.Sport
import com.raisrz.rais_project.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val detailViewModel: DetailViewModel by viewModels ()

    private lateinit var binding: ActivityDetailBinding
    private lateinit var sportId: String

    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailSport = intent.getParcelableExtra<Sport>(EXTRA_DATA)
        showDetail(detailSport)

        detailViewModel.getFavSport(sportId).observe(this) {
            if(it.isNotEmpty()){
                isFavorite = true
                setFavoriteState()
            }
        }
    }

    private fun showDetail(detail: Sport?) {
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
            binding.tvSportName.text = detail.strSport
            binding.tvDesc.text = detail.strSportDescription
            binding.tvFormatSport.text = detail.strFormat
            isFavorite = detail.isFavorite

            setFavoriteState()

            binding.fabFavorite.setOnClickListener {
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