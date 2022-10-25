package com.sloba.googledriveaccess.app.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import com.sloba.googledriveaccess.BuildConfig
import com.sloba.googledriveaccess.R

/**
 * Extension function to form and display a sign in fail general error message
 */
fun Context.showMustLogin() {
    showToast(this, R.string.must_log_in)
}

/**
 * Extension function to ease up Toast calls
 */
fun showToast(context: Context, @StringRes message: Int) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

/**
 * Extension function to form a GoogleSignInClient necessary for google log in to access the Drive files
 */
fun Context.buildGoogleSignInClient(): GoogleSignInClient {
    val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(BuildConfig.GOOGLE_CLIENT_ID)
        .requestScopes(Scope(Scopes.DRIVE_FULL))
        .requestEmail()
        .requestProfile()
        .build()
    return GoogleSignIn.getClient(this, signInOptions)
}

/**
 * Extension function to navigate Single top
 */
fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }