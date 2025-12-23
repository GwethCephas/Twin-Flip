package com.twinflip.app.navigation


sealed class NavRoutes(val route: String) {

    data object Home : NavRoutes("home")

    data object Game : NavRoutes("game")

    data object Themes : NavRoutes("themes")

    data object Multiplayer : NavRoutes("multiplayer")

}


