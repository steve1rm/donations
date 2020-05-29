package me.androidbox.tamboon.presentation.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.androidbox.tamboon.R

class CharitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val id: TextView = itemView.findViewById(R.id.tvId)
    val name: TextView = itemView.findViewById(R.id.tvName)
    val logo: ImageView = itemView.findViewById(R.id.ivLogo)
}
