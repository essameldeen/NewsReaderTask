package com.example.newsreader.data.repository

import com.example.newsreader.data.entity.ArticleEntity
import com.example.newsreader.data.dataSource.local.NewsDao
import com.example.newsreader.data.dataSource.remote.api.NewsApiService
import com.example.newsreader.data.dataSource.remote.response.ArticleDto
import com.example.newsreader.data.dataSource.remote.response.NewsResponse
import com.example.newsreader.data.dataSource.remote.response.Source
import com.example.newsreader.core.manager.InternetManager
import com.example.newsreader.data.mapper.toArticleEntity
import com.example.newsreader.data.mapper.toDomainModel
import com.example.newsreader.data.mapper.toEntity
import com.example.newsreader.domain.models.Article
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class NewsRepositoryImplTest {

    @Mock
    private lateinit var apiService: NewsApiService

    @Mock
    private lateinit var newsDao: NewsDao

    @Mock
    private lateinit var internetManager: InternetManager

    private lateinit var repository: NewsRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = NewsRepositoryImpl(apiService, newsDao, internetManager)
    }

    @Test
    fun `test getNews from api with have network connection and save to database`() = runTest {
        whenever(internetManager.checkNetwork()).thenReturn(true)
        val response = NewsResponse(
            status = "s",
            articles = listOf(
                ArticleDto(
                    "author",
                    "title",
                    "description",
                    "url",
                    Source("12", "source"),
                    "publishedAt",
                    "content",
                    "urlToImage"
                )
            ),
            totalResults = 1
        )
        whenever(apiService.getNews("US")).thenReturn(response)

        val result = repository.getNews("US")

        verify(newsDao).insertArticles(response.articles.map { it.toEntity() })

        assertEquals(response.articles.map { it.toDomainModel() }, result)
    }

    @Test
    fun `test getNews from api with not have network connection and return data from database`() =
        runTest {
            whenever(internetManager.checkNetwork()).thenReturn(false)
            val articles = listOf(
                ArticleEntity(
                    1,
                    "title",
                    "description",
                    "url",
                    "source",
                    "publishedAt",
                    "content",
                    "urlToImage",
                    source = "source",
                    isBookmarked = false
                )
            )
            whenever(newsDao.getArticles()).thenReturn(articles)

            val result = repository.getNews("US")


            assertEquals(articles.map { it.toDomainModel() }, result)
        }

    @Test
    fun `test getArticleDetailsById should return the correct article`() = runTest {
        val articleId = "urlToImage"
        val articleEntity = ArticleEntity(
            1,
            "title",
            "description",
            "url",
            "source",
            "urlToImage",
            "content",
            "content",
            source = "source",
            isBookmarked = false
        )
        whenever(newsDao.getArticlesById(articleId)).thenReturn(articleEntity)

        val result = repository.getArticleDetailsById(articleId)
        assertEquals(articleEntity.toDomainModel(), result)
    }

    @Test
    fun `test searchForNews should return articles from API when network is available`() = runTest {

        val searchQuery = "test"
        val article = listOf (Article(
            "author",
            "content",
            "description",
            "publishedAt",
            "source",
            "title",
            "url",
            "urlToImage",
            null,
            isBookmarked = false
        ))
        val response = NewsResponse(
            status = "s",
            articles = listOf(
                ArticleDto(
                    "author",
                    "content",
                    "description",
                    "publishedAt",
                    Source("12", "source"),
                    "title",
                    "url",
                    "urlToImage"
                )
            ),
            totalResults = 1
        )
        whenever(internetManager.checkNetwork()).thenReturn(true)
        whenever(apiService.getNews(searchQuery)).thenReturn(response)

        val result = repository.searchForNews(searchQuery)

        verify(newsDao).insertArticles(response.articles.map { it.toEntity() })
        assertEquals(article, result)
    }
    @Test
    fun `test searchForNews should return articles from database when network is not available`() = runTest {

        val searchQuery = "test"
        val articleEntity = listOf( ArticleEntity(
            1,
            "title",
            "description",
            "url",
            "source",
            "urlToImage",
            "content",
            "content",
            source = "source",
            isBookmarked = false
        )
        )

        whenever(internetManager.checkNetwork()).thenReturn(false)
        whenever(newsDao.searchArticles(searchQuery)).thenReturn(articleEntity)


        val result = repository.searchForNews(searchQuery)


        assertEquals(articleEntity.map { it.toDomainModel() }, result)
    }

    @Test
    fun `test getAllBookmarkedArticles should return all bookmarked articles`() = runTest {

        val bookmarkedArticles = listOf(
            ArticleEntity(
                1,
                "title",
                "description",
                "url",
                "source",
                "urlToImage",
                "content",
                "content",
                source = "source",
                isBookmarked = true
            )
        )
        whenever(newsDao.getAllBookmarkedArticles()).thenReturn(bookmarkedArticles)

        val result = repository.getAllBookmarkedArticles()

        assertEquals(bookmarkedArticles.map { it.toDomainModel() }, result)
    }

    @Test
    fun `test isArticleBookmarked should return true if article is bookmarked`() = runTest {

        val articleId = "urlToImage"
        val articleEntity = ArticleEntity(
            1,
            "title",
            "description",
            "url",
            "source",
            "urlToImage",
            "content",
            "content",
            source = "source",
            isBookmarked = true)
        whenever(newsDao.getArticlesById(articleId)).thenReturn(articleEntity)


        val result = repository.isArticleBookmarked(articleId)


        assertTrue(result)
    }
    @Test
    fun `test isArticleBookmarked should return false if article is bookmarked`() = runTest {

        val articleId = "urlT"
        val articleEntity = ArticleEntity(
            1,
            "title",
            "description",
            "url",
            "source",
            "urlToImage",
            "content",
            "content",
            source = "source",
            isBookmarked = false)
        whenever(newsDao.getArticlesById(articleId)).thenReturn(articleEntity)

        val result = repository.isArticleBookmarked(articleId)

        assertFalse(result)
    }
    @Test
    fun `test bookMarkArticle should insert the article as bookmarked`() = runTest {
        val article = Article(
            "author",
            "content",
            "description",
            "publishedAt",
            "source",
            "title",
            "url",
            "urlToImage",
            1,
            isBookmarked = true
        )
        val articleEntity = article.toArticleEntity()

        repository.bookMarkArticle(article)


        verify(newsDao).upsert(articleEntity)
    }
    @Test
    fun `test unBookMarkArticle  should insert the article as bookmarked`() = runTest {
        val article = Article(
            "author",
            "content",
            "description",
            "publishedAt",
            "source",
            "title",
            "url",
            "urlToImage",
            1,
            isBookmarked = false
        )
        val articleEntity = article.toArticleEntity()

        repository.unBookMarkArticle(article)


        verify(newsDao).upsert(articleEntity)
    }
}