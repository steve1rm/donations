package me.androidbox.tamboon.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.androidbox.tamboon.R
import me.androidbox.tamboon.data.entities.Charity
import me.androidbox.tamboon.presentation.viewholders.CharitiesViewHolder

class CharitiesAdapter : RecyclerView.Adapter<CharitiesViewHolder>() {

    private val charitiesList: MutableList<Charity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharitiesViewHolder {
        return CharitiesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.charity_item, parent, false))
    }

    override fun getItemCount(): Int = charitiesList.count()

    override fun onBindViewHolder(holder: CharitiesViewHolder, position: Int) {
        holder.id.text = charitiesList[position].id.toString()
        holder.name.text = charitiesList[position].name
    }

    fun populate(charities: List<Charity>) {
        this.charitiesList.addAll(charities)
    }
}
