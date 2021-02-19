package com.garmin.garminkaptain.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.garmin.garminkaptain.data.Review
import com.garmin.garminkaptain.databinding.PoiReviewItemBinding
import com.garmin.garminkaptain.formatMmDdYy

class PoiReviewListAdapter() :
    ListAdapter<Review, PoiReviewListAdapter.PoiReviewListItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PoiReviewListItemViewHolder {
        return PoiReviewListItemViewHolder(
            PoiReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holderReview: PoiReviewListItemViewHolder, position: Int) {
        holderReview.bind(getItem(position))
    }

    inner class PoiReviewListItemViewHolder(private val binding: PoiReviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(review: Review) {
            with(binding) {
                poiReviewUserUsername.text = review.user.userName
                Glide.with(binding.root)
                    .load(review.user.photo)
                    .circleCrop()
                    .fitCenter()
                    .into(poiReviewUserAvatar)
                poiReviewContent.text = review.content
                poiReviewTitle.text = review.title
                poiReviewDate.text = review.dateCreated.formatMmDdYy(binding.root.context)
                poiReviewRating.rating = review.rating.toFloat()
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Review>() {
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean = oldItem == newItem
        }
    }
}
