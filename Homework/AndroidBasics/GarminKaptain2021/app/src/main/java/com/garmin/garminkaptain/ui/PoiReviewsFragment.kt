package com.garmin.garminkaptain.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.garmin.garminkaptain.R
import com.garmin.garminkaptain.adapters.PoiReviewListAdapter
import com.garmin.garminkaptain.data.poiList
import com.garmin.garminkaptain.databinding.PoiReviewsFragmentBinding

class PoiReviewsFragment : Fragment(R.layout.poi_reviews_fragment) {

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

        val poi = poiList.find { it.id == args.poiId }
        binding.poiReviewsFragmentTitle.text = getString(R.string.label_reviews_screen_title, poi?.name)
        binding.poiReviewsList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = PoiReviewListAdapter(poi?.reviewsSummary?.reviews ?: listOf())

        }
    }
}
