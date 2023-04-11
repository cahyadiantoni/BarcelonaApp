package com.barcelona.cahya_submission_barcelona.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barcelona.cahya_submission_barcelona.DetailActivity
import com.barcelona.cahya_submission_barcelona.R
import com.barcelona.cahya_submission_barcelona.model.Player
import com.bumptech.glide.Glide

class ListPlayerAdapter(private val listHero: ArrayList<Player>) : RecyclerView.Adapter<ListPlayerAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_player, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo, appearances, goals, assists, overview, posisi ) = listHero[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)
        holder.tvName.text = name
        holder.tvDescription.text = description

        val mContext = holder.itemView.context

        holder.itemView.setOnClickListener {
            val moveDetail = Intent(mContext, DetailActivity::class.java)
            moveDetail.putExtra(DetailActivity.EXTRA_APPEARANCES, appearances)
            moveDetail.putExtra(DetailActivity.EXTRA_NAME, name)
            moveDetail.putExtra(DetailActivity.EXTRA_PHOTO, photo)
            moveDetail.putExtra(DetailActivity.EXTRA_GOALS, goals)
            moveDetail.putExtra(DetailActivity.EXTRA_ASSISTS, assists)
            moveDetail.putExtra(DetailActivity.EXTRA_POSISI, posisi)
            moveDetail.putExtra(DetailActivity.EXTRA_DESCRIPTION, description)
            moveDetail.putExtra(DetailActivity.EXTRA_OVERVIEW, overview)
            mContext.startActivity(moveDetail)
        }
    }

    override fun getItemCount(): Int = listHero.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }
}