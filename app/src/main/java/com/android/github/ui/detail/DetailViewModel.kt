package com.android.github.ui.detail

import androidx.lifecycle.MutableLiveData
import com.android.github.data.gateways.GitHubGateway
import com.android.github.data.models.ErrorResult
import com.android.github.data.models.SuccessfulResult
import com.android.github.data.models.UserDetailModel
import com.android.github.ui.base.BaseViewModel
import com.android.github.ui.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailViewModel(private val gitHubRemoteGateway: GitHubGateway) : BaseViewModel() {
    val userInfo = MutableLiveData<UserDetailModel>()
    val errorMessage = SingleLiveEvent<String>()
    val progress = SingleLiveEvent<Boolean>()

    fun loadDetailUser(login: String?) {
        login?.let {
            progress.postValue(true)
            launch {
                val result = gitHubRemoteGateway.loadUserDetail(login)
                progress.postValue(false)
                when (result) {
                    is SuccessfulResult -> {
                        userInfo.postValue(result.model)
                    }
                    is ErrorResult -> {
                        errorMessage.postValue(result.error)
                    }
                }
            }
        }
    }

}