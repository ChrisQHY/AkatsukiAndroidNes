package com.akatsuki.nes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.akatsuki.nes.databinding.VGalleryBinding
import com.akatsuki.nes.databinding.VGalleryRecyclerItemBinding
import com.akatsuki.nes.framework.base.EmulatorActivity
import com.akatsuki.nes.framework.ui.gamegallery.GameDescription
import com.akatsuki.nes.framework.ui.preferences.GeneralPreferenceActivity
import com.akatsuki.nes.framework.ui.preferences.GeneralPreferenceFragment
import java.io.File

class NesGalleryActivity:AppCompatActivity() {
    private val binding by lazy {VGalleryBinding.inflate(layoutInflater)}
    private var games = mutableListOf<GameDescription>()

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.tbGameGallery)

        binding.rvGameGallery.layoutManager = LinearLayoutManager(this)
        binding.rvGameGallery.adapter = GalleryAdapter()
    }

    override fun onResume() {
        super.onResume()
        loadGames()
    }

    override fun onCreateOptionsMenu(menu:Menu?):Boolean {
        menuInflater.inflate(R.menu.gallery_main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item:MenuItem):Boolean {
        val itemId = item.itemId
        when(itemId) {
            R.id.gallery_menu_pref -> {
                val i = Intent(this, GeneralPreferenceActivity::class.java)
                i.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT, GeneralPreferenceFragment::class.java.name)
                i.putExtra(PreferenceActivity.EXTRA_NO_HEADERS, true)
                startActivity(i)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadGames() {
        games.clear()
        File(filesDir.toString()).listFiles()?.let {files ->
            for(file in files) {
                if(file.name.uppercase().contains(".NES")) {
                    val game = GameDescription(file)
                    game.inserTime = System.currentTimeMillis()
                    games.add(game)
                }
            }
        }
        binding.rvGameGallery.adapter?.notifyDataSetChanged()
    }

    inner class GalleryAdapter:Adapter<GalleryAdapter.Holder>() {
        inner class Holder(val binding:VGalleryRecyclerItemBinding):ViewHolder(binding.root)

        override fun getItemCount() = games.size

        override fun onCreateViewHolder(parent:ViewGroup, viewType:Int) =
            Holder(VGalleryRecyclerItemBinding.inflate(layoutInflater, parent, false))

        override fun onBindViewHolder(holder:Holder, position:Int) {
            holder.binding.root.apply {
                text = games[position].name
                setOnClickListener {
                    Intent(this@NesGalleryActivity, NesEmulatorActivity::class.java).let {
                        it.putExtra(EmulatorActivity.EXTRA_GAME, games[position])
                        it.putExtra(EmulatorActivity.EXTRA_SLOT, 0)
                        it.putExtra(EmulatorActivity.EXTRA_FROM_GALLERY, true)
                        startActivity(it)
                    }
                }
            }
        }
    }
}