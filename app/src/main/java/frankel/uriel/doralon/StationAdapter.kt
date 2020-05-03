package frankel.uriel.doralon

import Station
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class StationAdapter : RecyclerView.Adapter<StationAdapter.StationViewHolder>() {

    var list: List<Station>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StationViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.station_list_item, parent, false)
        return StationViewHolder(view)


    }

    override fun getItemCount() = list?.size ?: 0

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.name?.text = list?.get(position)?.node_title
    }

    class StationViewHolder : ViewHolder {


        var name: TextView? = null

        constructor(view: View) : super(view) {
            name = view.findViewById(R.id.name)
        }
    }
}