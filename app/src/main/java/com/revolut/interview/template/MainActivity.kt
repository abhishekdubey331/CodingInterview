package com.revolut.interview.template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

/***
 * You have:
 * 1. DataSource.getRates which returns rates for a selected currency
 * 2. Adapter which can display a currency and an amount
 * 3. onBaseValueTextChanged callback with base amount
 * 4. onItemClicked with index of clicked item. Base currency isn't clickable
 *
 * You can use any library
 *
 * Objective: add the ability to change the base currency and its amount
 */
class MainActivity : AppCompatActivity() {
    private val dataSource = DataSource()
    private val adapter = Adapter(this::onBaseValueTextChanged, this::onItemClicked)
    private val decimalFormat = DecimalFormat("#0.0000")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        //test data, should be removed
        val sampleModels = mutableListOf(
            Adapter.Model("USD", decimalFormat.format(1.0))
        )
        sampleModels.addAll(
            dataSource.getRates("USD").map { rate ->
                Adapter.Model(rate.currency, decimalFormat.format(rate.value))
            }
        )

        adapter.models = sampleModels
    }

    private fun onBaseValueTextChanged(text: String) {

    }

    private fun onItemClicked(index: Int) {

    }
}
