package com.example.newsreader.domain.useCase

import com.example.newsreader.domain.manager.LocalUserManager
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


@ViewModelScoped
class SavingAppEntryUseCase @Inject constructor(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}