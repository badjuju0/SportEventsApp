package com.example.sporteventsapp.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomBar( navController: NavHostController){
    var screens = listOf(
        Screens.Event,
        Screens.Profile

    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any{it.route == currentDestination?.route}
    if (bottomBarDestination){
        NavigationBar(containerColor = background){
            screens.forEach {screen->
                AddItem(screen = screen,
                    navDestination = currentDestination ,
                    navController = navController )
            }
        }
    }

}
@Composable
fun RowScope.AddItem(
    screen: Screens,
    navDestination: NavDestination?,
    navController: NavHostController
){
    NavigationBarItem(
        icon = {
            Icon(imageVector = screen.icon, contentDescription = " NavBar Icon")
        },
        label = {
            Text(text = screen.title)
        },
        selected = navDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            navController.navigate(screen. route)
        })
}