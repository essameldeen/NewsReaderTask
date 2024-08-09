package com.example.newsreader.presentation.onBoarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsreader.domain.useCase.SavingAppEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewMode @Inject constructor(
    private val savingAppEntryUseCase: SavingAppEntryUseCase
) : ViewModel() {


    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SaveAppEntry -> {
                saveAppEnrty()
            }
        }

    }

     fun saveAppEnrty() = viewModelScope.launch {

        savingAppEntryUseCase.invoke()

    }

}