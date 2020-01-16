package com.android.github.data.gateways

import com.android.github.data.models.UserDetailModel
import com.android.github.data.models.UserModel
import com.android.github.data.rest.RetrofitHelper
import com.android.github.ui.utils.awaitResult
import com.android.github.data.models.Result

class GitHubRestGateway(val retrofitHelper: RetrofitHelper) : GitHubGateway {

    override suspend fun loadUsers(startUserId: String, perPage: String): Result<List<UserModel>> {
        return retrofitHelper.apiGitHub.loadUsers(startUserId, perPage).awaitResult()
    }
    override suspend fun loadUserDetail(login: String): Result<UserDetailModel> {
        return retrofitHelper.apiGitHub.loadUserDetail(login).awaitResult()
    }
}