package com.sid.cartrackcodingchallenge.user_detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sid.cartrackcodingchallenge.R
import com.sid.cartrackcodingchallenge.data.source.remote.response.UserResponse
import kotlinx.android.synthetic.main.user_list_item.view.*

class UserRecyclerViewAdapter(
        private val parentActivity: UserListActivity,
        private val userResponseList: List<UserResponse>,
        private val twoPane: Boolean
) : RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val userResponse = v.tag as UserResponse
            if (twoPane) {
                val fragment = UserDetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(UserDetailFragment.BUNDLE_KEY_USER, userResponse)
                    }
                }
                parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.user_detail_container, fragment)
                        .commit()
            } else {
                val intent = Intent(v.context, UserDetailActivity::class.java).apply {
                    putExtra(UserDetailFragment.BUNDLE_KEY_USER, userResponse)
                }
                v.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = userResponseList[position]
        holder.nameTextView.text = item.name

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = userResponseList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.tv_name
    }
}