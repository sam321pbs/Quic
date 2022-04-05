package com.sammengistu.quic.data.source.finance.repository

import com.sammengistu.quic.data.models.FinanceResponse
import com.sammengistu.quic.data.source.Result

interface FinanceRepository {
    suspend fun getMarketSummary(lang: String, region: String): Result<FinanceResponse?>
}