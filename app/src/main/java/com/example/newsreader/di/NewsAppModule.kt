package com.example.newsreader.di

import android.app.Application
import com.example.newsreader.data.manager.LocalUserManagerImpl
import com.example.newsreader.domain.manager.LocalUserManager
import com.example.newsreader.domain.useCase.ReadAppEntryUseCase
import com.example.newsreader.domain.useCase.SavingAppEntryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsAppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)
}


@Module
@InstallIn(ViewModelComponent::class)
object NewsViewModelModule {
    @Provides
    fun provideSaveAppEntryUseCase(localUserManager: LocalUserManager) =
        SavingAppEntryUseCase(localUserManager)

    @Provides
    fun provideReadAppEntryUseCase(localUserManager: LocalUserManager) =
       ReadAppEntryUseCase(localUserManager)


}

