package frankel.uriel.doralon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        stationList.setOnClickListener {
            startActivity(Intent(this@MainActivity, StationListActivity::class.java))
        }
        sales.setOnClickListener {
            startActivity(Intent(this@MainActivity, SalesListActivity::class.java))
        }


    }
}
