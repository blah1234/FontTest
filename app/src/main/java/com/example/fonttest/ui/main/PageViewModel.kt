package com.example.fonttest.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fonttest.R

class PageViewModel : ViewModel() {

    private lateinit var ctx: Context
    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> by lazy {
        Transformations.map(_index) {
            "${ctx.resources.getString(R.string.logout)}"
        }
    }

    fun setContext(ctx: Context) {
        this.ctx = ctx
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}