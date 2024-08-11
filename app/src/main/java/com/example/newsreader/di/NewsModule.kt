package com.example.newsreader.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.newsreader.data.dataSource.local.NewsDao
import com.example.newsreader.core.db.NewsDataBase
import com.example.newsreader.data.dataSource.remote.api.NewsApiService
import com.example.newsreader.core.manager.InternetManager
import com.example.newsreader.core.manager.InternetManagerImpl
import com.example.newsreader.data.dataSource.local.LocalUserManagerImpl
import com.example.newsreader.data.repository.NewsRepositoryImpl
import com.example.newsreader.domain.manager.LocalUserManager
import com.example.newsreader.domain.repository.NewsRepository
import com.example.newsreader.core.utils.NewsConstant.BASE_URL
import com.example.newsreader.core.utils.NewsConstant.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsDomainModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(@ApplicationContext applicationContext: Context): LocalUserManager =
        LocalUserManagerImpl(applicationContext)

    @Provides
    @Singleton
    fun provideInternetManager(@ApplicationContext applicationContext: Context): InternetManager =
        InternetManagerImpl(applicationContext)


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
        internetManager: InternetManager

    ): NewsRepository {
        return NewsRepositoryImpl(newsApiService, newsDao, internetManager)

    }
}
