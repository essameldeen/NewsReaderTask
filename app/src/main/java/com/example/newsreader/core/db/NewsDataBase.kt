package com.example.newsreader.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsreader.data.entity.ArticleEntity
import com.example.newsreader.data.dataSource.local.NewsDao


@Database(entities = [ArticleEntity::class], version = 1)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}