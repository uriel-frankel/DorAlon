package frankel.uriel.doralon

import Station
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
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
        list?.get(position)?.let {
            holder.station = it
            holder.name?.text = it.node_title
            if (it.node_data_field_alonit_field_24hrs_value == 1) {
                holder.open247?.visibility = View.VISIBLE
            } else {
                holder.open247?.visibility = View.GONE
            }
        }

    }

    class StationViewHolder : ViewHolder {

        var open247: View? = null
        var navigateWithWaze: Button? = null
        var name: TextView? = null
        var station: Station? = null

        constructor(view: View) : super(view) {
            name = view.findViewById(R.id.name)
            open247 = view.findViewById(R.id.open247)
            navigateWithWaze = view.findViewById(R.id.navigate)
            navigateWithWaze?.setOnClickListener {
                try {
                    val url =
                        "https://waze.com/ul?ll=" + station?.location_latitude + "," + station?.location_longitude + "&navigate=yes"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    view.context.startActivity(intent)
                } catch (ex: ActivityNotFoundException) {
                    val intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.waze"))
                    view.context.startActivity(intent)
                }
            }

        }
    }
}