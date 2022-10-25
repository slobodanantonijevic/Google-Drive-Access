package com.sloba.googledriveaccess.app.repo

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.haroldadmin.cnradapter.NetworkResponse
import com.sloba.googledriveaccess.app.model.response.ErrorResponse
import com.sloba.googledriveaccess.app.model.response.FilesResponse
import com.sloba.googledriveaccess.app.network.Api
import javax.inject.Inject

/**
 * Repository delegated to Google's services interaction
 */
class DriveRepository @Inject constructor(val api: Api) {

    /**
     * Check if the user is already logged in
     */
    fun checkLoggedIn(context: Context): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context)
    }

    /**
     * @GET method aiming at file.list endpoint of the Drive REST API v3
     */
    suspend fun getFiles(token: String?,
                         query: String = ""): NetworkResponse<FilesResponse, ErrorResponse> {
        return api.getFiles("Bearer $token", query)
    }
}