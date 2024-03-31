package com.ifs21024.tantanganpraktikum8

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.ifs21024.tantanganpraktikum8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView() // Panggil metode setupView() untuk melakukan konfigurasi awal
        loadFragment(FLAG_FRAGMENT_CHATS) // Memuat fragmen chats saat aktivitas dibuat
        setupAction()
    }

    private fun setupView() {
        // Konfigurasi tampilan di sini
        binding.inAppBar.toolbar.overflowIcon =
            ContextCompat.getDrawable(this, R.drawable.ic_more_vert)

        loadFragment(FLAG_FRAGMENT_CHATS) // Memuat fragmen chats saat aktivitas dibuat
    }


    private fun setupAction() {
        binding.inAppBar.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.inAppBar.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_group -> {
                    loadFragment(FLAG_FRAGMENT_CHATS, "Memilih menu New Group!")
                    true
                }
                R.id.action_broadcast -> {
                    loadFragment(FLAG_FRAGMENT_CHATS, "Memilih menu New Broadcast!")
                    true
                }
                R.id.action_linked -> {
                    loadFragment(FLAG_FRAGMENT_CHATS, "Memilih menu Linked Devices!")
                    true
                }
                R.id.action_starred -> {
                    loadFragment(FLAG_FRAGMENT_CHATS, "Memilih menu Starred Messages!")
                    true
                }
                R.id.action_settings -> {
                    loadFragment(FLAG_FRAGMENT_CHATS, "Memilih menu Settings!")
                    true
                }

                else -> true
            }
        }

        binding.inAppBar.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_chats -> {
                    loadFragment(FLAG_FRAGMENT_CHATS)
                    true
                }
                R.id.navigation_updates -> {
                    loadFragment(FLAG_FRAGMENT_UPDATES)
                    true
                }
                R.id.navigation_community -> {
                    loadFragment(FLAG_FRAGMENT_COMMUNITY)
                    true
                }
                R.id.navigation_calls -> {
                    loadFragment(FLAG_FRAGMENT_CALLS)
                    true
                }

                else -> true
            }
        }
    }

    private fun loadFragment(flag: String, message: String? = null) {
        val fragmentManager = supportFragmentManager
        val fragmentContainerId =
            binding.inAppBar.inContentMain.frameContainer.id

        when (flag) {
            FLAG_FRAGMENT_CHATS -> {
                binding.inAppBar.bottomNavView
                    .menu
                    .findItem(R.id.navigation_chats)
                    .setChecked(true)

                val chatsFragment = ChatsFragment()

                val bundle = Bundle().apply {
                    this.putString(
                        ChatsFragment.EXTRA_MESSAGE,
                        message ?: "Belum ada menu yang dipilih!"
                    )
                }
                chatsFragment.arguments = bundle

                fragmentManager
                    .beginTransaction()
                    .replace(
                        fragmentContainerId,
                        chatsFragment,
                        ChatsFragment::class.java.simpleName
                    )
                    .commit()
            }

            FLAG_FRAGMENT_UPDATES -> {
                val updatesFragment = UpdatesFragment()
                val fragment = fragmentManager
                    .findFragmentByTag(UpdatesFragment::class.java.simpleName)

                if (fragment !is UpdatesFragment) {
                    fragmentManager
                        .beginTransaction()
                        .replace(
                            fragmentContainerId,
                            updatesFragment,
                            UpdatesFragment::class.java.simpleName
                        )
                        .commit()
                }
            }

            FLAG_FRAGMENT_COMMUNITY -> {
                val communityFragment = CommunityFragment()
                val fragment = fragmentManager
                    .findFragmentByTag(CommunityFragment::class.java.simpleName)

                if (fragment !is CommunityFragment) {
                    fragmentManager
                        .beginTransaction()
                        .replace(
                            fragmentContainerId,
                            communityFragment,
                            CommunityFragment::class.java.simpleName
                        )
                        .commit()
                }
            }

            FLAG_FRAGMENT_CALLS -> {
                val callsFragment = CallsFragment()
                val fragment = fragmentManager
                    .findFragmentByTag(CallsFragment::class.java.simpleName)

                if (fragment !is CallsFragment) {
                    fragmentManager
                        .beginTransaction()
                        .replace(
                            fragmentContainerId,
                            callsFragment,
                            CallsFragment::class.java.simpleName
                        )
                        .commit()
                }
            }
        }
    }

    companion object {
        const val FLAG_FRAGMENT_CHATS = "fragment_chats"
        const val FLAG_FRAGMENT_UPDATES = "fragment_updates"
        const val FLAG_FRAGMENT_COMMUNITY = "fragment_community"
        const val FLAG_FRAGMENT_CALLS = "fragment_calls"
    }

}