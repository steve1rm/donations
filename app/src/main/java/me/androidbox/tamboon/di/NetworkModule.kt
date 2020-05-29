package me.androidbox.tamboon.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.androidbox.tamboon.BuildConfig
import me.androidbox.tamboon.R
import me.androidbox.tamboon.data.network.TamboonService
import me.androidbox.tamboon.data.request.RequestCharitiesImp
import me.androidbox.tamboon.data.request.RequestDonationImp
import me.androidbox.tamboon.domain.interactors.RequestCharities
import me.androidbox.tamboon.domain.interactors.RequestDonation
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class NetworkModule {

    @Reusable
    @Provides
    fun httpLoginInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()

        loggingInterceptor.level = if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        }
        else {
            HttpLoggingInterceptor.Level.NONE
        }

        return loggingInterceptor
    }

    @Reusable
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Named("BaseUrl")
    @Reusable
    @Provides
    fun provideBaseUrl(context: Context): String =
        context.getString(R.string.baseUrl)

    @Reusable
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Reusable
    @Provides
    fun provideTamboonService(retrofit: Retrofit): TamboonService =
        retrofit.create(TamboonService::class.java)

    @Reusable
    @Provides
    fun provideRequestCharities(tamboonService: TamboonService): RequestCharities =
        RequestCharitiesImp(tamboonService)

    @Reusable
    @Provides
    fun provideRequestDonation(tamboonService: TamboonService): RequestDonation =
        RequestDonationImp(tamboonService)
}
