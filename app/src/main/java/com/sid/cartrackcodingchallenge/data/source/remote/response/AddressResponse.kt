package com.sid.cartrackcodingchallenge.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class AddressResponse(
        val street: String,
        val suite: String,
        val city: String,
        val zipcode: String,
        val geo: GeoResponse
) : Parcelable