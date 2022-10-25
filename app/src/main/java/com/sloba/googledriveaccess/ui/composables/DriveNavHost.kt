package com.sloba.googledriveaccess.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sloba.googledriveaccess.Browse
import com.sloba.googledriveaccess.Preview
import com.sloba.googledriveaccess.Search
import com.sloba.googledriveaccess.Shared
import com.sloba.googledriveaccess.app.viewmodel.DriveViewModel
import com.sloba.googledriveaccess.ui.screens.Browse
import com.sloba.googledriveaccess.ui.screens.Preview
import com.sloba.googledriveaccess.ui.screens.Search
import com.sloba.googledriveaccess.ui.screens.Shared

@Composable
fun DriveNavHost(navController: NavHostController,
                 modifier: Modifier = Modifier,
                 viewModel: DriveViewModel = DriveViewModel(null)
) {
    NavHost(
        navController = navController,
        startDestination = Browse.route,
        modifier = modifier
    ) {
        composable(route = Browse.route) {
            Browse(viewModel)
        }
        composable(route = Shared.route) {
            Shared(viewModel)
        }
        composable(route = Search.route) {
            Search(viewModel)
        }
        composable(route = Preview.route) {
            Preview(viewModel)
        }
    }
}