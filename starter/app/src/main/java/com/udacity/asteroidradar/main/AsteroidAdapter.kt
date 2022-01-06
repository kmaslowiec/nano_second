package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.ListItemAsteroidsBinding

class AsteroidAdapter(val clickListener: AsteroidListener) : ListAdapter<Asteroid, AsteroidAdapter.AsteroidsViewHolder>(AndroidsDiffCallback()) {

    /** We de not need to init the data and use notifyDataSetChanged if we use DiffUtil
     * Highly Recommended !!!
     */
    /*var data = listOf<Asteroid>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
            //replace with DiffUtil
        }*/

    /** No need for this when we use DiffUtil **/
    //override fun getItemCount() = data.size

    /** RecyclerView calls this method to associate a ViewHolder with data.
     * The method fetches the appropriate data and uses the data to fill in the view holder's layout.
     * For example, if the RecyclerView displays a list of asteroids, the method might find the
     * appropriate object in the list and fill in the view holder's View widget.**/
    override fun onBindViewHolder(holder: AsteroidsViewHolder, position: Int) {
        val item = getItem(position)
        /** Needed to get resources e.g. Strings, Colors etc.**/
        //val res = holder.itemView.context.resources

        holder.bin(clickListener, item)

        holder.codename.text = item.codename
        holder.closeApproachDate.text = item.closeApproachDate
        holder.emojiPotentiallyHazardous
            .setImageResource(
                if (item.isPotentiallyHazardous) R.drawable.ic_status_potentially_hazardous
                else R.drawable.ic_status_normal
            )

    }

    /** RecyclerView calls this method whenever it needs to create a new ViewHolder.
     * The method creates and initializes the ViewHolder and its associated View, but does not fill
     * in the view's contents—the ViewHolder has not yet been bound to specific data **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidsViewHolder {
        return AsteroidsViewHolder.from(parent)
    }

    /** binds the views in the holder with xml file **/
    class AsteroidsViewHolder private constructor(val binding: ListItemAsteroidsBinding) : RecyclerView.ViewHolder(binding.root) {
        val codename: TextView = binding.listCodeName
        val closeApproachDate: TextView = binding.listCloseApproachDate
        val emojiPotentiallyHazardous: ImageView = binding.listEmojiPotentialHazard

        fun bin(clickListener: AsteroidListener, item: Asteroid) {
            binding.asteroid = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): AsteroidsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemAsteroidsBinding
                    .inflate(layoutInflater, parent, false)

                return AsteroidsViewHolder(binding)
            }
        }
    }

    /**
     * Callback for calculating the diff between two non-null items in a list.
     *
     * Used by ListAdapter to calculate the minumum number of changes between and old list and a new
     * list that's been passed to `submitList`.
     */
    class AndroidsDiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem == newItem
        }
    }

    /**Click Listener **/
    class AsteroidListener(val clickListener: (asteroid: Asteroid) -> Unit) {
        fun onClick(asteroid: Asteroid) = clickListener(asteroid)
    }
}