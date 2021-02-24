package com.garmin.garminkaptain.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.garmin.garminkaptain.R
import com.garmin.garminkaptain.adapters.PoiReviewListAdapter
import com.garmin.garminkaptain.data.Review
import com.garmin.garminkaptain.databinding.PoiReviewsFragmentBinding
import com.garmin.garminkaptain.viewModel.PoiViewModel

class PoiReviewsFragment : Fragment(R.layout.poi_reviews_fragment) {

    private val poiViewModel: PoiViewModel by activityViewModels()
    private lateinit var binding: PoiReviewsFragmentBinding
    private val args: PoiReviewsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PoiReviewsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView()

        poiViewModel.getLoading().observe(
            viewLifecycleOwner,
            {
                binding.poiReviewsProgress.visibility = if (it) VISIBLE else GONE
            })

        poiViewModel.getPoi(args.poiId).observe(viewLifecycleOwner, {
            binding.poiReviewsGroup.visibility = VISIBLE
            binding.poiReviewsFragmentTitle.text = getString(R.string.label_reviews_screen_title, it?.name)
            giveItemsToRecyclerView(it?.reviewsSummary?.reviews ?: listOf())
        })
    }

    private fun setUpRecyclerView() {
        binding.poiReviewsList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = PoiReviewListAdapter()
        }
    }

    private fun giveItemsToRecyclerView(items: List<Review>) {
        (binding.poiReviewsList.adapter as PoiReviewListAdapter).submitList(items)
    }
}
