package com.sammengistu.quic.data.source.finance.repository

import com.sammengistu.quic.data.models.FinanceResponse
import com.sammengistu.quic.data.source.Result
import com.sammengistu.quic.data.source.finance.remote.FinanceRemoteDataSource
import com.sammengistu.quic.di.scope.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FinanceRepositoryImpl @Inject constructor(
    private val remoteDataSource: FinanceRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): FinanceRepository {
    override suspend fun getMarketSummary(lang: String, region: String): Result<FinanceResponse?> {
        return withContext(ioDispatcher) {
            when (val response = remoteDataSource.getMarketSummary(lang, region)) {
                is Result.Success -> {
                    if (response.data != null) {
                        Result.Success(response.data)
                    } else {
                        Result.Success(null)
                    }
                }
                else -> {
                    Result.Error(Exception("Got a failed result"))
                }
            }
        }
    }
}