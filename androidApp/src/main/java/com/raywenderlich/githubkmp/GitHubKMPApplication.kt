package com.raywenderlich.githubkmp

import android.app.Application
import com.raywenderlich.api.GitHubApi
import com.raywenderlich.model.MembersDataRepository
import com.raywenderlich.presentation.DataRepository

class GitHubKMPApplication : Application() {
    val dataRepository: DataRepository by lazy {
        MembersDataRepository(GitHubApi())
    }
}