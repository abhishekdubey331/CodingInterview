package com.revolut.interview

import com.revolut.interview.data.RateDto
import io.reactivex.Single
import kotlin.random.Random

interface RatesService {
    suspend fun getRates(baseCurrencyCode: String): List<RateDto>
    fun getRatesSingle(baseCurrencyCode: String): Single<List<RateDto>>
}

class LocalRatesService : RatesService {

    private val rates = listOf(
        RateDto("USD", 1.0),
        RateDto("GBP", 0.77279),
        RateDto("EUR", 0.86033),
        RateDto("AUD", 1.3906),
        RateDto("BGN", 1.6826),
        RateDto("BRL", 4.1225),
        RateDto("CAD", 1.3196),
        RateDto("CHF", 0.97003),
        RateDto("CNY", 6.8354),
        RateDto("CZK", 22.124),
        RateDto("DKK", 6.4152),
        RateDto("HKD", 7.8569),
        RateDto("HRK", 6.3958),
        RateDto("HUF", 280.89),
        RateDto("IDR", 14904.0),
        RateDto("ILS", 3.5881),
        RateDto("INR", 72.025),
        RateDto("ISK", 109.95),
        RateDto("JPY", 111.45),
        RateDto("KRW", 1122.5),
        RateDto("MXN", 19.242),
        RateDto("MYR", 4.1404),
        RateDto("NOK", 8.4107),
        RateDto("NZD", 1.5171),
        RateDto("PHP", 53.85),
        RateDto("PLN", 3.7152),
        RateDto("RON", 3.9906),
        RateDto("RUB", 68.461),
        RateDto("SEK", 9.1115),
        RateDto("SGD", 1.3765),
        RateDto("THB", 32.805),
        RateDto("TRY", 6.5628),
        RateDto("ZAR", 15.334)
    )

    private val random = Random(System.currentTimeMillis())

    override suspend fun getRates(baseCurrencyCode: String): List<RateDto> {
        return rates.mapIndexed { index, rates ->
            if (index != 0) {
                val randomRatio = 1.0 + random.nextDouble() / 5.0 - 0.1
                RateDto(rates.currency, rates.value * randomRatio)
            } else rates
        }
    }

    override fun getRatesSingle(baseCurrencyCode: String): Single<List<RateDto>> {
        return Single.just(
            rates.mapIndexed { index, rates ->
                if (index != 0) {
                    val randomRatio = 1.0 + random.nextDouble() / 5.0 - 0.1
                    RateDto(rates.currency, rates.value * randomRatio)
                } else rates
            }
        )
    }
}