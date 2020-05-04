package frankel.uriel.doralon

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        agreement.setOnClickListener {
            startActivity(Intent(this@MainActivity, AgreementActivity::class.java))
        }

        homepage.setOnClickListener {
            val url = "https://www.doralon.co.il/"
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
        }

        facebook.setOnClickListener {
            val url = "https://www.facebook.com/dor.alon.il/"
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
        }


    }
}
