package frankel.uriel.doralon.model

import android.app.Application
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class DorAlonApplication : Application() {
    companion object {
        lateinit var api: Api
        val url = "https://doralon.comrax.com/"
        lateinit var picasso : Picasso
    }


    override fun onCreate() {
        super.onCreate()

        val client = OkHttpClient.Builder()
            .connectionSpecs(Arrays.asList(ConnectionSpec.COMPATIBLE_TLS))
            .build()

        picasso = Picasso.Builder(this)
            .downloader(OkHttp3Downloader(client))
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl( "https://doralon.comrax.com/mobile/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }
}