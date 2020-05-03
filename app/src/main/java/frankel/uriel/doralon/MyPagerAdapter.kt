package frankel.uriel.doralon

import Banner
import android.app.ActionBar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import frankel.uriel.doralon.model.DorAlonApplication
import kotlinx.android.synthetic.main.banner.view.*
import java.lang.Exception

class MyPagerAdapter : PagerAdapter() {

    var list: List<Banner>? = null

    override fun getCount(): Int {
        return list?.size ?: 0
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return o === view
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
       val view = LayoutInflater.from(container.context).inflate(R.layout.banner,container,false)

        container.addView(view)
        DorAlonApplication.picasso.load(DorAlonApplication.url + (list?.get(position)?.filepath ?: ""))
        .into(view.image)
        return view
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as View)
    }
}