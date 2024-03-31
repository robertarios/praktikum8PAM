package com.ifs21024.tantanganpraktikum8

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21024.tantanganpraktikum8.databinding.FragmentUpdatesBinding

class UpdatesFragment : Fragment() {
    private lateinit var binding: FragmentUpdatesBinding
    private lateinit var updatesAdapter: ListUpdatesAdapter
    private val dataUpdates = ArrayList<Updates>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdatesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Atur RecyclerView
        binding.rvUpdates.setHasFixedSize(true) // Set hasFixedSize menjadi true

        // Muat data ke RecyclerView
        dataUpdates.addAll(getListUpdates())
        updatesAdapter = ListUpdatesAdapter(dataUpdates) // Inisialisasi adapter dengan data
        binding.rvUpdates.adapter = updatesAdapter // Set adapter RecyclerView

        showRecyclerList()
    }

    private fun getListUpdates(): ArrayList<Updates> {
        val dataImage = resources.obtainTypedArray(R.array.updates_image)
        val dataName = resources.getStringArray(R.array.updates_name)
        val dataTime = resources.getStringArray(R.array.updates_time)

        val listUpdates = ArrayList<Updates>()
        for (i in dataName.indices) {
            val updates = Updates(dataName[i], dataImage.getResourceId(i, -1), dataTime[i])
            listUpdates.add(updates)
        }

        // Panggil recycle() setelah selesai menggunakan TypedArray
        dataImage.recycle()

        return listUpdates
    }

    private fun showRecyclerList() {
        val layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 2)
        } else {
            LinearLayoutManager(requireContext())
        }
        binding.rvUpdates.layoutManager = layoutManager
    }

    companion object {
        const val EXTRA_MESSAGE = "extra_message"
    }
}
