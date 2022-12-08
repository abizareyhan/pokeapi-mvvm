package com.abizareyhan.pokedex.pokemon.view

import android.content.Intent
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.abizareyhan.pokedex.core.base.BaseActivity
import com.abizareyhan.pokedex.core.extension.px
import com.abizareyhan.pokedex.core.extension.setItemDecoration
import com.abizareyhan.pokedex.core.itemDecoration.GridLayoutItemDecoration
import com.abizareyhan.pokedex.feature.pokemon.databinding.ActivityHomeBinding
import com.abizareyhan.pokedex.pokemon.adapter.PokemonGridPagingAdapter
import com.abizareyhan.pokedex.pokemon.constant.BundleKey
import com.abizareyhan.pokedex.pokemon.model.Pokemon
import com.abizareyhan.pokedex.pokemon.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity: BaseActivity<ActivityHomeBinding>(
    ActivityHomeBinding::inflate
) {
    private val homeViewModel: HomeViewModel by viewModels()
    private var fetchPokemonJob: Job? = null
    private var stopTypingJob: Job? = null

    private var currentSortAsc: Boolean = true

    private val pagingAdapter: PokemonGridPagingAdapter by lazy {
        PokemonGridPagingAdapter(::onPokemonClick)
    }

    override fun init() {
        setupRecyclerView()
        setupSearch()
        fetchPokemonPaging("", currentSortAsc)

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.inputSearch.text = null
            fetchPokemonPaging("", currentSortAsc)
        }

        binding.ivSort.setOnClickListener {
            currentSortAsc = !currentSortAsc

            fetchPokemonPaging(binding.inputSearch.text.toString(), currentSortAsc)
        }
    }

    private fun setupRecyclerView() {
        with(binding) {
            rvPokemon.adapter = pagingAdapter
            rvPokemon.layoutManager = GridLayoutManager(this@HomeActivity, 3)

            rvPokemon.setItemDecoration(
                GridLayoutItemDecoration(3, 12.px, false, 16.px)
            )

            pagingAdapter.addLoadStateListener {
                swipeRefreshLayout.isRefreshing = it.refresh is LoadState.Loading
            }
        }
    }

    private fun setupSearch() {
        with(binding) {
            inputSearch.doAfterTextChanged {
                if(inputSearch.hasFocus()) {
                    stopTypingJob?.cancel()
                    stopTypingJob = lifecycleScope.launch(Dispatchers.IO) {
                        delay(1500)
                        launch(Dispatchers.Main) {
                            fetchPokemonPaging(it.toString(), currentSortAsc)
                        }
                    }
                    stopTypingJob?.start()
                }
            }
        }
    }

    private fun fetchPokemonPaging(search: String = "", sortAsc: Boolean) {
        fetchPokemonJob?.cancel()
        fetchPokemonJob = lifecycleScope.launch {
            homeViewModel.getPokemonPaging(search, sortAsc).collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }

    private fun onPokemonClick(pokemon: Pokemon) {
        val intent = Intent(this@HomeActivity, PokemonDetailActivity::class.java)
        intent.putExtra(BundleKey.POKEMON, pokemon)
        startActivity(intent)
    }
}