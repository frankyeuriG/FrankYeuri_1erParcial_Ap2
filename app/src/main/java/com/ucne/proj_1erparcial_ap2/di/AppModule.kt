package com.ucne.proj_1erparcial_ap2.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ucne.proj_1erparcial_ap2.data.local.PrestamoDb
import com.ucne.proj_1erparcial_ap2.data.remote.TePrestoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // TODO: Inyectar la base de datos 
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): PrestamoDb {
        return Room.databaseBuilder(
            context,
            PrestamoDb::class.java,
            "Prestamo.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    // TODO: Inyectar el DAO 
    @Singleton
    @Provides
    fun providesPrestamoDao(db: PrestamoDb) = db.prestamoDao

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun providesTePrestoApi(moshi: Moshi): TePrestoApi {
        return Retrofit.Builder()
            .baseUrl("https://teprestpapi4.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TePrestoApi::class.java)
    }
}