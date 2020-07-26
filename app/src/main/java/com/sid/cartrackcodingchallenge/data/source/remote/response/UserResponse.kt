package com.sid.cartrackcodingchallenge.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserResponse(
        val id: Int,
        val name: String,
        val username: String,
        val email: String,
        val address: AddressResponse,
        private val phone: String,
        val website: String,
        val company: CompanyResponse
) : Parcelable {

    val getPhoneNumber: String
        get() {
            var phoneList = phone.split(" ")
            var phoneNumber = phoneList.getOrNull(0)

            if (phoneNumber == null || phoneNumber.isBlank()) phoneNumber = "N/A"

            return phoneNumber

        }

    val getPhoneNumberExtension: String
        get() {
            var phoneList = phone.split(" ")
            var extensionNumber = phoneList.getOrNull(1)

            if (extensionNumber == null || extensionNumber.isBlank()) extensionNumber = "N/A"

            return extensionNumber

        }

}