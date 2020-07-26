package com.sid.cartrackcodingchallenge.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CompanyResponse(val name: String, val catchPhrase: String, val bs: String) : Parcelable