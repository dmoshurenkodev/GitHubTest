package com.android.github.data.gateways

import com.android.github.data.models.Result
import com.android.github.data.models.UserDetailModel
import com.android.github.data.models.UserModel

interface GitHubGateway {
    suspend fun loadUsers(startUserId: String, perPage: String): Result<List<UserModel>>

    suspend fun loadUserDetail(login: String): Result<UserDetailModel>
}