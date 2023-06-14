package com.revolut.interview

import com.revolut.interview.domain.Rate

interface RatesCache {
    fun put(baseCurrencyCode: String, rates: List<Rate>)
    fun get(baseCurrencyCode: String): List<Rate>?
}

class RatesCacheImpl : RatesCache {

    var cache = mutableListOf<Pair<String, List<Rate>>>()

    override fun put(baseCurrencyCode: String, rates: List<Rate>) {
        cache.removeAll { it.first == baseCurrencyCode }
        cache.add(Pair(baseCurrencyCode, rates))
    }

    override fun get(baseCurrencyCode: String): List<Rate>? {
        return cache.find { it.first == baseCurrencyCode }?.second
    }
}