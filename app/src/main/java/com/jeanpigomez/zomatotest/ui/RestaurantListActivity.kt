package com.jeanpigomez.zomatotest.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.jeanpigomez.zomatotest.R
import com.jeanpigomez.zomatotest.databinding.ActivityRestaurantsBinding
import com.jeanpigomez.zomatotest.model.Location

class RestaurantListActivity : AppCompatActivity() {

    companion object {

        private const val INTENT_ENTITY_ID = "entity_id"
        private const val INTENT_ENTITY_TYPE = "entity_type"
        private const val INTENT_CITY = "city"

        fun newIntent(context: Context, location: Location): Intent {
            val intent = Intent(context, RestaurantListActivity::class.java)
            intent.putExtra(INTENT_ENTITY_ID, location.entityId)
            intent.putExtra(INTENT_ENTITY_TYPE, location.entityType)
            intent.putExtra(INTENT_CITY, location.cityName)
            return intent
        }
    }

    private lateinit var viewModel: RestaurantListViewModel
    private lateinit var binding: ActivityRestaurantsBinding
    private var entityId: String? = null
    private var entityType: String? = null
    private var cityName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurants)

        entityId = intent?.extras?.getString(INTENT_ENTITY_ID)
        entityType = intent?.extras?.getString(INTENT_ENTITY_TYPE)
        cityName = intent?.extras?.getString(INTENT_CITY)

        viewModel = ViewModelProviders.of(this).get(RestaurantListViewModel::class.java)
        binding.viewModel = viewModel

        initViews()
        initObservers()

        viewModel.getLocationDetails(entityId!!.toLong(), entityType!!)
    }

    private fun initViews() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.title = "Restaurants in: $cityName"
            it.setDisplayHomeAsUpEnabled(true)
        }
        binding.rvRestaurants.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun initObservers() {
        viewModel.getErrorMessage().observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage)
        })
    }

    private fun showError(@StringRes errorMessage: Int) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
