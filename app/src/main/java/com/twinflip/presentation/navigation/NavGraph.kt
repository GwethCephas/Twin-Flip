package com.twinflip.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.twinflip.presentation.game.GameCompleteScreen
import com.twinflip.presentation.game.GameScreen
import com.twinflip.presentation.game.GameViewModel
import com.twinflip.presentation.home.HomeScreen
import com.twinflip.presentation.theme.ThemeScreen
import com.twinflip.presentation.theme.ThemeViewModel

@Composable
fun NavGraph(
    themeViewModel: ThemeViewModel,
    gameViewModel: GameViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ) {
        composable(NavRoutes.Home.route) {
            HomeScreen(
                onNavigateToThemeScreen = {
                    navController.navigate(NavRoutes.Themes.route)
                }
            )
        }

        composable(NavRoutes.Themes.route) {
            ThemeScreen(
                themeViewModel = themeViewModel,
                onNavigateToGame = { themeName ->
                    navController.navigate(NavRoutes.Game.route + "/$themeName") {
                        launchSingleTop = true
                    }
                },
                onNavigateToHome = {
                    navController.navigate(NavRoutes.Home.route)
                }
            )
        }

        composable(
            route = NavRoutes.Game.route + "/{themeName}",
            arguments = listOf(
                navArgument("themeName") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val themeName = backStackEntry.arguments?.getString("themeName") ?: ""

            GameScreen(
                viewModel = gameViewModel,
                themeName = themeName,
                onNavigateBack = {
                    navController.popBackStack()
                },
                navigateToGameCompleteScreen = {
                    navController.navigate(NavRoutes.GameComplete.route)
                }
            )

        }
        composable(
            route = NavRoutes.GameComplete.route
        ) {
            GameCompleteScreen(
                time = "00.46",
                moves = 20,
                score = 800
            )

        }

    }

}