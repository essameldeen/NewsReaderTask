package com.example.newsreader.presentation.newsNavigator

import NewsHomeScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.newsreader.R
import com.example.newsreader.presentation.bookMark.BookMarkScreen
import com.example.newsreader.presentation.bookMark.BookMarkViewModel
import com.example.newsreader.presentation.navigation.Route
import com.example.newsreader.presentation.newsDetails.DetailsIntent
import com.example.newsreader.presentation.newsDetails.NewsDetailsScreen
import com.example.newsreader.presentation.newsDetails.NewsDetailsViewModel
import com.example.newsreader.presentation.newsHome.NewHomeViewModel
import com.example.newsreader.presentation.newsNavigator.components.BottomNavigationItem
import com.example.newsreader.presentation.newsNavigator.components.NewsBottomNavigation
import com.example.newsreader.presentation.search.SearchScreen
import com.example.newsreader.presentation.search.SearchViewModel

@Composable
fun NewsNavigatorScreen() {
    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, "Bookmark")
        )
    }


    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by remember {
        mutableIntStateOf(0)
    }

    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookMarkScreen.route -> 2
            else -> 0
        }
    }
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route || backStackState?.destination?.route == Route.SearchScreen.route || backStackState?.destination?.route == Route.BookMarkScreen.route
    }



    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItem,
                    selected = selectedItem
                ) { index ->
                    when (index) {
                        0 -> navigateToTab(navController, Route.HomeScreen.route)
                        1 -> navigateToTab(navController, Route.SearchScreen.route)
                        2 -> navigateToTab(navController, Route.BookMarkScreen.route)
                    }

                }
            }

        },
    ) { paddingValue ->
        val bottomNavigationPadding = paddingValue.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomNavigationPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: NewHomeViewModel = hiltViewModel()
                NewsHomeScreen(viewModel, navigateToSearch = {
                    navigateToTab(navController, Route.SearchScreen.route)
                }, navigateToDetails = { id ->
                    navigateToDetails(navController, id)
                })
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(viewModel = viewModel) { id ->
                    navigateToDetails(navController, id)
                }
            }
            composable(route = Route.DetailsScreen.route) {
                val viewModel: NewsDetailsViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<String>("articleId")
                    ?.let { article ->
                        viewModel.handleIntent(DetailsIntent.LoadArticleDetails(article))
                        NewsDetailsScreen(viewModel = viewModel) {
                            navController.navigateUp()
                        }
                    }


            }
            navigation(
                route = Route.NewsNavigation.route,
                startDestination = Route.BookMarkScreen.route
            ) {
                composable(route = Route.BookMarkScreen.route) {
                    val viewModel: BookMarkViewModel = hiltViewModel()
                    BookMarkScreen(viewModel) { articleId ->
                        navigateToDetails(navController, articleId)
                    }
                }
            }


        }

    }
}

fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(navController: NavController, article: String) {
    navController.currentBackStackEntry?.savedStateHandle?.set("articleId", article)
    navController.navigate(Route.DetailsScreen.route)
}

