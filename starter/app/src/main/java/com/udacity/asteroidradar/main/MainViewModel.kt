package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.Asteroid

class MainViewModel : ViewModel() {

    private val _getAsteroids = MutableLiveData<List<Asteroid>>()
    val getAsteroids: LiveData<List<Asteroid>> get() = _getAsteroids

    private val _navigateToAsteroidDetail = MutableLiveData<Asteroid?>()
    val navigateToAsteroidDetail get() = _navigateToAsteroidDetail

    //replace with DAO query
    fun addAsteroids() {
        val list = listOf<Asteroid>(
            Asteroid(1, "first", "101"),
            Asteroid(2, "second", "102"), Asteroid(3, "third", "103")
        )
        _getAsteroids.value = list
    }

    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToAsteroidDetail.value = asteroid
    }

    fun toAsteroidDetailNavigationDone() {
        _navigateToAsteroidDetail.value = null
    }
}