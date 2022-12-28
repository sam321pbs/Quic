package com.sammengistu.quic.networking.data.source.finance.remote

import com.sammengistu.quic.networking.data.models.FinanceResponse
import com.sammengistu.quic.networking.data.source.Result
import com.sammengistu.quic.networking.data.source.finance.remote.retrofit.FinanceApiService
import javax.inject.Inject

class FinanceRemoteDataSourceImpl @Inject constructor(
    private val financeApiService: FinanceApiService,
) : FinanceRemoteDataSource {
    override suspend fun getMarketSummary(lang: String, region: String): Result<FinanceResponse?> =
        try {
            val response = financeApiService.getMarketSummary(lang, region)
            if (response.isSuccessful) {
                Result.Success(response.body())
            } else {
                Result.Error(Exception(response.errorBody().toString()))
            }
        } catch (exception: Exception) {
            Result.Error(exception)
        }
}