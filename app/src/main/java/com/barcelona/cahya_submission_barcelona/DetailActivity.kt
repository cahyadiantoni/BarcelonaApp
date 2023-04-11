package com.barcelona.cahya_submission_barcelona

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity: AppCompatActivity(), View.OnClickListener {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        val actionbar = supportActionBar
        actionbar!!.title = "Detail Player"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val tvSetName: TextView = findViewById(R.id.tv_set_name)
        val imgSetPhoto: ImageView = findViewById(R.id.img_item_photo)
        val tvSetDesc: TextView = findViewById(R.id.tv_set_descriptions)
        val tvOverview: TextView = findViewById(R.id.tv_set_overview)
        val tvApp: TextView = findViewById(R.id.tv_set_appearances)
        val tvGoal: TextView = findViewById(R.id.tv_set_goals)
        val tvAssist: TextView = findViewById(R.id.tv_set_assists)
        val tvPosisi: TextView = findViewById(R.id.tv_set_posisi)

        val tName  = intent.getStringExtra(EXTRA_NAME)
        val tImg = intent.getStringExtra(EXTRA_PHOTO)
        val tDesc = intent.getStringExtra(EXTRA_DESCRIPTION)
        val tOverview = intent.getStringExtra(EXTRA_OVERVIEW)
        val tApp = intent.getStringExtra(EXTRA_APPEARANCES)
        val tGoal = intent.getStringExtra(EXTRA_GOALS)
        val tAssist = intent.getStringExtra(EXTRA_ASSISTS)
        val tPosisi = intent.getStringExtra(EXTRA_POSISI)

        tvSetName.text = tName
        Glide.with(this)
            .load(tImg)
            .apply(RequestOptions())
            .into(imgSetPhoto)
        tvSetDesc.text = tDesc
        tvOverview.text = tOverview
        tvApp.text = tApp
        tvGoal.text = tGoal
        tvAssist.text = tAssist
        tvPosisi.text = tPosisi

        val btnShare: FloatingActionButton = findViewById(R.id.action_share)
        btnShare.setOnClickListener(this)
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_OVERVIEW = "extra_overview"
        const val EXTRA_APPEARANCES = "extra_appearances"
        const val EXTRA_GOALS = "extra_goals"
        const val EXTRA_ASSISTS = "extra_assist"
        const val EXTRA_POSISI = "extra_posisi"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.action_share -> {
                val tName  = intent.getStringExtra(EXTRA_NAME)
                val tPosisi  = intent.getStringExtra(EXTRA_POSISI)
                val tDesc  = intent.getStringExtra(EXTRA_DESCRIPTION)
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_TEXT, ("$tName is one of the FC Barcelona players who has a position as $tPosisi. \n $tDesc."))
                intent.type = "text/plain"
                startActivity(Intent.createChooser(intent, "Send To"))
            }
        }
    }
}

