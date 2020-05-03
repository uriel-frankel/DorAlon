package frankel.uriel.doralon

import Banner
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import frankel.uriel.doralon.model.DorAlonApplication
import kotlinx.android.synthetic.main.sales_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.toList as toList1

class SalesListActivity : AppCompatActivity() {

    val adapter = MyPagerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sales_layout)
        pager.adapter = adapter
        DorAlonApplication.api.getBanners()?.enqueue(object : Callback<List<Banner>>{
            override fun onFailure(call: Call<List<Banner>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Banner>>, response: Response<List<Banner>>) {
                adapter.list = response.body()
                adapter.notifyDataSetChanged()
            }

        })




    }
}
