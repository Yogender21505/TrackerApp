package com.example.journeytosouth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.ui.graphics.Brush
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainPage(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        verticalArrangement = Arrangement.spacedBy(32.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ButtonLists(name = "Normal List",navController,"NormalListScreen")
        ButtonLists(name = "Lazy List", navController,"LazyListScreen")
    }
}

@Composable
private fun ButtonLists(name: String,navController: NavController,page: String){
    val gradientColors = listOf(Color(0xFF000000), Color(0xFF000000))
    val roundCornerShape = RoundedCornerShape(20.dp)

    Box(
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(colors = gradientColors),
                shape = roundCornerShape
            )
            .clip(roundCornerShape)
            .clickable(onClick = {navController.navigate(page)})
            .padding(PaddingValues(horizontal = 40.dp, vertical = 16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontSize = 26.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
