package com.android.github

import android.app.Application
import com.android.github.data.gateways.GitHubRestGateway
import com.android.github.data.rest.RetrofitHelper
import com.android.github.ui.detail.DetailViewModel
import com.android.github.ui.users.UsersListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    fun setupKoin() {
        startKoin {
            androidContext(this@App)
            modules(koinModule)
            if (BuildConfig.DEBUG) {
                androidLogger()
            }
        }
    }


    companion object {
        private const val GIT_HUB_REST = "git_hub_rest"

        val koinModule = module {
            single { RetrofitHelper() }
            single(named(GIT_HUB_REST)) { GitHubRestGateway(get()) }

            viewModel { DetailViewModel(get(named(GIT_HUB_REST))) }
            viewModel { UsersListViewModel(get(named(GIT_HUB_REST))) }


        }
    }
}