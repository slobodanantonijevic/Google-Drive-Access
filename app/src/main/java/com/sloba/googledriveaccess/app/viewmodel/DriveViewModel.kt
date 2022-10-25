package com.sloba.googledriveaccess.app.viewmodel

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.web.WebViewState
import com.google.android.gms.auth.GoogleAuthUtil
import com.google.android.gms.auth.UserRecoverableAuthException
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.haroldadmin.cnradapter.NetworkResponse
import com.sloba.googledriveaccess.app.model.DriveFile
import com.sloba.googledriveaccess.app.model.response.ErrorResponse
import com.sloba.googledriveaccess.app.model.response.FilesResponse
import com.sloba.googledriveaccess.app.repo.DriveRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * App's main ViewModel to store and manage UI-related data in a lifecycle conscious way
 */
@HiltViewModel
class DriveViewModel @Inject constructor(private val repo: DriveRepository?): ViewModel() {

    /**
     * Hoisted states for Jetpack Compose to monitor:
     * files (list), (auth) token, (response) error, loading (dialog), displayTabRow, preview (URL)
     */

    private val _files = mutableStateListOf<DriveFile>()
    val files: List<DriveFile>
        get() = _files

    private var _token by mutableStateOf<String?>(null)
    val token: String?
        get() = _token

    private var _error by mutableStateOf<String?>(null)
    val error: String?
        get() = _error

    private var _loading by mutableStateOf(false)
    val loading: Boolean
        get() = _loading

    private var _displayTabRow by mutableStateOf(true)
    val displayTabRow: Boolean
        get() = _displayTabRow

    private var _preview by mutableStateOf<String>("")
    val preview: String
        get() = _preview

    /**
     * Check if the user is already logged in
     */
    fun checkLoggedIn(context: Context) {
        setLoading(true)
        setToken(repo?.checkLoggedIn(context), context)
    }

    /**
     * Extract the Auth token for Drive REST API v3 access
     */
    fun setToken(account: GoogleSignInAccount?, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            _token = account?.account?.let {
                try {
                    GoogleAuthUtil.getToken(
                        context, it,
                        "oauth2:https://www.googleapis.com/auth/drive"
                    )
                } catch (e: UserRecoverableAuthException) {
                    null
                }
            }
            setLoading(false)
        }
    }

    /**
     * Get the list of files from Drive API, per set query
     */
    fun fetchFiles(query: String) {
        clearFiles()
        setLoading(true)
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repo?.getFiles(_token, query)) {
                is NetworkResponse.Error -> {
                    handleError(response)
                }
                is NetworkResponse.Success -> {
                    setFiles(response.body)
                    resetError()
                }
            }

            return@launch
        }
    }

    /**
     * Set files to notify the JetPack compose of the state change and trigger the recomposition
     */
    private fun setFiles(body: FilesResponse?) {
        body?.files?.let {
            _files.clear()
            _files.addAll(it)
        }
    }

    /**
     * Clear the file list to prepare for the new batch
     */
    fun clearFiles() {
        _files.clear()
    }

    /**
     * Handle error returned y the Drive REST API
     */
    private fun handleError(response: NetworkResponse.Error<FilesResponse, ErrorResponse>) {
        var err = "Unknown error"
        response.body?.message?.let {
            err = it
        }
        _error = err
    }

    /**
     * Remove the error from UI
     */
    private fun resetError() {
        _error = null
    }

    /**
     * Trigger loading dialog on or off
     */
    fun setLoading(loading: Boolean) {
        if (!loading) {
            // Delay for a second and a half just for the sake of the prettier effect. Will also help a but in preventing multiple clicks
            Handler(Looper.getMainLooper()).postDelayed({
                _loading = loading
            }, 1500)
        } else {
            _loading = loading
        }
    }

    /**
     * Formed preview URL
     */
    fun setPreview(preview: String) {
        _preview = preview
    }

    /**
     * Should navigation tabs be displayed?
     */
    fun setDisplayTabRow(display: Boolean) {
        _displayTabRow = display
    }
}