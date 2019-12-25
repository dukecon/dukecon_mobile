package org.dukecon.sessionize.api

import io.ktor.client.HttpClient
import io.ktor.client.features.HttpClientFeature
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.HttpResponsePipeline
import io.ktor.http.isSuccess
import io.ktor.util.AttributeKey
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object ExpectSuccess : HttpClientFeature<Unit, ExpectSuccess> {
    override val key: AttributeKey<ExpectSuccess> = AttributeKey("ExpectSuccess")

    override fun prepare(block: Unit.() -> Unit): ExpectSuccess = this

    override fun install(feature: ExpectSuccess, scope: HttpClient) {
        scope.responsePipeline.intercept(HttpResponsePipeline.Receive) {
            if (!context.response.status.isSuccess()) throw ApiException(context.response)
            proceedWith(subject)
        }
    }
}

class ApiException(val response: HttpResponse) : Throwable()