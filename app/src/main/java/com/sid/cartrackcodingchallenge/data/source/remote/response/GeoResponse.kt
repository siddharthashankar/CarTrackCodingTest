package com.sid.cartrackcodingchallenge.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class GeoResponse(val lat: Double, val lng: Double) : Parcelable