package ru.studyguk.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import ru.studyguk.cryptoapp.R
import ru.studyguk.cryptoapp.databinding.ItemCoinInfoBinding
import ru.studyguk.cryptoapp.domain.CoinInfo

class CoinInfoAdapter(private val context: Context) :
    ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffCallback) {

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        val symbolsTemplate = context.resources.getString(R.string.symb_template)
        val updateTemplate = context.resources.getString(R.string.update_template)
        with(holder.binding) {
            tvSymbols.text = String.format(symbolsTemplate, coin.fromSymbol, coin.toSymbol)
            tvValue.text = coin.price
            tvUpdateTime.text =
                String.format(updateTemplate, coin.lastUpdate)
            Picasso.get().load(coin.imageUrl).into(ivLogoCoin)
            root.setOnClickListener {
                onCoinClickListener?.onCoinClick(coin)
            }
        }
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinInfo)
    }
}