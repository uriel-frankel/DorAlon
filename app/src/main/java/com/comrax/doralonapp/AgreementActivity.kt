package com.comrax.doralonapp

import android.content.res.Resources
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.agreement_layout.*
import java.io.InputStream


class AgreementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.agreement_layout)
        try {
            val res: Resources = resources
            val in_s: InputStream = res.openRawResource(R.raw.agreement)
            val b = ByteArray(in_s.available())
            in_s.read(b)
            agreement.text = Html.fromHtml(String(b))
        } catch (e: Exception) {
            agreement.text = "Error: can't show help."
        }
    }
}