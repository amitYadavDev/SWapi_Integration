package amitapps.media.apipractice

import amitapps.media.apipractice.repository.ResultRepository
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ResultViewModel @Inject constructor(private val repository: ResultRepository) : ViewModel() {
    val list = repository.getResults().cachedIn(viewModelScope)
}