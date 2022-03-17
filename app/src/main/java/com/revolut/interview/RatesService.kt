package com.revolut.interview

import com.revolut.interview.data.RatesDto
import io.reactivex.Single
import kotlin.random.Random

interface RatesService {
    suspend fun getRates(): List<RatesDto>
    fun getRatesSingle(): Single<List<RatesDto>>
}

class LocalRatesService : RatesService {

    private val rates = listOf(
        RatesDto("USD", 1.0),
        RatesDto("GBP", 0.77279),
        RatesDto("EUR", 0.86033),
        RatesDto("AUD", 1.3906),
        RatesDto("BGN", 1.6826),
        RatesDto("BRL", 4.1225),
        RatesDto("CAD", 1.3196),
        RatesDto("CHF", 0.97003),
        RatesDto("CNY", 6.8354),
        RatesDto("CZK", 22.124),
        RatesDto("DKK", 6.4152),
        RatesDto("HKD", 7.8569),
        RatesDto("HRK", 6.3958),
        RatesDto("HUF", 280.89),
        RatesDto("IDR", 14904.0),
        RatesDto("ILS", 3.5881),
        RatesDto("INR", 72.025),
        RatesDto("ISK", 109.95),
        RatesDto("JPY", 111.45),
        RatesDto("KRW", 1122.5),
        RatesDto("MXN", 19.242),
        RatesDto("MYR", 4.1404),
        RatesDto("NOK", 8.4107),
        RatesDto("NZD", 1.5171),
        RatesDto("PHP", 53.85),
        RatesDto("PLN", 3.7152),
        RatesDto("RON", 3.9906),
        RatesDto("RUB", 68.461),
        RatesDto("SEK", 9.1115),
        RatesDto("SGD", 1.3765),
        RatesDto("THB", 32.805),
        RatesDto("TRY", 6.5628),
        RatesDto("ZAR", 15.334)
    )

    private val random = Random(System.currentTimeMillis())

    override suspend fun getRates(): List<RatesDto> {

        return rates.mapIndexed { index, rates ->
            if (index != 0) {
                val randomRatio = 1.0 + random.nextDouble() / 5.0 - 0.1
                RatesDto(rates.currency, rates.value * randomRatio)
            } else rates
        }
    }

    override fun getRatesSingle(): Single<List<RatesDto>> {
        return Single.just(
            rates.mapIndexed { index, rates ->
                if (index != 0) {
                    val randomRatio = 1.0 + random.nextDouble() / 5.0 - 0.1
                    RatesDto(rates.currency, rates.value * randomRatio)
                } else rates
            }
        )
    }
}