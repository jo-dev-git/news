package com.jo.news.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jo.news.data.HeadlinesUiState
import com.jo.news.repository.HeadlineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeadlineViewModel @Inject constructor(private val headlineRepository: HeadlineRepository) :
    ViewModel() {

    private val _headlines = MutableLiveData<HeadlinesUiState>()
    val headlines: LiveData<HeadlinesUiState> get() = _headlines

    fun getHeadlines(country: String) {
        viewModelScope.launch {
            try {
                val response = headlineRepository.getHeadlines(country)
                _headlines.value = HeadlinesUiState.Success(response)
            } catch (e: Exception) {
                _headlines.value = HeadlinesUiState.Error("Error: ${e.message}")
            }
        }
    }
}