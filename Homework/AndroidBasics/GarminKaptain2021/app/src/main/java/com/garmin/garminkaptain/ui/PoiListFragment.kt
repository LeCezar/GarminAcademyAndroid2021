package com.garmin.garminkaptain.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.garmin.garminkaptain.R
import com.garmin.garminkaptain.data.PointOfInterest
import com.garmin.garminkaptain.data.poiList
import com.garmin.garminkaptain.viewModel.PoiViewModel

class PoiListFragment : Fragment(R.layout.poi_list_fragment) {

    inner class PoiListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameView = itemView.findViewById<TextView>(R.id.poi_item_name_view)
        private val typeView = itemView.findViewById<TextView>(R.id.poi_item_type_view)

        fun bind(poi: PointOfInterest) {
            nameView.text = poi.name
            typeView.text = poi.poiType
            itemView.setOnClickListener {
                findNavController().navigate(
                    PoiListFragmentDirections.actionPoiListFragmentToPoiDetailsFragment(poi.id)
                )
            }
        }
    }

    inner class PoiListAdapter : RecyclerView.Adapter<PoiListItemViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoiListItemViewHolder {
            return PoiListItemViewHolder(
                layoutInflater.inflate(R.layout.poi_list_item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: PoiListItemViewHolder, position: Int) {
            pointsOfInterest.getOrNull(position)?.let {
                holder.bind(it)
            }
        }

        override fun getItemCount(): Int = pointsOfInterest.size
    }

    private var pointsOfInterest = listOf<PointOfInterest>()
    private var adapter = PoiListAdapter()
    private val viewModel: PoiViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<RecyclerView>(R.id.poi_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = this@PoiListFragment.adapter
        }

        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeToRefresh)
        swipeRefreshLayout.setOnRefreshListener { viewModel.loadPoiList() }

        viewModel.getLoading()
            .observe(viewLifecycleOwner, { swipeRefreshLayout.isRefreshing = it })

        viewModel.getPoiList().observe(viewLifecycleOwner, {
            it?.let {
                pointsOfInterest = it
                adapter.notifyDataSetChanged()
            }
        })

    }

}
