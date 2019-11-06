package me.eijaz.cryptotracker.UI

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import me.eijaz.cryptotracker.R
import java.io.IOException
import okhttp3.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import me.eijaz.cryptotracker.adapters.CryptocurrencyAdapter
import me.eijaz.cryptotracker.models.Cryptocurrency
import me.eijaz.cryptotracker.utils.Constants

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_cryptocurrencies.layoutManager = LinearLayoutManager(this)
        getCoins()
    }

    fun getCoins(){

        val client = OkHttpClient()
        val request = Request.Builder().url(Constants.APIURL.APIURL).build()

        client.newCall(request).enqueue(object: Callback {

            override fun onFailure(call: Call?, e: IOException?) {
                println(e.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println("Body :"+body)
                val gson = Gson()
                val cryptocurrencies:List<Cryptocurrency> = gson.fromJson(body, object : TypeToken<List<Cryptocurrency>>() {}.type)

                runOnUiThread {
                    rv_cryptocurrencies.adapter = CryptocurrencyAdapter(cryptocurrencies)
                }
            }
        })
    }
}
