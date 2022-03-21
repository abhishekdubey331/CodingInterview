package com.revolut.interview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RatesAdapter() : RecyclerView.Adapter<RatesAdapter.ViewHolder>() {

    private var ratesList: List<Rate> = emptyList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val currency: TextView = itemView.findViewById(R.id.currency)
        val value: TextView = itemView.findViewById(R.id.value)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_rates, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.currency.text = ratesList[position].currency
        viewHolder.value.text = ratesList[position].value.toString()
    }

    override fun getItemCount() = ratesList.size

    fun updateRatesList(ratesList: List<Rate>) {
        this.ratesList = ratesList
        notifyDataSetChanged()
    }

}