package com.revolut.interview.template

import kotlin.random.Random

class DataSource {
    private val currencies = listOf(
        CurrencyData("AUD", 1.3906),
        CurrencyData("BGN", 1.6826),
        CurrencyData("BRL", 4.1225),
        CurrencyData("CAD", 1.3196),
        CurrencyData("CHF", 0.97003),
        CurrencyData("CNY", 6.8354),
        CurrencyData("CZK", 22.124),
        CurrencyData("DKK", 6.4152),
        CurrencyData("GBP", 0.77279),
        CurrencyData("HKD", 7.8569),
        CurrencyData("HRK", 6.3958),
        CurrencyData("HUF", 280.89),
        CurrencyData("IDR", 14904.0),
        CurrencyData("ILS", 3.5881),
        CurrencyData("INR", 72.025),
        CurrencyData("ISK", 109.95),
        CurrencyData("JPY", 111.45),
        CurrencyData("KRW", 1122.5),
        CurrencyData("MXN", 19.242),
        CurrencyData("MYR", 4.14),
        CurrencyData("NOK", 8.4107),
        CurrencyData("NZD", 1.5171),
        CurrencyData("PHP", 53.85),
        CurrencyData("PLN", 3.7152),
        CurrencyData("RON", 3.9906),
        CurrencyData("RUB", 68.461),
        CurrencyData("SEK", 9.1115),
        CurrencyData("SGD", 1.3765),
        CurrencyData("THB", 32.805),
        CurrencyData("TRY", 6.5628),
        CurrencyData("ZAR", 15.334),
        CurrencyData("USD", 1.0),
        CurrencyData("EUR", 0.86033)
    )

    private val random = Random(System.currentTimeMillis())

    fun getRates(baseCurrency: String): List<Rate> {
        val base = currencies.first { it.name == baseCurrency }

        return currencies.asSequence()
            .filter { currency ->
                currency.name != baseCurrency
            }
            .map { currency ->
                val randomRatio = 1.0 + random.nextDouble() / 5.0 - 0.1
                Rate(currency.name, (base.ratio / currency.ratio) * randomRatio)
            }
            .toList()
    }
}

private data class CurrencyData(val name: String, val ratio: Double)