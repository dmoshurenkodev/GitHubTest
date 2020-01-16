package com.android.github.ui.users.adapter

import androidx.paging.DataSource
import com.android.github.data.gateways.GitHubGateway
import com.android.github.data.models.UserModel

class UsersDataSourceFactory(
    val gitHubRemoteGateway: GitHubGateway,
    val errorMessage: (errorMessage: String) -> Unit
) : DataSource.Factory<Int, UserModel>() {
    override fun create(): DataSource<Int, UserModel> {
        return UsersPositionalDataSource(gitHubRemoteGateway, errorMessage)
    }

}