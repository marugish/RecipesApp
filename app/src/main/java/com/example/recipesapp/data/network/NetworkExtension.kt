package com.example.recipesapp.data.network

import com.example.recipesapp.domain.search.model.Response

inline fun <reified T> retrofit2.Response<T>.call(): Response<T> {
    val body = this.body() ?: return Response.Error(
        errorMessage = this.message(),
        errorCode = this.code()
    )

    return Response.Success(
        data = body,
        responseCode = this.code()
    )
}