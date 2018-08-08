package com.xmoba.xmoba.view.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xmoba.xmoba.R
import com.xmoba.xmoba.extensions.firstUppercase
import com.xmoba.xmoba.extensions.gone
import com.xmoba.xmoba.extensions.loadImage
import com.xmoba.xmoba.model.UserView
import kotlinx.android.synthetic.main.cell_user.view.*
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class UserListAdapter @Inject constructor(private val context: Context) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private var users: ArrayList<UserView> = ArrayList()
    private var userClickListener: UserListClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.cell_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {

        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val userView = getItem(position)
        userView?.let {
            holder.bindUser(userView)
            holder.itemView.setOnClickListener { onItemClickListener(userView) }
        }
    }

    fun getItem(position: Int): UserView? {

        return if (users.size > position) {

            users[position]
        } else null
    }

    fun clear() {

        this.users.clear()
        this.notifyDataSetChanged()
    }

    fun addUsers(users: List<UserView>) {

        if (users == null) {
            this.users = users
        } else {
            this.users.addAll(users)
        }

        this.notifyDataSetChanged()
    }

    fun setOnUserClickListener(onClickListener: UserListClickListener) {

        this.userClickListener = onClickListener
    }

    private fun onItemClickListener(user: UserView) {

        userClickListener?.onUserClicked(user)
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var view: View = itemView

        fun bindUser(user: UserView) {

            view.tvUserEmail.text = user.email
            view.tvUserName.text = "${user.userName.title.firstUppercase()} " +
                    "${user.userName.firstName.firstUppercase()} ${user.userName.lastName.firstUppercase()}"
            view.tvUserAge.text = "${user.birthday.age} years old" // TODO get from strings
            view.tvUserPhoneCell.text = "${user.phone} / ${user.cell}"
            view.ivUserImage.loadImage(user.picture.medium)

            when {
                "male" == user.gender -> view.ivUserGender.setImageResource(R.mipmap.gender_male)
                "female" == user.gender -> view.ivUserGender.setImageResource(R.mipmap.gender_female)
                else -> view.ivUserGender.gone()
            }
        }
    }
}