package com.twinflip.app.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.twinflip.core.audio.MusicManager
import com.twinflip.core.audio.SoundManager
import com.twinflip.feature_home.HomeScreen
import com.twinflip.feature_multiplayer.presentation.MultiplayerScreen
import com.twinflip.feature_multiplayer.presentation.MultiplayerViewModel
import com.twinflip.feature_singleplayer.presentation.game.GameScreen
import com.twinflip.feature_singleplayer.presentation.game.GameViewModel
import com.twinflip.feature_themes.presentation.ThemeScreen
import com.twinflip.feature_themes.presentation.ThemeViewModel

@SuppressLint("LocalContextResourcesRead", "DiscouragedApi")
@Composable
fun NavGraph(
    themeViewModel: ThemeViewModel,
    gameViewModel: GameViewModel,
    multiplayerViewModel: MultiplayerViewModel,
    soundManager: SoundManager,
    musicManager: MusicManager
) {
    val navController = rememberNavController()
    val context = LocalContext.current

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
                themeViewModel = themeViewModel,
                soundManager = soundManager,
                musicManager = musicManager
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
                    navController.navigate(NavRoutes.Home.route) {
                        popUpTo(NavRoutes.Home.route) {
                            inclusive = true
                        }
                    }
                },
                soundManager = soundManager,
                musicManager = musicManager
            )
        }

        composable(
            route = NavRoutes.Game.route + "/{themeName}/{backgroundImage}",
            arguments = listOf(
                navArgument("themeName") { type = NavType.StringType },
                navArgument("backgroundImage") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val themeName = backStackEntry.arguments?.getString("themeName") ?: ""
            val backgroundImage = backStackEntry.arguments?.getString("backgroundImage") ?: ""

            val resId = context.resources.getIdentifier(
                backgroundImage, "drawable", context.packageName
            )

            GameScreen(
                viewModel = gameViewModel,
                themeName = themeName,
                backgroundImage = resId,
                themeViewModel = themeViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                },
                soundManager = soundManager,
                musicManager = musicManager
            )

        }

        composable(
            route = NavRoutes.Multiplayer.route + "/{themeName}/{backgroundImage}",
            arguments = listOf(
                navArgument("themeName") { type = NavType.StringType },
                navArgument("backgroundImage") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val themeName = backStackEntry.arguments?.getString("themeName") ?: ""
            val backgroundImage = backStackEntry.arguments?.getString("backgroundImage") ?: ""

            val resId = context.resources.getIdentifier(
                backgroundImage, "drawable", context.packageName
            )

            MultiplayerScreen(
                multiplayerViewModel = multiplayerViewModel,
                themeName = themeName,
                backgroundImage = resId,
                onNavigateToHomeScreen = {
                    navController.navigate(NavRoutes.Home.route) {
                        launchSingleTop = true
                    }
                },
                soundManager = soundManager,
                musicManager = musicManager
            )
        }

    }

}