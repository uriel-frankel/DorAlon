package com.comrax.doralonapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.station_layout.*
import retrofit2.Call
import retrofit2.Response

class StationActivity : AppCompatActivity() {

    private var lat: String? = null
    private var lon: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.station_layout)

        ViewCompat.setLayoutDirection(container, ViewCompat.LAYOUT_DIRECTION_RTL);
        val id = intent.getStringExtra("id")
        id?.let {
            DorAlonApplication.api.getStation(id)?.enqueue(object :
                retrofit2.Callback<JsonObject> {
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    container.visibility = View.VISIBLE
                    address.text = getFiledByName(response, "field_address")
                    phone.text = getFiledByName(response, "field_phone")
                    operationTime.text = getFiledByName(response, "field_time")
                    header.text = response.body()?.get("title")?.asString + if (BuildConfig.DEBUG) {
                        " " + response.body()?.get("nid")?.asString
                    } else {
                        ""
                    }

                    showOrHideField(response = response, name = "field_wifi", view = wifi)
                    showOrHideField(response = response, name = "field_matas", view = matas)
                    showOrHideField(response = response, name = "field_delek98", view = delek98)
                    showOrHideField(
                        response = response,
                        name = "field_superalonit",
                        view = superalonit
                    )
                    showOrHideField(response = response, name = "field_alonit", view = alonit)
                    showOrHideField(response = response, name = "field_shabbat", view = shabbat)
                    showOrHideField(response = response, name = "field_atm", view = atm)
                    showOrHideField(response = response, name = "field_gas", view = gas)
                    showOrHideField(
                        response = response,
                        name = "field_better_place",
                        view = better_place
                    )
                    showOrHideField(response = response, name = "field_wash", view = wash)
                    showOrHideField(
                        response = response,
                        name = "field_selfservice",
                        view = selfservice
                    )
                    showOrHideField(response = response, name = "field_24hrs", view = open247)

                    getLatLon(response)
                    navigate.setOnClickListener {
                        try {
                            val url =
                                "https://waze.com/ul?ll=$lat,$lon&navigate=yes"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            this@StationActivity.startActivity(intent)
                        } catch (ex: ActivityNotFoundException) {
                            val intent =
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("market://details?id=com.waze")
                                )
                            this@StationActivity.startActivity(intent)
                        }
                    }
                }


            })
        }
    }

    private fun showOrHideField(
        response: Response<JsonObject>,
        name: String,
        view: View,
        goneValue: String? = "0"
    ) {
        if (getFiledByName(response, name) == goneValue || getFiledByName(response, name) == null) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }

    private fun getFiledByName(response: Response<JsonObject>, name: String): String? {
        var s: String? = null
        try {
            s = (response.body()?.getAsJsonArray(name)?.get(0) as JsonObject).get("value").asString
        } catch (t: Throwable) {
            t.printStackTrace()
        }
        return s
    }

    private fun getLatLon(response: Response<JsonObject>) {
        try {
            val jsonObject = response.body()?.getAsJsonArray("field_location")?.get(0) as JsonObject
            lat = jsonObject.get("latitude").asString
            lon = jsonObject.get("longitude").asString
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

}
