package com.example.quizzazi.presentation.ui.screens.profile

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.quizzazi.presentation.components.game.DisplayLoadingIndicator
import com.example.quizzazi.utils.Response

@Composable
fun ProfileEditScreen(profileEditViewModel: ProfileEditViewModel = hiltViewModel()) {


    val name by profileEditViewModel.name.collectAsState()

    val galleryLauncher =  rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
        imageUri?.let {
            profileEditViewModel.setImageUri(imageUri)
        }
    }

    val imageUri by profileEditViewModel.imageUri.collectAsState()
  profileEditViewModel.updateProfile(imageUri)

    when (val response = viewModel.getUserData.value) {
        is Response.Loading -> {
            DisplayLoadingIndicator()
        }
        is Response.Success -> {
            Log.d("UserInfoTop", "Successfully loaded data: ${response.data}")
            if (response.data != null) {
                val obj = response.data
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(data = imageUri),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(150.dp)
                .clickable {
                    galleryLauncher.launch("image/*")
                },
            contentScale = ContentScale.Crop
        )
        TextField(
            value = name,
            onValueChange = { newName -> profileEditViewModel.setName(newName) },
            label = { Text("Enter your name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        Button(
            onClick = { profileEditViewModel.updateProfile() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Update Profile")
        }
    }
}

