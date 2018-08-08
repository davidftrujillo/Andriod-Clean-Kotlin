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
import com.xmoba.xmoba.extensions.visible
import com.xmoba.xmoba.model.UserView
import kotlinx.android.synthetic.main.cell_list_loader.view.*
import kotlinx.android.synthetic.main.cell_user.view.*
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class UserListAdapter @Inject constructor(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_USER = 0
    private val VIEW_TYPE_LOADER = 1

    private var users: ArrayList<UserView> = ArrayList()
    private var userClickListener: UserListClickListener? = null
    private var paginationEnabled = true;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(getCellLayout(viewType), parent, false)

        return when(viewType) {
            VIEW_TYPE_USER -> UserViewHolder(view)
            VIEW_TYPE_LOADER -> LoaderViewHolder(view)
            else -> {
                throw IllegalArgumentException("Invalid View type: $viewType")
            }
        }
    }

    override fun getItemCount(): Int {

        return if (users.isEmpty()) 0 else users.size + 1 // +1 because of the loader footer
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(getItemViewType(position)) {
            VIEW_TYPE_USER -> {

                val userView = getItem(position)
                userView?.let {
                    (holder as UserViewHolder).bindUser(userView)
                    holder.itemView.setOnClickListener { onItemClickListener(userView) }
                }
            }
            VIEW_TYPE_LOADER -> {

                if (paginationEnabled) {
                    (holder as LoaderViewHolder).showLoader()
                } else {
                    (holder as LoaderViewHolder).hideLoader()
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        return if (position != 0 && position == itemCount - 1) {
            VIEW_TYPE_LOADER
        } else { VIEW_TYPE_USER }
    }

    fun setPaginationEnabled(enabled: Boolean) {

        this.paginationEnabled = enabled
    }

    private fun getCellLayout(viewType: Int): Int {

        return when(viewType) {
            VIEW_TYPE_LOADER -> R.layout.cell_list_loader
            else -> R.layout.cell_user
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

    class LoaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var view: View = itemView

        fun showLoader() {

            view.pbFooterLoader.visible()
        }

        fun hideLoader() {

            view.pbFooterLoader.gone()
        }
    }
}