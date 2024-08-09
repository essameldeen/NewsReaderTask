package com.example.newsreader.domain.useCase

import com.example.newsreader.domain.manager.LocalUserManager
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ReadAppEntryUseCase @Inject constructor(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readEntryApp()
    }
}