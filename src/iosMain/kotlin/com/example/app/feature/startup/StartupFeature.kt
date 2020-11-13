package com.example.app.feature.startup

import com.example.app.model.user.User

interface StartupFeature {

    interface Model {

        suspend fun getUsers(): List<User>

    }

    interface View {

        fun setUsers(users: List<User>)

        fun setError()

    }

    interface Presenter {

        fun onViewDidLoad()

    }

}
