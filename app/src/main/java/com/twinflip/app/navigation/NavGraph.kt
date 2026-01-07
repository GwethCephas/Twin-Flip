package com.twinflip.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.twinflip.feature_singleplayer.presentation.game.GameScreen
import com.twinflip.feature_singleplayer.presentation.game.GameViewModel
import com.twinflip.core.presentation.home.HomeScreen
import com.twinflip.feature_multiplayer.presentation.MultiplayerScreen
import com.twinflip.feature_multiplayer.presentation.MultiplayerViewModel
import com.twinflip.feature_themes.presentation.theme.ThemeScreen
import com.twinflip.feature_themes.presentation.theme.ThemeViewModel

@Composable
fun NavGraph(
    themeViewModel: ThemeViewModel,
    gameViewModel: GameViewModel,
    multiplayerViewModel: MultiplayerViewModel
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
                },
                onNavigateToMultiPlayerScreen = { themeName ->
                    navController.navigate(NavRoutes.Multiplayer.route + "/$themeName") {
                        launchSingleTop = true
                    }
                },
                themeViewModel = themeViewModel
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
                themeViewModel = themeViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )

        }

        composable(
            route = NavRoutes.Multiplayer.route + "/{themeName}",
            arguments = listOf(
                navArgument("themeName") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val themeName = backStackEntry.arguments?.getString("themeName") ?: ""

            MultiplayerScreen(
                multiplayerViewModel = multiplayerViewModel,
                themeName = themeName
            )
        }

    }

}