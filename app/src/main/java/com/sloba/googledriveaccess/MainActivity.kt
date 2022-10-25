package com.sloba.googledriveaccess

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.sloba.googledriveaccess.app.util.buildGoogleSignInClient
import com.sloba.googledriveaccess.app.util.showMustLogin
import com.sloba.googledriveaccess.app.viewmodel.DriveViewModel
import com.sloba.googledriveaccess.ui.composables.LoadingDialog
import com.sloba.googledriveaccess.ui.screens.Drive
import com.sloba.googledriveaccess.ui.screens.Login
import com.sloba.googledriveaccess.ui.theme.GoogleDriveTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity class is the apps main and only UI container
 * From it it delegates the architecture of all other components
 * with minimal UI and Context related logic
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: DriveViewModel by viewModels()

    /**
     * Prepare and register the ActivityResultLauncher to receive log in result from the Intent
     */
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            viewModel.setToken(task.result, baseContext)
        } else {
            baseContext.showMustLogin()
        }
    }

    private val signInClient by lazy {
        baseContext.buildGoogleSignInClient()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(Unit, block = {
                viewModel.checkLoggedIn(this@MainActivity)
            })
            GoogleDriveTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (viewModel.loading) {
                        LoadingDialog()
                    }
                    ComposeMe(viewModel.token)
                }
            }
        }
    }

    /**
     * The actual main compose content container
     */
    @Composable
    fun ComposeMe(token: String?) {
        when (token) {
            null -> Login {
                viewModel.setLoading(true)
                requestLogin()
            }
            else -> {
                viewModel.setLoading(false)
                Drive(viewModel)
            }
        }
    }

    /**
     * Request login via Google Service Intent
     */
    private fun requestLogin() {
        val signInIntent = signInClient.signInIntent
        launcher.launch(signInIntent)
        viewModel.setLoading(false)
    }
}