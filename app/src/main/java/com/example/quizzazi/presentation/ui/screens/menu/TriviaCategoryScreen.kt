package com.example.quizzazi.presentation.ui.screens.menu

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.quizzazi.domain.model.quiz.CategoryModel
import com.example.quizzazi.presentation.components.game.DisplayLoadingIndicator
import com.example.quizzazi.presentation.components.menu.CategoryItem
import com.example.quizzazi.presentation.components.menu.UserInfoTop
import com.example.quizzazi.presentation.ui.nav.BottomNavigationItem
import com.example.quizzazi.presentation.ui.nav.BottomNavigationMenu
import com.example.quizzazi.utils.Response
import com.example.quizzazi.utils.getBlackToWhiteColor



@SuppressLint("SuspiciousIndentation")
@Composable
fun TriviaCategoryScreen(navController: NavHostController, viewModel: TriviaGetQuizViewModel = hiltViewModel()) {
   val categoriesResponse by viewModel.categories.collectAsState()
    val questionStatsResponse by viewModel.questionStats.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

            when (categoriesResponse) {
                is Response.Loading -> {
                    DisplayLoadingIndicator()
                }
                is Response.Success -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(getBlackToWhiteColor())
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {
                            UserInfoTop(viewModel)
                            val categories =
                                (categoriesResponse as Response.Success<List<CategoryModel>>).data
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(6.dp)
                            ) {
                                items(categories) { category ->
                                    // Access the data property when the response is Success
                                    val categoryStats =
                                        when (val statsResponse = questionStatsResponse) {
                                            is Response.Success -> statsResponse.data?.categories?.get(
                                                category.id
                                            )

                                            else -> null
                                        }
                                    CategoryItem(
                                        category = category,
                                        categoryStats = categoryStats,
                                        navController = navController
                                    )
                                }
                            }
                            BottomNavigationMenu(
                                selectedItem = BottomNavigationItem.Trivia,
                                navHostController = navController
                            )
                        }
                    }
                    Box(modifier = Modifier.fillMaxWidth()) {
                        BottomNavigationMenu(
                            selectedItem = BottomNavigationItem.Trivia,
                            navHostController = navController
                        )
                    }
                }
                is Response.Error -> {
                    Text(text = "Category's not available currently")
                }
            }
        }

