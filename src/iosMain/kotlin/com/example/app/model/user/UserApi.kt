package com.example.app.model.user

import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import kotlin.native.concurrent.ensureNeverFrozen

interface UserApi {

    companion object {

        operator fun invoke(): UserApi = UserApiImpl()

    }

    suspend fun getUsers(): List<User>

}

private class UserApiImpl : UserApi {

    private companion object {

        const val BASE_URL = "https://jsonplaceholder.typicode.com"
        const val USERS_PATH = "/users"

    }

    private val http = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    init {
        ensureNeverFrozen()
    }

    override suspend fun getUsers() =
        http.get<List<User>>("$BASE_URL$USERS_PATH")

}
