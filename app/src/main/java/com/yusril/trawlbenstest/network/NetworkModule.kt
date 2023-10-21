package com.yusril.trawlbenstest.network


import com.yusril.trawlbenstest.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(client : OkHttpClient):Retrofit{
        return Retrofit.Builder().apply {
            baseUrl("https://pokeapi.co/api/v2/")
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build()
    }

    @Singleton
    @Provides
    fun provideHttpClient():OkHttpClient{
        return OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG){
                    HttpLoggingInterceptor.Level.BODY
                }else{
                    HttpLoggingInterceptor.Level.NONE
                }
            }).build()
    }
}