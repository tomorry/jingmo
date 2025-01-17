package com.hefengbao.jingmo.ui.screen.classicpoem

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.ClassicPoemCollectionEntity
import com.hefengbao.jingmo.data.repository.ClassicPoemRepository
import com.hefengbao.jingmo.ui.screen.classicpoem.nav.ClassicPoemBookmarksReadArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class ClassicPoemBookmarksReadViewModel @Inject constructor(
    private val classicPoemRepository: ClassicPoemRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val args = ClassicPoemBookmarksReadArgs(savedStateHandle)
    private var id = MutableStateFlow(args.poemId.toInt())
    private var collectedAt = MutableStateFlow(0L)

    fun setCurrentId(id: Int) {
        this.id.value = id
    }

    fun setCurrentCollectedAt(collectedAt: Long) {
        this.collectedAt.value = collectedAt
    }

    val classicPoemEntity = id.flatMapLatest {
        classicPoemRepository.get(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val prevId = collectedAt.flatMapLatest {
        classicPoemRepository.getCollectionPrevId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val nextId = collectedAt.flatMapLatest {
        classicPoemRepository.getCollectionNextId(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val classicPoemCollectionEntity = id.flatMapLatest {
        classicPoemRepository.isCollect(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    fun collect(id: Int) {
        viewModelScope.launch {
            classicPoemRepository.collect(ClassicPoemCollectionEntity(id))
        }
    }

    fun uncollect(id: Int) {
        viewModelScope.launch {
            classicPoemRepository.uncollect(id)
        }
    }
}