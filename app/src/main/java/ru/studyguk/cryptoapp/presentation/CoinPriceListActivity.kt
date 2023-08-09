package ru.studyguk.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_coin_price_list.*
import ru.studyguk.cryptoapp.R
import ru.studyguk.cryptoapp.presentation.adapters.CoinInfoAdapter
import ru.studyguk.cryptoapp.data.model.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        val adapter = CoinInfoAdapter(this)
        rvCoinPriceList.adapter = adapter
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
//                Snackbar.make(
//                    constraintLayout,
//                    coinPriceInfo.fromSymbol,
//                    Snackbar.LENGTH_SHORT
//                ).show()
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }
        }
        viewModel = ViewModelProviders.of(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
            Snackbar.make(
                constraintLayout,
                "Данные обновлены",
                Snackbar.LENGTH_SHORT
            ).show()
        })
    }
}