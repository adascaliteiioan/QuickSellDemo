package com.example.quicksellapp.di

import android.app.Application
import androidx.room.Room
import com.example.quicksellapp.data.db.ProductDb
import com.example.quicksellapp.data.db.ProductsDao
import com.example.quicksellapp.repository.LanguageRepository
import com.example.quicksellapp.repository.QuickSellRepository
import com.example.quicksellapp.util.tools.prefs.PrefsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePrefsManager(application: Application): PrefsManager =
        PrefsManager(application)


    @Singleton
    @Provides
    fun provideLanguageRepository(
        prefsManager: PrefsManager
    ): LanguageRepository = LanguageRepository(prefsManager)

    @Singleton
    @Provides
    fun provideQuickSellRepo(): QuickSellRepository = QuickSellRepository()

    @Singleton
    @Provides
    fun provideProductDatabase(app: Application): ProductDb =
        Room.databaseBuilder(app, ProductDb::class.java, "products_db.db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideProductDao(productDb: ProductDb): ProductsDao = productDb.productDao()

}