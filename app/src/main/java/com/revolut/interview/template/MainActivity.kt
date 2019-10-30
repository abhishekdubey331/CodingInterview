package com.revolut.interview.template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

/**
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 *
 * Objective:
 * 1. Implement a currency converter (Check the video "demo.webm")
 * 2. Tapping on a cell must move this cell to the top of the list
 * 3. Changing the amount of the top cell must simultaneously update the value of the other cells
 * 4. The rates must be updated every second
 *
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 *
 * To get the rates you can use [DataSource.getRates]
 *
 * To display the values you can use [Adapter.models] setter
 *
 * To handle the actions you can use these callbacks:
 * [onTopCellTextChanged] handles changes of the top cell value
 * [onItemClicked] handles clicks on the cells
 *
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 *
 * Feel free to use any libraries or frameworks you find useful or familiar with,
 * and create your own classes and files.
 *
 * It's not supposed that you will be making changes to any class apart from [MainActivity]
 * but if you see that it's needed for your implementation feel free to do it.
 *
 * ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 */
class MainActivity : AppCompatActivity() {
    private val dataSource: DataSource = DataSourceImpl()
    private val adapter: Adapter<AdapterImpl.ViewHolder> = AdapterImpl(this::onTopCellTextChanged, this::onItemClicked)
    private val decimalFormat = DecimalFormat("#0.0000")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

//        /**
//         * Example usage (displays static list)
//         */
//        val sampleModels = mutableListOf(
//            Adapter.Model("USD", decimalFormat.format(1.0))
//        )
//        sampleModels.addAll(
//            dataSource.getRates("USD").map { rate ->
//                Adapter.Model(rate.currency, decimalFormat.format(rate.value))
//            }
//        )
//
//        adapter.models = sampleModels

    }

    private fun onTopCellTextChanged(text: String) {

    }

    private fun onItemClicked(index: Int) {

    }
}
