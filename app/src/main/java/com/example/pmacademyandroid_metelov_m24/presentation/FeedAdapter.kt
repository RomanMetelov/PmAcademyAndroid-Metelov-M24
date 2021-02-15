package com.example.pmacademyandroid_metelov_m24.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pmacademyandroid_metelov_m24.R
import com.example.pmacademyandroid_metelov_m24.databinding.BannedPostItemBinding
import com.example.pmacademyandroid_metelov_m24.databinding.RegularPostItemBinding

class FeedAdapter :
    androidx.recyclerview.widget.ListAdapter<PostUiModel, RecyclerView.ViewHolder>(PostsDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isBanned) R.layout.banned_post_item
        else R.layout.regular_post_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.regular_post_item -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(viewType, parent, false)
                RegularPostViewHolder(view)
            }
            R.layout.banned_post_item -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(viewType, parent, false)
                BannedPostViewHolder(view)
            }
            else -> RegularPostViewHolder(View(parent.context))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RegularPostViewHolder -> holder.bind(getItem(position))
            is BannedPostViewHolder -> holder.bind(getItem(position))
        }
    }

}

class RegularPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = RegularPostItemBinding.bind(itemView)

    fun bind(post: PostUiModel) {
        binding.apply {
            tvPostTitle.text = post.title
            tvPostBody.text = post.body
            tvWarningLabel.visibility = if (post.hasWarning) View.VISIBLE else View.GONE
            root.setBackgroundColor(post.backgroundColor)
            tvPostAuthor.text = root.context.getString(R.string.user_id, post.userId.toString())
        }
    }
}

class BannedPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = BannedPostItemBinding.bind(itemView)

    fun bind(post: PostUiModel) {
        binding.tvPostBody.text = post.title
    }
}

class PostsDiffCallback : DiffUtil.ItemCallback<PostUiModel>() {
    override fun areItemsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
        return oldItem == newItem
    }

}