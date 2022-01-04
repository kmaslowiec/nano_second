package com.udacity.asteroidradar.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R

class AsteroidAdapter : RecyclerView.Adapter<AsteroidAdapter.AsteroidsViewHolder>() {

    var data = listOf<Asteroid>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
            //replace with DiffUtil
        }

    override fun getItemCount() = data.size

    /** RecyclerView calls this method to associate a ViewHolder with data.
     * The method fetches the appropriate data and uses the data to fill in the view holder's layout.
     * For example, if the RecyclerView displays a list of asteroids, the method might find the
     * appropriate object in the list and fill in the view holder's View widget.**/
    override fun onBindViewHolder(holder: AsteroidsViewHolder, position: Int) {
        val item = data[position]
        /** Needed to get resources e.g. Strings, Colors etc.**/
        //val res = holder.itemView.context.resources

        holder.codename.text = item.codename
        holder.closeApproachDate.text = item.closeApproachDate
    }

    /** RecyclerView calls this method whenever it needs to create a new ViewHolder.
     * The method creates and initializes the ViewHolder and its associated View, but does not fill
     * in the view's contents—the ViewHolder has not yet been bound to specific data **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item_asteroids, parent, false)

        return AsteroidsViewHolder(view)
    }

    class AsteroidsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val codename: TextView = itemView.findViewById(R.id.list_code_name)
        val closeApproachDate: TextView = itemView.findViewById(R.id.list_close_approach_date)
        val isPotentiallyHazardous: ImageView = itemView.findViewById(R.id.list_emoji_potential_hazard)
    }
}