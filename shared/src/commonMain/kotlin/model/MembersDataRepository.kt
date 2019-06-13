package com.raywenderlich.model

import com.raywenderlich.api.GitHubApi
import com.raywenderlich.api.UpdateProblem
import com.raywenderlich.presentation.DataRepository

class MembersDataRepository(private val api: GitHubApi) : DataRepository {
    override var members: List<Member>? = null

    override var onRefreshListners: List<() -> Unit> = emptyList()

    override suspend fun update() {
        val newMembers = try {
            api.getMembers()
        } catch (cause: Throwable) {
            throw UpdateProblem()
        }

        if (newMembers != members) {
            members = newMembers
            callRefreshListners()
        }
    }

    private fun callRefreshListners() {
        onRefreshListners.forEach { it() }
    }
}