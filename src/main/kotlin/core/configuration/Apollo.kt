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
            .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5YjRkZTZlNy1jMDhhLTQ5NzUtYWQ3ZS05YWVhN2Y1OWRkY2YiLCJqdGkiOiIxMzJjNTc5ZjFhOThlYjBkZDc0YmZjNmU3Y2Q1ZWJkNWJmNzZjN2U5MDgzMDdkMzE4N2IzYmFiNWY1NWNkNDM3Y2Y1NTBiYTIxZjVkMGQ4ZSIsImlhdCI6MTcwNzU4ODk1NC45NTg3NzgsIm5iZiI6MTcwNzU4ODk1NC45NTg3OCwiZXhwIjoxNzM4NjkyOTU0Ljk1MTcxMSwic3ViIjoiIiwic2NvcGVzIjpbInZpZXctdXNlci1wcm9maWxlIiwidmlldy1wcml2YXRlLXJlcG9ydHMiXX0.vsI0g4CtEMpWBH3msZN5sH-_NYj_MBGEHtiwSdLY_yAaIroNUrHwlBkNgm3hLuuT4vHCOkRcmdWnwnI9-sLLEsSaCRZQp-DQGIfTbXwYtwpTW5OW73t1D4nWoV2r54zW7PCTlLT4gvp-9J-8zpHKyHtowjiYp5K7sQXm7rdB45qoI7Y9MI5SLX_b9gZjQ1ogBJxytza40UG9c6ZEUToutTFZdz_ThjqZjUsO_55FQJLVInCNh5ZVHGz_Cd3mV5Qi-5p8Q3bm3Ij4P9_s7JHZwFGtXesSvUxSAFsly7yPEhb_rGDb19kGjge9cq_wS3MX62zbbweA7eq4CXXN3mcPs8CmynnUPhh3hvMfC25pFyypEhZinwWYYQtiucWGkl1eSriirvN0H-SJ0XaOtzvWEF4_8IjVQ9-ETcl6Q3HWm1DTHZ7H5t1V5022ejpC8hIjO13HbK711Tdy3RJuNft8AGypvI7Ri-D1Sa95MzDnxHRyLP15nXCNYGew50M6KjsDu2yej2va6u7svAZ9UyyV50femnLSkUtA0H18MqOzzCcchb2tppDfHEC-wGh91YO-miZrPscU7ZBo0xwZ9TbAVtzvnP1n8tLiOCU5wEIjmObwxNC5gTBB0rNcz_ZMYjKPVf0HgPXSQs19PObhohKcb9-mK2RTpNmq-utccPjKUi4")
            .build()

        return chain.proceed(request)
    }
}


