package com.sammengistu.quic.networking.data.source.finance.repository

import com.sammengistu.quic.networking.data.models.FinanceResponse
import com.sammengistu.quic.networking.data.source.Result
import com.sammengistu.quic.networking.data.source.finance.remote.FinanceRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FinanceRepositoryImpl @Inject constructor(
    private val remoteDataSource: FinanceRemoteDataSource,
): FinanceRepository {
    override suspend fun getMarketSummary(lang: String, region: String): Flow<Result<FinanceResponse?>> = flow {
        emit(remoteDataSource.getMarketSummary(lang, region))
    }
}