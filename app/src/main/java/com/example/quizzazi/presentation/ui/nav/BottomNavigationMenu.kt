package com.example.quizzazi.presentation.ui.nav

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.quizzazi.R

enum class BottomNavigationItem(var icon: Int, val route: Screens) {
    Trivia(R.drawable.memory_game_focus, Screens.TriviaScreen),
    Profile(R.drawable.pet_focus, Screens.ProfileScreen)
}

@Composable
fun BottomNavigationMenu(
    selectedItem: BottomNavigationItem,
    navHostController: NavHostController
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .background(Color.Transparent)) {
        for (item in BottomNavigationItem.values()) {
            val iconRes = if (item == selectedItem) {
                when (item) {
                    BottomNavigationItem.Trivia -> R.drawable.memory_game_focus
                    BottomNavigationItem.Profile -> R.drawable.pet_focus
                }
            } else {
                when (item) {
                    BottomNavigationItem.Trivia -> R.drawable.memory_game
                    BottomNavigationItem.Profile -> R.drawable.pet
                }
            }
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = "icons",
                modifier = Modifier
                    .size(40.dp)
                    .weight(1f)
                    .clickable {
                        if (currentRoute != item.route.route) {
                            navHostController.navigate(item.route.route){
                                navHostController.navigateUp()
                            }
                        } else {
                            // Если мы уже находимся на этом экране, не выполнять переход повторно
                        }
                    },
            )
        }
    }
}