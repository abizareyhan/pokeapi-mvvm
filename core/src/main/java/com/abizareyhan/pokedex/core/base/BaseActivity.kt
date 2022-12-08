package com.abizareyhan.pokedex.core.base

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>(
    val bindingFactory: (LayoutInflater) -> VB,
): AppCompatActivity() {
    protected val binding: VB by lazy { bindingFactory(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = binding.root
        setContentView(view)
        setLayoutTransitionTypeToChanging()

        init()
    }

    abstract fun init()

    private fun setLayoutTransitionTypeToChanging() {
        with(binding) {
            (root as? ViewGroup)?.layoutTransition?.enableTransitionType(
                LayoutTransition.CHANGING
            )
        }
    }
}