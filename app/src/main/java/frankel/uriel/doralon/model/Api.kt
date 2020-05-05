package frankel.uriel.doralon.model

import Banner
import Station
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface Api {
    @GET("getbanners/json")
    fun getBanners(): Call<List<Banner>>?


    @GET("getstations")
    fun getStations(): Call<HashMap<String, Station>>?

//http://doralon.comrax.com/mobile/station/2363/all
    @GET("station/{id}/all")
    fun getStation(@Path("id") id: String): Call<JsonObject>?
}