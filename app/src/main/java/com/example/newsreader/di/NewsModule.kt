package com.example.newsreader.di

import android.app.Application
import androidx.room.Room
import com.example.newsreader.data.dataSource.local.NewsDao
import com.example.newsreader.data.dataSource.local.NewsDataBase
import com.example.newsreader.data.dataSource.remote.api.NewsApiService
import com.example.newsreader.data.manager.LocalUserManagerImpl
import com.example.newsreader.data.repository.NewsRepositoryImpl
import com.example.newsreader.domain.manager.LocalUserManager
import com.example.newsreader.domain.repository.NewsRepository
import com.example.newsreader.domain.useCase.BookMarkUseCase
import com.example.newsreader.domain.useCase.GetNewsByIdUseCase
import com.example.newsreader.domain.useCase.GetNewsUseCase
import com.example.newsreader.domain.useCase.UnBookMarkUseCase
import com.example.newsreader.utils.NewsConstant.BASE_URL
import com.example.newsreader.utils.NewsConstant.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsDomainModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideNewsDataBase(
        application: Application
    ): NewsDataBase {
        return Room.databaseBuilder(
            application,
            klass = NewsDataBase::class.java,
            name = NEWS_DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(newsDataBase: NewsDataBase): NewsDao {
        return newsDataBase.newsDao()
    }

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApiService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApiService: NewsApiService,
        newsDao: NewsDao,
        application: Application

    ): NewsRepository {
        return NewsRepositoryImpl(newsApiService, newsDao, application)

    }
}

@Module
@InstallIn(ViewModelComponent::class)
class NewsModuleDomain {

    @Provides
    fun provideGetNewsUseCase(newsRepository: NewsRepository): GetNewsUseCase {
        return GetNewsUseCase(newsRepository)
    }

    @Provides
    fun provideGetNewsByIdUseCase(newsRepository: NewsRepository): GetNewsByIdUseCase {
        return GetNewsByIdUseCase(newsRepository)
    }

    @Provides
    fun provideBookMarkNewsUseCase(newsRepository: NewsRepository): BookMarkUseCase {
        return BookMarkUseCase(newsRepository)
    }

    @Provides
    fun provideUnBookMarkNewsUseCase(newsRepository: NewsRepository): UnBookMarkUseCase {
        return UnBookMarkUseCase(newsRepository)
    }
}