package com.barcelona.cahya_submission_barcelona.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    var name: String = "",
    var description: String = "",
    var photo: String = "",
    var appearances: String = "",
    var goals: String = "",
    var assists: String = "",
    var overview: String = "",
    var posisi: String = ""
) : Parcelable