package com.example.newsreader.presentation.navigation

sealed class Route(
    val route: String
) {
     object OnBoardingScreen : Route("onBoardingScreen")
     object HomeScreen : Route("homeScreen")
     object AppStartNavigation : Route("appStartNavigation")
     object NewsNavigation : Route("newsNavigation")
     object NewsNavigatorScreen : Route("newsNavigatorScreen")

}