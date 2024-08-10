package com.example.newsreader.domain.useCase

import android.os.UserManager
import com.example.newsreader.domain.manager.LocalUserManager
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ReadAppEntryUseCaseTest {
    private lateinit var useCase: ReadAppEntryUseCase
    private val userManager: LocalUserManager = mockk()

    @Before
    fun setup() {
        useCase = ReadAppEntryUseCase(userManager)
    }
    @Test
    fun `invoke should return flow of boolean from localUserManager`() = runTest {

        val expectedValue = true
        val flow: Flow<Boolean> = flowOf(expectedValue)
        coEvery { userManager.readEntryApp() } returns flow


        val result = useCase.invoke().first()

        assertEquals(expectedValue, result)
    }

}