package com.example.newsreader.data.dataSource.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ArticleEntity::class], version = 1)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}