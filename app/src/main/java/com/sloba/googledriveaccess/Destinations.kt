package com.sloba.googledriveaccess

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Definition of the NavGraph destinations
 */
interface Destination {
    val icon: ImageVector
    val route: String
}

object Browse : Destination {
    override val icon = Icons.Filled.AccountBox
    override val route = "my drive"
}

object Shared : Destination {
    override val icon = Icons.Filled.Group
    override val route = "shared with me"
}

object Search : Destination {
    override val icon = Icons.Filled.Search
    override val route = "search"
}

object Preview : Destination {
    override val icon = Icons.Filled.Preview
    override val route = "preview"
}

val tabRowScreens = listOf(Browse, Shared, Search)