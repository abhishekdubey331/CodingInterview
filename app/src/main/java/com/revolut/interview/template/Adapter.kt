package com.revolut.interview.template

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

class Adapter(
        private val onTextChanged: (String) -> Unit,
        private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    var models: List<Model> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val watcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable) = Unit

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = onTextChanged(s.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_adapter, parent, false))

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = models[position]

        holder.currency.text = item.currency
        holder.amount.setText(item.amount)

        holder.amount.removeTextChangedListener(watcher)
        holder.amount.isEnabled = position == 0

        if (position == 0) {
            holder.amount.addTextChangedListener(watcher)
            holder.itemView.setOnClickListener(null)
            holder.amount.setOnClickListener(null)
        } else {
            holder.itemView.setOnClickListener { onItemClicked(position) }
            holder.amount.setOnClickListener { onItemClicked(position) }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currency = itemView.findViewById<TextView>(R.id.currency)
        val amount = itemView.findViewById<EditText>(R.id.amount)
    }

    data class Model(val currency: String, val amount: String)
}