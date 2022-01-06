package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {


    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel
        val adapter = AsteroidAdapter(AsteroidAdapter.AsteroidListener { asteroid ->
            viewModel.onAsteroidClicked(asteroid)
        })
        binding.asteroidRecycler.adapter = adapter
        viewModel.addAsteroids()

        viewModel.getAsteroids.observe(viewLifecycleOwner, {
            //adapter.data = it
            /** With DiffUtil **/
            adapter.submitList(it)
        })

        viewModel.navigateToAsteroidDetail.observe(viewLifecycleOwner, { asteroid ->
            asteroid?.let {
                this.findNavController().navigate(MainFragmentDirections.actionShowDetail(asteroid))
                viewModel.toAsteroidDetailNavigationDone()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}