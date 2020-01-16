package com.android.github.ui.users

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.android.github.data.gateways.GitHubGateway
import com.android.github.data.models.UserModel
import com.android.github.ui.base.BaseViewModel
import com.android.github.ui.users.adapter.UsersDataSourceFactory
import com.android.github.ui.utils.SingleLiveEvent

class UsersListViewModel(val gitHubRemoteGateway: GitHubGateway) : BaseViewModel() {
    val errorMessage = SingleLiveEvent<String>()


    fun getUsersGitHub(): LiveData<PagedList<UserModel>> {
        val dataSource = UsersDataSourceFactory(gitHubRemoteGateway, ::errorMessage)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(1)
            .build()
        return LivePagedListBuilder(dataSource, config).setBoundaryCallback(object :
            PagedList.BoundaryCallback<UserModel>() {
            override fun onZeroItemsLoaded() {
//                view.visible()
            }

            override fun onItemAtFrontLoaded(item: UserModel) {
//                view.gone()
            }
        }).build()
    }

    fun errorMessage(error: String) {
        errorMessage.postValue(error)
    }
}