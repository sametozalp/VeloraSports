package com.ozalp.velorasports

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ozalp.velorasports.presentation.compose.ChooseOrganizationScreen
import com.ozalp.velorasports.presentation.compose.HomeScreen
import com.ozalp.velorasports.presentation.compose.LoginScreen
import com.ozalp.velorasports.presentation.compose.RegisterScreen
import com.ozalp.velorasports.presentation.destination.concretes.ChooseOrganizationDestination
import com.ozalp.velorasports.presentation.destination.concretes.HomeDestination
import com.ozalp.velorasports.presentation.destination.concretes.LoginDestination
import com.ozalp.velorasports.presentation.destination.concretes.RegisterDestination

@Composable
fun SportCenterNavController(modifier: Modifier = Modifier) {

    var showBottomBar by remember { mutableStateOf(false) }
    var showTopBar by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {

        }, topBar = {

        }) { innerPadding ->

        val navController = rememberNavController()
        NavHost(
            navController = navController,
            modifier = modifier.padding(innerPadding),
            startDestination = LoginDestination.routeWithArgs
        ) {

            composable(LoginDestination.routeWithArgs) {
                LoginScreen(onLoginClick = {
                    navController.navigate(ChooseOrganizationDestination.routeWithArgs)
                }, onRegisterClick = {
                    navController.navigate(RegisterDestination.routeWithArgs)
                })

            }

            composable(RegisterDestination.routeWithArgs) {
                RegisterScreen(navigateLoginScreen = {
                    navController.popBackStack()
                })

            }

            composable(ChooseOrganizationDestination.routeWithArgs) {
                ChooseOrganizationScreen()
            }

            composable(HomeDestination.routeWithArgs) {
                HomeScreen()
            }

        }
    }

}