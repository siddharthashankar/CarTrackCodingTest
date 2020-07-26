package com.sid.cartrackcodingchallenge.user_detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.sid.cartrackcodingchallenge.BaseActivity
import com.sid.cartrackcodingchallenge.MyApplication
import com.sid.cartrackcodingchallenge.R
import com.sid.cartrackcodingchallenge.data.source.UserRepository
import com.sid.cartrackcodingchallenge.data.source.remote.response.UserResponse
import kotlinx.android.synthetic.main.activity_user_list.*
import kotlinx.android.synthetic.main.user_list.*
import javax.inject.Inject

class UserListActivity : BaseActivity() {

    companion object {
        private const val TAG = "UserListActivity"
    }

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        (application as MyApplication).applicationComponent?.inject(this)

        val userListViewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)
        userListViewModel.init(userRepository)

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (user_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        progress_bar.visibility = View.VISIBLE

        userListViewModel.getUsers().observe(this, Observer { userListWrapper ->
            progress_bar.visibility = View.GONE
            if (userListWrapper.error == null) {

                setupRecyclerView(rv_user_list, userListWrapper.userResponseList!!)

                userListWrapper.userResponseList?.forEach { userResponse ->
                    Log.d(TAG, "UserListActivity getUsersSuccess: ${userResponse.name}")

                }
            } else {
                Log.e(TAG, "UserListActivity getUsersError: ${userListWrapper.error}")

                userListWrapper.error?.let {
                    showSnackBar(cl_parent, it)
                }
            }
        })

    }

    private fun setupRecyclerView(recyclerView: RecyclerView, userResponseList: List<UserResponse>) {
        recyclerView.adapter =
                UserRecyclerViewAdapter(
                        this,
                        userResponseList,
                        twoPane
                )
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
