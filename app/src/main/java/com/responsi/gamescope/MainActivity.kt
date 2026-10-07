package com.responsi.gamescope

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.responsi.gamescope.ui.screen.GameDetailScreen
import com.responsi.gamescope.ui.screen.HomeScreen
import com.responsi.gamescope.ui.theme.GameScopeTheme
import com.responsi.gamescope.ui.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameScopeTheme {
                val navController = rememberNavController()
                val gameViewModel: GameViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = "home",
                    enterTransition = {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(durationMillis = 250)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(durationMillis = 250)
                        )
                    },
                    popEnterTransition = {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(durationMillis = 250)
                        )
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(durationMillis = 250)
                        )
                    },
                    predictivePopEnterTransition = {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(durationMillis = 250)
                        )
                    },
                    predictivePopExitTransition = {
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(durationMillis = 250)
                        )
                    },
                    sizeTransform = { null }
                ) {
                    composable("home") {
                        HomeScreen(
                            viewModel = gameViewModel,
                            onGameClick = { gameId ->
                                navController.navigate("detail/$gameId")
                            }
                        )
                    }

                    composable(
                        route = "detail/{gameId}",
                        arguments = listOf(
                            navArgument("gameId") {
                                type = NavType.IntType
                            }
                        )
                    ) { backStackEntry ->
                        val gameId = backStackEntry.arguments?.getInt("gameId") ?: 0
                        GameDetailScreen(
                            gameId = gameId,
                            viewModel = gameViewModel,
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
