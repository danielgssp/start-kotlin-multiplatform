package com.raywenderlich.presentation

import com.raywenderlich.model.Member

interface DataRepository {
    val members: List<Member>?
    var onRefreshListners: List<() -> Unit>

    suspend fun update()
}