package me.androidbox.tamboon.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.androidbox.tamboon.R
import me.androidbox.tamboon.data.entities.Charities
import me.androidbox.tamboon.presentation.viewholders.CharitiesViewHolder

class CharitiesAdapter : RecyclerView.Adapter<CharitiesViewHolder>() {

    private lateinit var charitiesList: Charities

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharitiesViewHolder {
        return CharitiesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.charity_item, parent, false))
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CharitiesViewHolder, position: Int) {
        holder.id.text = charitiesList.charityList[position].id.toString()
        holder.name.text = charitiesList.charityList[position].name
    }
}