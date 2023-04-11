package com.barcelona.cahya_submission_barcelona

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barcelona.cahya_submission_barcelona.adapter.ListPlayerAdapter
import com.barcelona.cahya_submission_barcelona.data.PlayerData
import com.barcelona.cahya_submission_barcelona.model.Player

class MainActivity : AppCompatActivity() {
    private var backPressedTime: Long = 0
    private lateinit var backToast: Toast
    private lateinit var rvPlayers: RecyclerView
    private var list: ArrayList<Player> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref: SharedPreferences = this.getSharedPreferences("PREF", 0)
        val editor = pref.edit()
        val firstRun = pref.getBoolean("firstRun", true)
        if (firstRun) {
            Log.i("onCreate: ", "first time")
            editor.putBoolean("firstRun", false)
            editor.apply()
            Toast.makeText(this, "Aplikasi ini membutuhkan koneksi internet", Toast.LENGTH_LONG).show()
        }

        rvPlayers = findViewById(R.id.rv_players)
        rvPlayers.setHasFixedSize(true)

        list.addAll(PlayerData.listData)
        showRecyclerList()
    }

    private fun showDialog(){
        val view = View.inflate(this@MainActivity, R.layout.layout_info, null)

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val btn = view.findViewById<Button>(R.id.btnOk)
        btn.setOnClickListener {
            dialog.dismiss()
        }
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val displayWidth = displayMetrics.widthPixels
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window!!.attributes)
        val dialogWindowWidth = (displayWidth * 0.96f).toInt()
        layoutParams.width = dialogWindowWidth
        dialog.window!!.attributes = layoutParams
    }

    private fun showRecyclerList() {
        rvPlayers.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListPlayerAdapter(list)
        rvPlayers.adapter = listHeroAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvPlayers.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvPlayers.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.action_profile -> {
                val moveIntent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.action_about -> {
                showDialog()
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        backToast = Toast.makeText(this, "Klik Sekali lagi untuk keluar", Toast.LENGTH_SHORT)
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel()
            super.onBackPressed()
            return
        } else {
            backToast.show()
        }
        backPressedTime = System.currentTimeMillis()

    }
}