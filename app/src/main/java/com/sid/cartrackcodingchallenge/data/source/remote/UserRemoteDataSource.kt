package com.sid.cartrackcodingchallenge.data.source.remote

import android.content.Context
import android.util.Log
import com.sid.cartrackcodingchallenge.BuildConfig
import com.sid.cartrackcodingchallenge.data.source.remote.action.OnUsersResponse
import com.sid.cartrackcodingchallenge.data.source.remote.response.NetworkErrors
import com.sid.cartrackcodingchallenge.data.source.remote.response.UserResponse
import com.sid.cartrackcodingchallenge.utils.ErrorCodes.Companion.AUTHENTICATION_ERROR
import com.sid.cartrackcodingchallenge.utils.ErrorCodes.Companion.INTERNAL_SERVER_ERROR
import com.sid.cartrackcodingchallenge.utils.NetworkUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.net.ConnectException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSource @Inject
constructor(internal var context: Context) {

    private val apiInterface: ApiInterface
    private val retrofit: Retrofit

    init {
        // use 10MB cache
        val cacheSize = 10 * 1024 * 1024
        val httpCacheDirectory = File(context.cacheDir, "responses")
        val cache = Cache(httpCacheDirectory, cacheSize.toLong())

        val client = OkHttpClient.Builder().cache(cache).addNetworkInterceptor { chain ->
            val response = chain.proceed(chain.request())
            val maxAge = 60 // read from cache for 60 seconds even if there is internet connection
            response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("Pragma")
                .build()
        }.addInterceptor { chain ->
            var request = chain.request()
            if (!NetworkUtils.checkInternetConnection(context)) {
                val maxStale = 60 * 60 * 24 * 30 // Offline cache available for 30 days
                request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader("Pragma")
                    .build()
            }
            chain.proceed(request)
        }.build()

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        this.apiInterface = retrofit.create(ApiInterface::class.java)
    }

    fun getUsers(onUsersResponse: OnUsersResponse) {
        val apiInterfaceClubCompanies = apiInterface.getUsers()
        apiInterfaceClubCompanies.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<UserResponse>>() {

                override fun onSuccess(userResponseList: List<UserResponse>) {
                    onUsersResponse.onSuccessUsersResponse(userResponseList)
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "onError: " + e.message, e)
                    onUsersResponse.onErrorResponse(handleErrors(e))
                }
            })
    }

    fun handleErrors(error: Throwable): String {
        if (error is HttpException) {
            val body = error.response().errorBody()
            return handleError(body!!)
        } else return if (error is ConnectException) {
            "Connection error"
        } else {
            "Error occurred"
        }
    }

    private fun handleError(errorBody: ResponseBody): String {
        val errorConverter = retrofit.responseBodyConverter<NetworkErrors>(NetworkErrors::class.java, arrayOfNulls(0))
        var networkErrors: NetworkErrors? = null
        try {
            networkErrors = errorConverter.convert(errorBody) // Convert the error body into custom Error type.
            Log.e("Error occurred", networkErrors.status.toString() + " " + networkErrors!!.message)

            when (networkErrors.status) {
                AUTHENTICATION_ERROR -> return "Error occurred while authenticating"
                INTERNAL_SERVER_ERROR -> return "Internal server error"
                else -> return "Unexpected error occurred"
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        return networkErrors?.message!!
    }

    companion object {
        private val TAG = "RemoteDataSource"
    }
}