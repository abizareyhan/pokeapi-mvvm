package com.abizareyhan.pokedex.pokemon.view

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.LinearLayoutManager
import com.abizareyhan.pokedex.core.R
import com.abizareyhan.pokedex.core.base.BaseActivity
import com.abizareyhan.pokedex.core.extension.parcelable
import com.abizareyhan.pokedex.core.extension.px
import com.abizareyhan.pokedex.core.extension.setItemDecoration
import com.abizareyhan.pokedex.core.itemDecoration.VerticalItemDecorator
import com.abizareyhan.pokedex.domain.base.ResourceState
import com.abizareyhan.pokedex.feature.pokemon.databinding.ActivityPokemonDetailBinding
import com.abizareyhan.pokedex.pokemon.adapter.PokemonGridAdapter
import com.abizareyhan.pokedex.pokemon.adapter.PokemonStatsAdapter
import com.abizareyhan.pokedex.pokemon.adapter.PokemonTypeAdapter
import com.abizareyhan.pokedex.pokemon.constant.BundleKey
import com.abizareyhan.pokedex.pokemon.enums.PokemonType
import com.abizareyhan.pokedex.pokemon.model.Pokemon
import com.abizareyhan.pokedex.pokemon.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class PokemonDetailActivity: BaseActivity<ActivityPokemonDetailBinding>(
    ActivityPokemonDetailBinding::inflate
) {
    private val detailViewModel: DetailViewModel by viewModels()

    private lateinit var pokemon: Pokemon
    private var baseColor by Delegates.notNull<Int>()
    private val typeAdapter by lazy {
        PokemonTypeAdapter()
    }

    private var downXValue: Float = 0F

    private val statsAdapter by lazy {
        PokemonStatsAdapter()
    }

    private val evolutionAdapter by lazy {
        PokemonGridAdapter(::onEvolutionClick)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        pokemon = intent?.extras?.parcelable(BundleKey.POKEMON) ?: Pokemon()

        setupTypeAdapter()
        setupStatsAdapter()
        setupEvolutionAdapter()
        observePokemon()
        setPokemonData(pokemon)

        detailViewModel.getPokemonDetail(pokemon.id)

        with(binding) {
            ivBack.setOnClickListener {
                finish()
            }

            ivPrev.setOnClickListener {
                pokemon.prevPokemonId?.let {
                    detailViewModel.getPokemonDetail(it)
                }
            }

            ivNext.setOnClickListener {
                pokemon.nextPokemonId?.let {
                    detailViewModel.getPokemonDetail(it)
                }
            }
            
            ivThumbnail.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        downXValue = event.x
                        true
                    }
                    MotionEvent.ACTION_UP -> {
                        val upXValue = event.x

                        val deltaX = downXValue - upXValue

                        if (deltaX > 0) {
                            ivNext.performClick()
                        } else {
                            ivPrev.performClick()
                        }

                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }
    }

    private fun setupTypeAdapter() {
        with(binding) {
            rvTags.adapter = typeAdapter
            rvTags.layoutManager = LinearLayoutManager(this@PokemonDetailActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupStatsAdapter() {
        with(binding) {
            rvStats.adapter = statsAdapter
            rvStats.layoutManager = LinearLayoutManager(this@PokemonDetailActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupEvolutionAdapter() {
        with(binding) {
            rvEvolution.adapter = evolutionAdapter
            rvEvolution.layoutManager = LinearLayoutManager(this@PokemonDetailActivity, LinearLayoutManager.VERTICAL, false)

            rvEvolution.setItemDecoration(
                VerticalItemDecorator(16.px)
            )
        }
    }

    private fun observePokemon() {
        detailViewModel.getPokemonDetailLiveData.observe(this@PokemonDetailActivity) {
            when(it.status) {
                ResourceState.SUCCESS -> {
                    binding.tvDescription.visibility = View.VISIBLE
                    pokemon = it.data
                    setPokemonData(it.data)
                }
                ResourceState.FAILED -> {
                    binding.tvDescription.visibility = View.GONE
                }
            }
        }
    }

    private fun setPokemonData(pokemon: Pokemon) {
        with(binding) {
            ivPrev.visibility = if(pokemon.prevPokemonId == null) {
                View.GONE
            } else {
                View.VISIBLE
            }

            ivNext.visibility = if(pokemon.nextPokemonId == null) {
                View.GONE
            } else {
                View.VISIBLE
            }

            baseColor = (pokemon.pokemonTypes.firstOrNull() ?: PokemonType.NORMAL).let {
                ContextCompat.getColor(root.context, it.colorResource)
            }

            statsAdapter.baseColor = baseColor.toColor()

            root.setBackgroundColor(baseColor)
            tvAbout.setTextColor(baseColor)
            tvBaseStats.setTextColor(baseColor)
            tvEvolution.setTextColor(baseColor)

            tvHeader.text = pokemon.name.capitalize()
            tvID.text = pokemon.hexID

            typeAdapter.updateItems(pokemon.pokemonTypes)

            tvWeightAmount.text = getString(
                R.string.weight_in_kg,
                pokemon.weightInKg
            )
            tvHeightAmount.text = getString(
                R.string.height_in_m,
                pokemon.heightInMeter
            )

            tvMoveAmount.text = pokemon.abilities.map {
                it.replace("-", " ").capitalize()
            }.joinToString("\n")

            tvDescription.text = pokemon.description

            statsAdapter.updateItems(pokemon.baseStats)
            evolutionAdapter.updateItems(pokemon.evolutions)

            Glide.with(root.context)
                .load(pokemon.thumbnailURL)
                .centerCrop()
                .into(ivThumbnail)
        }
    }

    private fun onEvolutionClick(pokemon: Pokemon) {
        binding.root.smoothScrollTo(0, 0)
        detailViewModel.getPokemonDetail(pokemon.id)
    }
}