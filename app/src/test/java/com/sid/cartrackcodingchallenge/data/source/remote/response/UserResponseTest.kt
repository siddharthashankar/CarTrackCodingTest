package com.sid.cartrackcodingchallenge.data.source.remote.response

import org.junit.Assert.assertEquals
import org.junit.Test

class UserResponseTest {

    private lateinit var userResponse: UserResponse

    private val geoResponse = GeoResponse(-37.3159, 81.1496)
    private val addressResponse = AddressResponse("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874", geoResponse)
    private val companyResponse =
        CompanyResponse("Romaguera-Crona", "Multi-layered client-server neural-net", "harness real-time e-markets")


    @Test
    fun getGetPhoneNumber_WhenExtensionAvailable() {

        val phone = "1-770-736-8031 x56442"

        userResponse = UserResponse(
            1,
            "Leanne Graham",
            "Bret",
            "Sincere@april.biz",
            addressResponse,
            phone,
            "hildegard.org",
            companyResponse
        )

        assertEquals("1-770-736-8031", userResponse.getPhoneNumber)
    }

    @Test
    fun getGetPhoneNumber_WhenExtensionNotAvailable() {

        val phone = "1-770-736-8031"

        userResponse = UserResponse(
            1,
            "Leanne Graham",
            "Bret",
            "Sincere@april.biz",
            addressResponse,
            phone,
            "hildegard.org",
            companyResponse
        )

        assertEquals("1-770-736-8031", userResponse.getPhoneNumber)

    }

    @Test
    fun getGetPhoneNumber_WhenPhoneNumberNotAvailable() {
        val phone = ""

        userResponse = UserResponse(
            1,
            "Leanne Graham",
            "Bret",
            "Sincere@april.biz",
            addressResponse,
            phone,
            "hildegard.org",
            companyResponse
        )

        assertEquals("N/A", userResponse.getPhoneNumber)
    }

    @Test
    fun getGetPhoneNumberExtension_WhenExtensionAvailable() {
        val phone = "1-770-736-8031 x56442"

        userResponse = UserResponse(
            1,
            "Leanne Graham",
            "Bret",
            "Sincere@april.biz",
            addressResponse,
            phone,
            "hildegard.org",
            companyResponse
        )

        assertEquals("x56442", userResponse.getPhoneNumberExtension)
    }

    @Test
    fun getGetPhoneNumberExtension_WhenExtensionNotAvailable() {
        val phone = "1-770-736-8031"

        userResponse = UserResponse(
            1,
            "Leanne Graham",
            "Bret",
            "Sincere@april.biz",
            addressResponse,
            phone,
            "hildegard.org",
            companyResponse
        )

        assertEquals("N/A", userResponse.getPhoneNumberExtension)
    }

}