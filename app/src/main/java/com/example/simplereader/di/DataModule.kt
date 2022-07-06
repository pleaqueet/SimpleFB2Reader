package com.example.simplereader.di

import android.content.Context
import androidx.room.Room
import com.example.simplereader.data.room.BooksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideBooksDatabase(@ApplicationContext applicationContext: Context) =
        Room.databaseBuilder(
            applicationContext,
            BooksDatabase::class.java,
            "books"
        ).build()

    @Singleton
    @Provides
    fun provideBookDao(db: BooksDatabase) = db.getBookDao()
}