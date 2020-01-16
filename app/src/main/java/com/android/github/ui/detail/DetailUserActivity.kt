package com.android.github.ui.detail

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.github.R
import com.android.github.data.models.UserDetailModel
import com.android.github.data.models.UserModel
import com.android.github.ui.utils.CircleTransformation
import com.android.github.ui.utils.observe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel


class DetailUserActivity : AppCompatActivity() {
    private val viewModel: DetailViewModel by viewModel()


    companion object {
        private const val EXTRA_USER_GITHUB = "extra_user_github"

        @JvmStatic
        fun newIntent(context: Context, user: UserModel): Intent {
            val intent = Intent(context, DetailUserActivity::class.java)
            intent.putExtra(EXTRA_USER_GITHUB, user)
            return intent
        }
    }


    val user by lazy { intent.getParcelableExtra<UserModel>(EXTRA_USER_GITHUB) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        viewModel.loadDetailUser(user.login)
        setupUI()
        observe(viewModel.userInfo, { showUserInfo(it) })
        observe(viewModel.errorMessage, { showErrorMessage(it) })
        observe(viewModel.progress, { showProgress(it) })
    }
    fun setupUI () {
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                this.finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
    private fun showProgress(progress: Boolean?) {
        if (progress != null && progress) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.INVISIBLE
        }
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

    private fun showUserInfo(userDetail: UserDetailModel?) {
        userDetail?.let {
            title = userDetail.name
            Picasso.get().load(user.avatarUrl).transform(CircleTransformation()).into(ivAvatar)
            tvUserName.text = userDetail.name
            tvBlogAddress.text = userDetail.blog
            tvRepoCount.text = getString(R.string.repos_count, userDetail.publicRepos)
            tvGistCount.text = getString(R.string.gists_count, userDetail.publicGists)
            tvFollowersCount.text = getString(R.string.followers_count, userDetail.followers)
        }
    }
}
