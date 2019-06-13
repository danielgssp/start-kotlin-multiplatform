package com.raywenderlich.presentation

import com.raywenderlich.model.Member

interface MembersView : BaseView {
    var isUpdating: Boolean
    fun onUpdate(members: List<Member>)
}