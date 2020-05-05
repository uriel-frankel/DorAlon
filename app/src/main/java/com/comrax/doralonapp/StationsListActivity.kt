package com.comrax.doralonapp

import Station
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.stations_list_layout.*
import retrofit2.Call
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap

class StationsListActivity : AppCompatActivity() {

    val adapter = StationAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stations_list_layout)
        ViewCompat.setLayoutDirection(recyclerView,ViewCompat.LAYOUT_DIRECTION_RTL);


        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = adapter

        DorAlonApplication.api.getStations()?.enqueue(object :
            retrofit2.Callback<HashMap<String, Station>> {
            override fun onFailure(call: Call<HashMap<String, Station>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<HashMap<String, Station>>,
                response: Response<HashMap<String, Station>>
            ) {
                adapter.list = response.body()?.values?.toList().apply { Collections.sort(this
                ) { o1, o2 -> o1.node_title.compareTo(o2.node_title) }
                }
                adapter.notifyDataSetChanged()
            }

        })


    }
}
