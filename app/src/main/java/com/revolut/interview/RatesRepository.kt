package com.revolut.interview

import com.revolut.interview.domain.Rate
import io.reactivex.Single

interface RatesRepository {
    suspend fun getRates(): List<Rate>
    fun getRatesSingle(): Single<List<Rate>>
}