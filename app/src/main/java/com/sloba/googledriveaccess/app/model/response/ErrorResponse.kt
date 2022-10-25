package com.sloba.googledriveaccess.app.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Error response model, to parse errors fetched from server during the API calls
 */
@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "error")
    val error: ApiErrors,
    @Json(name = "code")
    val code: Int,
    @Json(name = "message")
    val message: String
)

@JsonClass(generateAdapter = true)
data class ApiErrors(
    @Json(name = "errors")
    val errors: List<ApiError>
)

@JsonClass(generateAdapter = true)
data class ApiError(
    @Json(name = "domain")
    val domain: String,
    @Json(name = "reason")
    val reason: String,
    @Json(name = "message")
    val message: String,
    @Json(name = "locationType")
    val locationType: String,
    @Json(name = "location")
    val location: String
)
