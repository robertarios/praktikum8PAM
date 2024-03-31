package com.ifs21024.tantanganpraktikum8

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21024.tantanganpraktikum8.databinding.FragmentChatsBinding

class ChatsFragment : Fragment() {
    private lateinit var binding: FragmentChatsBinding
    private lateinit var chatsAdapter: ListChatsAdapter // Anda perlu membuat adapter untuk RecyclerView
    private val dataChats = ArrayList<Chats>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Atur RecyclerView
        binding.rvChats.setHasFixedSize(true) // Set hasFixedSize menjadi true

        // Muat data ke RecyclerView
        dataChats.addAll(getListChats())
        chatsAdapter = ListChatsAdapter(dataChats) // Inisialisasi adapter dengan data
        binding.rvChats.adapter = chatsAdapter // Set adapter RecyclerView

        showRecyclerList()
    }

    private fun getListChats(): ArrayList<Chats> {
        val dataImage = resources.obtainTypedArray(R.array.profile_image)
        val dataName = resources.getStringArray(R.array.profile_name)
        val dataMessage = resources.getStringArray(R.array.message)

        val listChats = ArrayList<Chats>()
        for (i in dataName.indices) {
            val chats = Chats(dataName[i], dataImage.getResourceId(i, -1), dataMessage[i])
            listChats.add(chats)
        }

        // Panggil recycle() setelah selesai menggunakan TypedArray
        dataImage.recycle()

        return listChats
    }

    private fun showRecyclerList() {
        val layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 2)
        } else {
            LinearLayoutManager(requireContext())
        }
        binding.rvChats.layoutManager = layoutManager
    }

    companion object {
        const val EXTRA_MESSAGE = "extra_message"
    }
}
