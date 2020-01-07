import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.GsonConverter
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.Routing
import io.ktor.routing.get
import kotlin.math.floor
import kotlin.random.Random

private val random = Random
private val rates = mapOf(
    "AUD" to 1.581853,
    "BGN" to 1.95667,
    "BRL" to 4.180272,
    "CAD" to 1.496278,
    "CHF" to 1.135581,
    "CNY" to 7.651233,
    "CZK" to 25.6714,
    "DKK" to 7.460322,
    "EUR" to 1.0,
    "GBP" to 0.875964,
    "HKD" to 8.865545,
    "HRK" to 7.411408,
    "HUF" to 318.369525,
    "IDR" to 15976.631289,
    "ILS" to 4.086349,
    "INR" to 80.578328,
    "ISK" to 134.225376,
    "JPY" to 124.820654,
    "KRW" to 1272.369725,
    "MXN" to 21.737622,
    "MYR" to 4.614057,
    "NOK" to 9.756221,
    "NZD" to 1.645278,
    "PHP" to 59.227941,
    "PLN" to 4.332249,
    "RON" to 4.741708,
    "RUB" to 74.881412,
    "SEK" to 10.477164,
    "SGD" to 1.532713,
    "THB" to 35.317401,
    "USD" to 1.129649,
    "ZAR" to 15.893079
)
data class ExchangeRatesResponse(val baseCurrency: String, val rates: Map<String, Double>)

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        register(ContentType.Application.Json, GsonConverter())
    }
    install(Routing) {
        get("/"){
            call.respondRedirect("/latest", permanent = true)
        }
        get("/latest") {
            val baseCurrency = call.request.queryParameters["base"]?.toUpperCase() ?: "EUR"
            val baseCurrencyRate = rates[baseCurrency]
            if (baseCurrencyRate != null) {
                call.respond(
                    ExchangeRatesResponse(
                        baseCurrency = baseCurrency,
                        rates = calculateRates(baseCurrency, baseCurrencyRate)
                    )
                )
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid base currency")
            }
        }
    }
}


private fun calculateRates(baseCurrency: String, baseCurrencyRate: Double): Map<String, Double> {
    return rates.filter { it.key != baseCurrency }.mapValues {
        val rate = (it.value / baseCurrencyRate)
        floor((rate + rate * random.nextDouble() * 0.02) * 1000) / 1000
    }
}