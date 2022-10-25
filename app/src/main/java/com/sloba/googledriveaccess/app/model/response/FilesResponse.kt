package com.sloba.googledriveaccess.app.model.response

import com.sloba.googledriveaccess.app.model.DriveFile
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * File list response model, mapped to Drive api using Moshi
 */
@JsonClass(generateAdapter = true)
data class FilesResponse(
    @Json(name = "files")
    val files: List<DriveFile>
)
