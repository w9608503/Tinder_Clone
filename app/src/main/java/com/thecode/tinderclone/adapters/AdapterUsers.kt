package com.thecode.tinderclone.adapters

// UserAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thecode.tinderclone.R
import com.thecode.tinderclone.entities.User
import de.hdodenhof.circleimageview.CircleImageView

class AdapterUsers : ListAdapter<User, AdapterUsers.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user1, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = getItem(position)
        holder.bind(currentUser)
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userNameTextView: TextView = itemView.findViewById(R.id.textViewUserName)
        private val emailTextView: TextView = itemView.findViewById(R.id.textViewUseremail)
        private val addressTextView: TextView = itemView.findViewById(R.id.textViewUseraddress)
        private val circle_imge: CircleImageView = itemView.findViewById(R.id.circle_img)

        fun bind(user: User) {
            userNameTextView.text = user.username
            emailTextView.text = user.email
            addressTextView.text = user.address
            Glide.with(itemView.context)
                .load(user.imageUrl)
                .into(circle_imge)
            // Bind other user details TextViews as needed
        }
    }

    private class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.address == newItem.address
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}
