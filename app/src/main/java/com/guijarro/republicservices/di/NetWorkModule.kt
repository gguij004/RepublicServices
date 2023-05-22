package com.guijarro.republicservices.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.guijarro.republicservices.data.local.room_db.DriversAndRoutesDatabase
import com.guijarro.republicservices.data.local.room_db.LocalDriversAndRoutesImpl
import com.guijarro.republicservices.data.remote.api.DrivesAndRoutesApi
import com.guijarro.republicservices.data.remote.api.DrivesAndRoutesApi.Companion.BASE_URL
import com.guijarro.republicservices.data.remote.repository_impl.RemoteDrivesAndRoutesRepositoryImpl
import com.guijarro.republicservices.domain.repository.LocalDriversAndRoutsRepository
import com.guijarro.republicservices.domain.repository.RemoteDrivesAndRoutRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Dagger Hilt module that provides networking and data repository dependencies.
 *
 * The module includes the following functionality:
 * - Provides the [DrivesAndRoutesApi] instance for network communication.
 * - Provides the [Gson] instance for JSON serialization/deserialization.
 * - Provides the [OkHttpClient] instance for making HTTP requests.
 * - Provides the [HttpLoggingInterceptor] instance for logging network requests and responses.
 * - Provides the [RemoteDrivesAndRoutRepository] implementation for remote data repository.
 * - Provides the [CoroutineDispatcher] instance for managing coroutines.
 * - Provides the [DriversAndRoutesDatabase] instance for local data storage.
 * - Provides the [LocalDriversAndRoutsRepository] implementation for local data repository.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {

    @Provides
    @Singleton
    fun providesDriversAndRoutsApi(
        gson: Gson,
        okHttpClient: OkHttpClient,
    ): DrivesAndRoutesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(DrivesAndRoutesApi::class.java)
    }

    @Provides
    @Singleton
    fun providesGson(): Gson = Gson()

    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideDriversAndRoutesRepository(api: DrivesAndRoutesApi): RemoteDrivesAndRoutRepository {
        return RemoteDrivesAndRoutesRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideDriversAndRoutesDatabase(app: Application): DriversAndRoutesDatabase {

        return Room.databaseBuilder(
            app,
            DriversAndRoutesDatabase::class.java,
            DriversAndRoutesDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesLocalRepository(db: DriversAndRoutesDatabase): LocalDriversAndRoutsRepository {
        return LocalDriversAndRoutesImpl(db.driversAndRoutesDao)
    }

}