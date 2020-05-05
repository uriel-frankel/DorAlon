package com.comrax.doralonapp

import Banner
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.banner.view.*

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
        DorAlonApplication.picasso.load(
            DorAlonApplication.url + (list?.get(position)?.filepath ?: ""))
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