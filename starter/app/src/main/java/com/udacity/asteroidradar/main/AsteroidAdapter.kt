package com.udacity.asteroidradar.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.TextItemViewHolder

class AsteroidAdapter : RecyclerView.Adapter<TextItemViewHolder>() {

    var data = listOf<Asteroid>()
    @SuppressLint("NotifyDataSetChanged")
    set(value){
        field = value
        notifyDataSetChanged()
        //replace with DiffUtil
    }

    override fun getItemCount() = data.size

    /** RecyclerView calls this method to associate a ViewHolder with data.
    * The method fetches the appropriate data and uses the data to fill in the view holder's layout.
    * For example, if the RecyclerView displays a list of names, the method might find the
    * appropriate name in the list and fill in the view holder's TextView widget.**/
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.codename
    }

    /** RecyclerView calls this method whenever it needs to create a new ViewHolder.
    * The method creates and initializes the ViewHolder and its associated View, but does not fill
    * in the view's contents—the ViewHolder has not yet been bound to specific data **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.text_view_item, parent, false) as TextView

        return TextItemViewHolder(view)
    }
}