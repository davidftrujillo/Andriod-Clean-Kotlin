package com.xmoba.data.repository.datasource

import com.xmoba.data.model.response.ResponseData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by david on 7/8/18.
 */
interface ApiService {

    @GET(".")
    fun getUsers(@Query("page") page: Int, @Query("results") results: Int): Observable<ResponseData>
}