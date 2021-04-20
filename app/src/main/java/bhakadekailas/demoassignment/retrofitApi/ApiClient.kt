import android.util.Log
import bhakadekailas.demoassignment.util.GlobalConstant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {
        val TAG = ApiClient::class.java.simpleName

        private val httpClient = OkHttpClient.Builder()
        private val builder = Retrofit.Builder()
            .baseUrl(GlobalConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())


        fun <T> createRetrofitService(clazz: Class<T>, baseUrl: String): T {
            Log.e(TAG, "createRetrofitService: ")
            val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(clazz)
        }

        fun createService(serviceClass: Class<ApiInterface>): ApiInterface {
            Log.e(TAG, "createService: ")
            val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

            val retrofit = builder.client(httpClient.build())
                .client(okHttpClient)
                .build()
            return retrofit.create(serviceClass)
        }
    }
}