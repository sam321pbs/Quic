package com.sammengistu.quic.networking.data.source.finance.repository

import com.sammengistu.quic.networking.data.models.FinanceResponse
import com.sammengistu.quic.networking.data.source.Result
import kotlinx.coroutines.flow.Flow

interface FinanceRepository {
    suspend fun getMarketSummary(lang: String, region: String): Flow<Result<FinanceResponse?>>
}