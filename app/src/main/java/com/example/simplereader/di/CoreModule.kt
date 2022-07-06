package com.example.simplereader.di

import android.content.Context
import com.example.simplereader.core.FB2BookParser
import com.example.simplereader.core.FileFormatter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Singleton
    @Provides
    fun provideFB2Parser(@ApplicationContext applicationContext: Context, fileFormatter: FileFormatter) =
        FB2BookParser(applicationContext, fileFormatter)

    @Singleton
    @Provides
    fun provideFileFormatter() = FileFormatter()
}