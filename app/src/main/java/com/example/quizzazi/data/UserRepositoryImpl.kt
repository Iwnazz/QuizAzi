package com.example.quizzazi.data

import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.example.newsapp.utils.Constance.COLLECTION_NAME_USERS
import com.example.newsapp.utils.Constance.allTimeScore
import com.example.newsapp.utils.Constance.lastGameScore
import com.example.newsapp.utils.Constance.monthlyScore
import com.example.newsapp.utils.Constance.weeklyScore
import com.example.quizzazi.domain.model.user.User
import com.example.quizzazi.domain.repository.UserRepository
import com.example.quizzazi.utils.Response
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@Suppress("IMPLICIT_CAST_TO_ANY")
class UserRepositoryImpl @Inject constructor(private val firebaseFireStore : FirebaseFirestore):UserRepository {

    private var operationSuccessfully = false

    override fun getUserInfo(userId: String): Flow<Response<User>> = callbackFlow {
        Response.Loading
        val snapshotListener = firebaseFireStore.collection(COLLECTION_NAME_USERS)
            .document(userId)
            .addSnapshotListener{snapShot, error->
                val response = if (snapShot != null){
                    val userInfo = snapShot.toObject(User::class.java)
                    Response.Success<User>(userInfo!!)
                } else{
                  Response.Error(error?.message?:error.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose{
            snapshotListener.remove()
        }
    }

    override fun updateProfile(userId: String, name: String?, imgUrl: Uri?): Flow<Response<Boolean>> = flow {
        val userDocument = firebaseFireStore.collection(COLLECTION_NAME_USERS).document(userId)
        val updates = mutableMapOf<String, Any?>()

        if (!name.isNullOrEmpty()) {
            updates["userName"] = name
        }

        if (imgUrl != null) {
            updates["imageUrl"] = "profile_pictures/${userId}"
            uploadImage(imgUrl, userId)
        }
        try {
            userDocument.update(updates).await()
        } catch (e: Exception) {
            emit(Response.Error("Failed to update profile"))
        }
    }

    override fun setProfileImage(imageRef:String?, view: ShapeableImageView): Flow<Response<String>> = flow {
        if (!imageRef.isNullOrEmpty()) {
            val storageRef = FirebaseStorage.getInstance().reference
            val pathReference = storageRef.child(imageRef)
            val ONE_MEGABYTE: Long = 1024 * 1024
            pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener { byteArray ->
                val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                view.setImageBitmap(bmp)
            }
        }
    }

    override fun updateScores(userId: String, newScore: Double): Flow<Response<Boolean>> = callbackFlow {
        val snapshotListener = firebaseFireStore.collection(COLLECTION_NAME_USERS)
            .document(userId)
            .addSnapshotListener { snapShot, error ->
                val response = if (snapShot != null) {
                    val userInfo = snapShot.toObject(User::class.java)
                    if (userInfo != null) {
                        val newAllTimeScore = userInfo.allTimeScore + newScore
                        val newWeeklyScore = userInfo.weeklyScore + newScore
                        val newMonthlyScore = userInfo.monthlyScore + newScore
                        firebaseFireStore.collection(COLLECTION_NAME_USERS)
                            .document(userId)
                            .update(
                                allTimeScore, newAllTimeScore,
                                weeklyScore, newWeeklyScore,
                                monthlyScore , newMonthlyScore,
                                lastGameScore, newScore
                            )
                            .addOnSuccessListener {
                                trySend(Response.Success(true)).isSuccess
                                close()
                                Log.e("DataUpdate", "Updated")
                            }
                            .addOnFailureListener {
                                trySend(Response.Error("Failed to update scores")).isSuccess
                                close()
                                Log.e("DataUpdate", "Failed")
                            }
                    } else{

                    }
                } else {
                    trySend(Response.Error(error?.message ?: error.toString())).isSuccess
                    close()
                }
            }

        awaitClose {
            snapshotListener.remove()
        }
    }
    private fun uploadImage(imgUri: Uri, userId: String)
    {
        val userDocument = firebaseFireStore.collection(COLLECTION_NAME_USERS).document(userId)
        val storageRef = FirebaseStorage.getInstance().reference
        val profilePicRef = storageRef.child("profile_pictures/${userId}")
        val uploadTask = profilePicRef.putFile(imgUri)
        uploadTask.addOnCompleteListener{task->
            if (task.isSuccessful){
                userDocument.update("image","profile_pictures/${userId}")
            }
            else{
                Log.e("ImageUpload","Unsuccessful")
            }
        }
    }
}
