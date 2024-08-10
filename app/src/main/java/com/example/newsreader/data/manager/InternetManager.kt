package com.example.newsreader.data.manager

fun interface InternetManager {
    fun checkNetwork(): Boolean
}