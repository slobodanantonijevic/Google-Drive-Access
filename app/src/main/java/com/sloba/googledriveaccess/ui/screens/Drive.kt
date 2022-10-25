package com.sloba.googledriveaccess.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sloba.googledriveaccess.*
import com.sloba.googledriveaccess.app.util.navigateSingleTopTo
import com.sloba.googledriveaccess.app.viewmodel.DriveViewModel
import com.sloba.googledriveaccess.ui.composables.DriveNavHost
import com.sloba.googledriveaccess.ui.composables.TabRow
import com.sloba.googledriveaccess.ui.screens.Preview

/**
 * Main container composable for through screen navigation
 */
@Composable
fun Drive(viewModel: DriveViewModel) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = tabRowScreens.find { it.route == currentDestination?.route } ?: Browse

        Scaffold(
            topBar = {
                if (viewModel.displayTabRow) {
                    TabRow(
                        allScreens = tabRowScreens,
                        onTabSelected = { new ->
                            navController.navigateSingleTopTo(new.route)
                        },
                        currentScreen = currentScreen
                    )
                }
                if (viewModel.preview.isNotEmpty()) {
                    if (currentDestination?.route != Preview.route) {
                        navController.navigateSingleTopTo(Preview.route)
                    }
                }
            },
            bottomBar = {
                viewModel.error?.let {
                    Text(text = it)
                }
            }) { innerPadding ->
            DriveNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                viewModel
            )
        }

}