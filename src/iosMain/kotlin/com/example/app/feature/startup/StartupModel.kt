package com.example.app.feature.startup

import com.example.app.model.user.UserApi

class StartupModel(
    private val userApi: UserApi = UserApi()
) : StartupFeature.Model {

    override suspend fun getUsers() =
        userApi.getUsers()

}
