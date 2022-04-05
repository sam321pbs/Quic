package com.sammengistu.quic.data.source.finance.remote

import com.sammengistu.quic.data.models.FinanceResponse
import com.sammengistu.quic.data.source.Result

interface FinanceRemoteDataSource {
    suspend fun getMarketSummary(lang: String, region: String): Result<FinanceResponse?>
}