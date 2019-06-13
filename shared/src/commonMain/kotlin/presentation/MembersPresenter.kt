package com.raywenderlich.presentation

import com.raywenderlich.ApplicationDispatcher
import kotlinx.coroutines.launch

class MembersPresenter(
    private val view: MembersView,
    private val repository: DataRepository
)   : CoroutinePresenter(ApplicationDispatcher, view) {

    private val onRefreshListener: () -> Unit = this::showData

    override fun onCreate() {
        view.isUpdating = isFirstDataLoading()
        repository.onRefreshListners += onRefreshListener
        showData()
        updateDate()
    }

    override fun onDestroy() {
        super.onDestroy()
        repository.onRefreshListners -= onRefreshListener
    }

    private fun showData() {
        view.onUpdate(repository.members.orEmpty())
    }

    private fun updateDate() {
        launch {
            repository.update()
            showData()
        }.invokeOnCompletion {
            view.isUpdating = false
        }
    }

    private fun isFirstDataLoading() = repository.members == null
}