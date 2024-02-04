package com.example.quizzazi.data

import com.example.newsapp.utils.Constance.COLLECTION_NAME_USERS
import com.example.quizzazi.domain.model.user.User

import com.example.quizzazi.domain.repository.RepositoryAuth
import com.example.quizzazi.utils.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
class RepositoryAuthImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
) : RepositoryAuth {

    override fun isUserAuthenticatedInFirebase(): Boolean = auth.currentUser != null

    private suspend fun performFirebaseAuthOperation(
        operation: suspend () -> Unit
    ): Flow<Response<Boolean>> = flow {
        var operationSuccessful: Boolean
        try {
            emit(Response.Loading)
            withContext(Dispatchers.IO) {
                operation()
                operationSuccessful = true
            }
            emit(Response.Success(operationSuccessful))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An Unexpected Error", e))
        }
    }

    override suspend fun firebaseSignIn(email: String, password: String): Flow<Response<Boolean>> =
        performFirebaseAuthOperation {
            auth.signInWithEmailAndPassword(email, password).await()
        }

    override fun firebaseSignOut(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            withContext(Dispatchers.IO) {
                auth.signOut()
            }
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An Unexpected Error", e))
        }
    }

    override suspend fun firebaseSignUp(
        email: String,
        password: String,
        userName: String,
    ): Flow<Response<Boolean>> = performFirebaseAuthOperation {
        auth.createUserWithEmailAndPassword(email, password).await()
        val userid = auth.currentUser?.uid!!
        val obj = User(userName = userName, userid = userid, password = password, email = email)
        fireStore.collection(COLLECTION_NAME_USERS).document(userid)
            .set(obj).await()
    }
}