package com.jeanpigomez.zomatotest.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.jeanpigomez.zomatotest.R
import com.jeanpigomez.zomatotest.databinding.ActivityChooseCityBinding

class ChooseCityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseCityBinding
    private lateinit var viewModel: ChooseCityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_city)
        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProviders.of(this).get(ChooseCityViewModel::class.java)
        binding.viewModel = viewModel

        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.btnSearchLocation.setOnClickListener {
            if (binding.etCityName.text.toString() != "") {
                viewModel.getLocation(binding.etCityName.text.toString())
            } else {
                showError(R.string.please_enter_city_name)
            }
        }
    }

    private fun initObservers() {
        viewModel.getErrorMessage().observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage)
        })

        viewModel.getLocationData().observe(this, Observer { location ->
            location?.let {
                startActivity(RestaurantListActivity.newIntent(this, it))
            }
        })
    }

    private fun showError(@StringRes errorMessage: Int) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}
