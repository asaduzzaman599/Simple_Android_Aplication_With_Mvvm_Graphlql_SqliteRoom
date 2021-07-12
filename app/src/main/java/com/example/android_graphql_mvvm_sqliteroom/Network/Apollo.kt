package com.example.android_graphql_mvvm_sqliteroom.Network

import com.apollographql.apollo.ApolloClient

object ApolloClientManager {
    private const val SERVER_URL = "https://graphqlzero.almansi.me/api"

    val apolloClient: ApolloClient = ApolloClient.builder()
        .serverUrl(SERVER_URL)
        .build()

}