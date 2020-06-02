package me.androidbox.tamboon.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.androidbox.tamboon.R
import me.androidbox.tamboon.data.entities.Charity
import me.androidbox.tamboon.presentation.viewholders.CharitiesViewHolder

class CharitiesAdapter : RecyclerView.Adapter<CharitiesViewHolder>() {

    private val charitiesList: MutableList<Charity> = mutableListOf()
    private var selectedCharity: (Charity) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharitiesViewHolder {
        val charitiesViewHolder = CharitiesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.charity_item, parent, false))

        charitiesViewHolder.itemView.setOnClickListener {
            selectedCharity(charitiesList[charitiesViewHolder.adapterPosition])
        }

        return charitiesViewHolder
    }

    override fun getItemCount(): Int = charitiesList.count()

    override fun onBindViewHolder(holder: CharitiesViewHolder, position: Int) {
        holder.id.text = charitiesList[position].id.toString()
        holder.name.text = charitiesList[position].name

        Glide.with(holder.itemView.context)
            .load(charitiesList[position].logoUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.logo)
    }

    fun populate(charities: List<Charity>) {
        this.charitiesList.addAll(charities)
    }

    fun setSelectedCharity(selectedCharity: (Charity) -> Unit) {
        this.selectedCharity = selectedCharity
    }
}
