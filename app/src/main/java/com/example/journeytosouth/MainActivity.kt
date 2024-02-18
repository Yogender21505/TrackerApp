package com.example.journeytosouth

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.journeytosouth.ui.theme.JourneyToSouthTheme

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            JourneyToSouthTheme {
                Box(modifier= Modifier.fillMaxSize()){
                    val navController= rememberNavController()
                    NavHost(navController = navController, startDestination = "main" ){
                        composable(route = "main"){
                            MainPage(navController = navController)
                        }
                        composable(route = "NormalListScreen"){
                            NormalListScreen()
                        }
                        composable(route= "LazyListScreen"){
                            LazyListScreen()
                        }
                    }
                }
            }
        }
    }
}


