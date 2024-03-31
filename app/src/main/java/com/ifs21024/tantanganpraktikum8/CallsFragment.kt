package com.ifs21024.tantanganpraktikum8

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21024.tantanganpraktikum8.databinding.FragmentCallsBinding

class CallsFragment : Fragment() {
    private lateinit var binding: FragmentCallsBinding
    private lateinit var callsAdapter: ListCallsAdapter // Anda perlu membuat adapter untuk RecyclerView
    private val dataCalls = ArrayList<Calls>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCallsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Atur RecyclerView
        binding.rvCalls.setHasFixedSize(true) // Set hasFixedSize menjadi true

        // Muat data ke RecyclerView
        dataCalls.addAll(getListCalls())
        callsAdapter = ListCallsAdapter(dataCalls) // Inisialisasi adapter dengan data
        binding.rvCalls.adapter = callsAdapter // Set adapter RecyclerView

        showRecyclerList()
    }

    private fun getListCalls(): ArrayList<Calls> {
        val dataImage = resources.obtainTypedArray(R.array.calls_image)
        val dataName = resources.getStringArray(R.array.calls_name)
        val dataTime = resources.getStringArray(R.array.calls_time)

        val listCalls = ArrayList<Calls>()
        for (i in dataName.indices) {
            val calls = Calls(dataName[i], dataImage.getResourceId(i, -1), dataTime[i])
            listCalls.add(calls)
        }

        // Panggil recycle() setelah selesai menggunakan TypedArray
        dataImage.recycle()

        return listCalls
    }

    private fun showRecyclerList() {
        val layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 2)
        } else {
            LinearLayoutManager(requireContext())
        }
        binding.rvCalls.layoutManager = layoutManager
    }

    companion object {
        const val EXTRA_MESSAGE = "extra_message"
    }
}
