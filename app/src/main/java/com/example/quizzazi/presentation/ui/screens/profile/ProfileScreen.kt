package com.example.quizzazi.presentation.ui.screens.profile

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.quizzazi.R
import com.example.quizzazi.presentation.components.game.DisplayLoadingIndicator
import com.example.quizzazi.presentation.components.menu.RoundedImage
import com.example.quizzazi.presentation.components.profile.ActionCard
import com.example.quizzazi.presentation.components.profile.AdditionalStatItem
import com.example.quizzazi.presentation.components.profile.PlayerStatItem
import com.example.quizzazi.presentation.ui.nav.BottomNavigationItem
import com.example.quizzazi.presentation.ui.nav.BottomNavigationMenu
import com.example.quizzazi.presentation.ui.nav.Screens
import com.example.quizzazi.utils.Response

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun UserProfileScreen(navController: NavHostController, viewModel: ProfileScreenViewModel = hiltViewModel()) {
    viewModel.getUserInfo()
    val imageUri by viewModel.userImageUri.collectAsState()
    viewModel.updateUserImageUri(imageUri)
    val userRanking = "123"
    when (val response = viewModel.getUserData.value) {
        is Response.Loading -> {
            DisplayLoadingIndicator()
        }
        is Response.Success -> {
            Log.d("UserInfoTop", "Successfully loaded data: ${response.data}")
            if (response.data != null) {
                val obj = response.data
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Profile",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            textAlign = TextAlign.Center
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            RoundedImage(
                                imageUrl = obj.imageUrl,
                                userName = obj.userName,
                                modifier = Modifier.size(90.dp)
                            )
                        }

                        Text(
                            text = obj.userName,
                            fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = obj.email,
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.finlandica_bold)),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            textAlign = TextAlign.Center
                        )

                        PlayerStatItem(
                            icon = R.drawable.ranking,
                            label = "Ranking",
                            value = userRanking
                        )
                        PlayerStatItem(
                            icon = R.drawable.star,
                            label = "AziPoints",
                            value = obj.allTimeScore.toString()
                        )
                        AdditionalStatItem(
                            label = "Last game score:",
                            value = obj.lastGameScore.toString()
                        )
                        AdditionalStatItem(
                            label = "Weekly Score:",
                            value = obj.weeklyScore.toString()
                        )
                        AdditionalStatItem(
                            label = "Monthly Score:",
                            value = obj.monthlyScore.toString()
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        ActionCard(icon = R.drawable.log_out, label = "Sign Out") {
                            viewModel.signOut()
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        ActionCard(icon = R.drawable.edit, label = "Edit Profile") {
                            navController.navigate(Screens.ProfileEditScreen.route) {
                                popUpTo(Screens.ProfileScreen.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
                Box(modifier = Modifier.fillMaxWidth()) {
                    BottomNavigationMenu(
                        selectedItem = BottomNavigationItem.Profile,
                        navHostController = navController
                    )
                }
            }
        } is Response.Error -> {
        Text(text = "Error")
    }
    }
}