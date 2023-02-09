package com.revolut.interview

import com.revolut.interview.domain.Rate

interface RatesCache {
    fun put(baseCurrencyCode: String, rates: List<Rate>)
    fun get(baseCurrencyCode: String): List<Rate>?
}