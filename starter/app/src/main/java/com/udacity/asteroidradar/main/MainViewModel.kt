package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.Asteroid

class MainViewModel : ViewModel() {

    private val _getAsteroids = MutableLiveData<List<Asteroid>>()
    val getAsteroids : LiveData<List<Asteroid>> get() = _getAsteroids

    //replace with DAO query
    fun addAsteroids() {
        val list = listOf<Asteroid>(Asteroid(1, "first"), Asteroid(2, "second"), Asteroid(3, "third"))
        _getAsteroids.value = list
    }
}