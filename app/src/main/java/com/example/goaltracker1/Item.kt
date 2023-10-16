package com.example.goaltracker1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Item(
    val id :Int,
    val title: String,
    val expiration: String,
    val description: String,
    val state: String
) : Parcelable
