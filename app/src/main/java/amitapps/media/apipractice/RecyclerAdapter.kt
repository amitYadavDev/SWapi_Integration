package amitapps.media.apipractice

import amitapps.media.apipractice.data.remote.model.Result
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val context: Activity, val mResult: List<Result>?) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(context)
            .inflate(R.layout.characters_layout, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mResult?.get(position)

        // sets the image to the imageview from our itemHolder class
        holder.name.text = ItemsViewModel?.name ?: "dkdflk"
        if(ItemsViewModel != null)
        Log.d("responseBodyRV", ItemsViewModel.name)
        else
            Log.d("responseBodyRV", "  ooooye null he")
        // sets the text to the textview from our itemHolder class
        holder.gender.text = ItemsViewModel?.gender ?: "male"
        holder.height.text = ItemsViewModel?.height ?: "165"

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mResult?.size!!
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val gender: TextView = itemView.findViewById(R.id.gender)
        val height: TextView = itemView.findViewById(R.id.height)
    }
}
