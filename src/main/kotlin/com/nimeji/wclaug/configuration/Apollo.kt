package core.configuration

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

val client = ApolloClient.Builder()
    .serverUrl("https://www.warcraftlogs.com/api/v2/client")
    .okHttpClient(
        OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
    )

    .build()

typealias ApolloJson = Map<String, Any?>

private class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer key")
            .build()

        return chain.proceed(request)
    }
}


