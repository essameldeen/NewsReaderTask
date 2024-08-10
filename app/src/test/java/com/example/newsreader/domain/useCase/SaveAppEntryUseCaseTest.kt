package com.example.newsreader.domain.useCase

import com.example.newsreader.domain.manager.LocalUserManager
import io.mockk.coVerify
import io.mockk.mockk

import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SaveAppEntryUseCaseTest {
    private lateinit var useCase: SavingAppEntryUseCase
    private val userManager: LocalUserManager = mockk(relaxed = true)

    @Before
    fun setup() {
        useCase = SavingAppEntryUseCase(userManager)
    }
    @Test
    fun `invoke should return flow of boolean from localUserManager`() = runTest {


        // Act
        useCase.invoke()

        // Assert
        coVerify { userManager.saveAppEntry() }

    }

}