package co.pratham.newslist.di.module

import android.util.Log
import co.pratham.newslist.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitModule {
    val HTTP_TAG = "APILog";
    @Provides
    @Singleton
    fun provideGSON() =
            GsonBuilder()
                    .setLenient()
                    .serializeNulls()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create()

    @Provides
    @Singleton
    fun provideHttpLogginInterceptor() =
            HttpLoggingInterceptor { message ->
                Log.d(HTTP_TAG, message)
            }.setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideRxFactory() =
            RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideGSONFactory(gson: Gson) =
            GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideOKHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
            OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .readTimeout(30,TimeUnit.SECONDS)
                    .connectTimeout(30,TimeUnit.SECONDS)
                    .build()
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,
                        gsonFactory: GsonConverterFactory,
                        rxFactory:RxJava2CallAdapterFactory) =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addCallAdapterFactory(rxFactory)
                    .addConverterFactory(gsonFactory)
                    .client(okHttpClient)
                    .build()
}