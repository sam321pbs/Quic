package com.sammengistu.quic.data.source.finance.remote

import android.util.Log
import com.sammengistu.quic.data.models.FinanceResponse
import com.sammengistu.quic.data.source.Result
import com.sammengistu.quic.data.source.finance.remote.retrofit.FinanceApiService
import com.sammengistu.quic.di.scope.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FinanceRemoteDataSourceImpl @Inject constructor(
    private val financeApiService: FinanceApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FinanceRemoteDataSource {
    override suspend fun getMarketSummary(lang: String, region: String): Result<FinanceResponse?> =
        withContext(ioDispatcher) {
            return@withContext try {
                val response = financeApiService.getMarketSummary(lang, region)
                if (response.isSuccessful) {
                    Result.Success(response.body()).also {
                        Log.d("FinanceRemoteDataImpl", response.body().toString())
                    }
                } else {
                    Result.Success(null)
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }
}