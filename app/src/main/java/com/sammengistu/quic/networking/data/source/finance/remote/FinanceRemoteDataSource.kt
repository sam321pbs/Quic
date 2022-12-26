package com.sammengistu.quic.networking.data.source.finance.remote

import com.sammengistu.quic.networking.data.models.FinanceResponse
import com.sammengistu.quic.networking.data.source.Result

interface FinanceRemoteDataSource {
    suspend fun getMarketSummary(lang: String, region: String): Result<FinanceResponse?>
}