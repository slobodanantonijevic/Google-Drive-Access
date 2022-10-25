package com.sloba.googledriveaccess.app.network

import com.haroldadmin.cnradapter.NetworkResponse
import com.sloba.googledriveaccess.app.model.response.ErrorResponse
import com.sloba.googledriveaccess.app.model.response.FilesResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Api interface is the main container with API endpoints and their respective definitions
 */
interface Api {

    /**
     * file.list endpoint from Drive REST API v3
     */
    @GET("drive/v3/files?")
    suspend fun getFiles(
        @Header("Authorization") auth: String,
        @Query("q", encoded = true) q: String,
        @Query("orderBy", encoded = true) orderBy: String = "folder"
    ): NetworkResponse<FilesResponse, ErrorResponse>
}