package com.android.github.ui.users.adapter

import androidx.paging.PositionalDataSource
import com.android.github.data.gateways.GitHubGateway
import com.android.github.data.models.ErrorResult
import com.android.github.data.models.SuccessfulResult
import com.android.github.data.models.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class UsersPositionalDataSource(
    val gitHubRemoteGateway: GitHubGateway,
    val errorCallback: (errorMessage: String) -> Unit
) : PositionalDataSource<UserModel>(), CoroutineScope {
    private var startLoadUserId = "1"
    private var countUsersLoad = "30"

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<UserModel>) {
        launch {
            val result = gitHubRemoteGateway.loadUsers(startLoadUserId, countUsersLoad)

            when (result) {
                is SuccessfulResult -> {
                    startLoadUserId = result.model.get(result.model.size - 1).id.toString()
                    callback.onResult(result.model)
                }
                is ErrorResult -> {
                    errorCallback.invoke(result.error)
                }
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<UserModel>) {
        launch {
            val result = gitHubRemoteGateway.loadUsers(startLoadUserId, countUsersLoad)
            when (result) {
                is SuccessfulResult -> {
                    startLoadUserId = result.model.get(result.model.size - 1).id.toString()
                    callback.onResult(result.model, 0)
                }
                is ErrorResult -> {
                    errorCallback.invoke(result.error)
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
}