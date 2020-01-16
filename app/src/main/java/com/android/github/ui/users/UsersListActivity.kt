package com.android.github.ui.users

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.github.R
import com.android.github.ui.detail.DetailUserActivity
import com.android.github.ui.users.adapter.UsersPagedAdapter
import com.android.github.ui.utils.observe
import kotlinx.android.synthetic.main.activity_users_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class UsersListActivity : AppCompatActivity() {
    private val viewModel: UsersListViewModel by viewModel()

    private val adapter: UsersPagedAdapter = UsersPagedAdapter {
        it?.let { startActivity(DetailUserActivity.newIntent(this, it)) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)
        setupView()
        setupObservers()
    }

    private fun setupObservers() {
        observe(viewModel.getUsersGitHub(), {
            adapter.submitList(it)
        })
        observe(viewModel.errorMessage, {
            showErrorMessage(it)
        })
    }
    private fun showErrorMessage(errorMessage: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.app_name))
        builder.setMessage(getString(R.string.error_message_of_loading))
        builder.setNeutralButton(
            getString(android.R.string.ok),
            { dialogInterface: DialogInterface, i: Int ->

            })
        builder.show()
    }
    private fun setupView() {
        rvUsersList.layoutManager = LinearLayoutManager(this)
        rvUsersList.adapter = adapter
    }
}
