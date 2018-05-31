package me.eijaz.cryptotracker.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cryptocurrency_list_item.view.*
import me.eijaz.cryptotracker.R
import me.eijaz.cryptotracker.models.Cryptocurrency
import me.eijaz.cryptotracker.utils.Constants

class CryptocurrencyAdapter(val cryptocurrencies: List<Cryptocurrency>): RecyclerView.Adapter<CryptocurrencyViewHolder>() {

    /**
     * Adapter takes in list of model objects
     */



    override fun getItemCount(): Int {

        /**
         *  lets the Adapter know how many items to display
         */
        return cryptocurrencies.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CryptocurrencyViewHolder {

        val view = LayoutInflater.from(parent?.context).inflate(R.layout.cryptocurrency_list_item, parent,  false)
        return CryptocurrencyViewHolder(view)

    }


    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CryptocurrencyViewHolder?, position: Int) {


        /**
         *  coin - A single CryptoModel object from list
         */

        val crypto = cryptocurrencies.get(position)

        /**
         * get CryptoModel data and bind them to corresponding
         * viewholder widgets(text view, imageview etc)
         */

        holder?.coinName?.text = crypto.name
        holder?.coinSymbol?.text = crypto.symbol
        holder?.coinPrice?.text = "$" + crypto.price_usd
        holder?.twentyFourHourChange?.text = "24h: " + crypto.percent_change_24h+"%"
        holder?.sevenDayChange?.text = "7d: " + crypto.percent_change_7d+"%"

        /**
         *  Picasso for async image loading
         */

        Picasso.with(holder?.itemView?.context).load(Constants.LOGOURL.LOGOURL + crypto.symbol.toLowerCase()+".png").into(holder?.coinIcon)


        /**
         *  Set color of percentage change textview to reflect
         *  if the percentage change was negative or positive
         */

        if(crypto.percent_change_24h.contains("-")){
            holder?.twentyFourHourChange?.setTextColor(Color.parseColor("#ff0000"))
        }
        else{
            holder?.twentyFourHourChange?.setTextColor(Color.parseColor("#32CD32"))
        }

        if(crypto.percent_change_7d.contains("-")){
            holder?.sevenDayChange?.setTextColor(Color.parseColor("#ff0000"))
        }
        else{
            holder?.sevenDayChange?.setTextColor(Color.parseColor("#32CD32"))
        }
    }

}


class CryptocurrencyViewHolder(view: View): RecyclerView.ViewHolder(view) {

    /**
     * ViewHolder items (textviews, imageviews) from the crypto_layout.xml
     */
    val coinIcon = view.iv_crypto_logo
    val coinSymbol = view.tv_crypto_symbol
    val coinName = view.tv_crypto_name
    val twentyFourHourChange = view.tv_crypto_24hr
    val sevenDayChange = view.tv_crypto_7d
    val coinPrice = view.tv_crypto_price


}
