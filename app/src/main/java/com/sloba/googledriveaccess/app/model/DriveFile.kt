package com.sloba.googledriveaccess.app.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Drive file data class
 */
@JsonClass(generateAdapter = true)
data class DriveFile(
    @Json(name = "kind")
    val kind: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "mimeType")
    val mimeType: String
)
