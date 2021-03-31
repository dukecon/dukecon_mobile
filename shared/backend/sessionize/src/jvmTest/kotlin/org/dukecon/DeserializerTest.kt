package org.dukecon

import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.dukecon.sessionize.jsondata.Days
import org.junit.Test

class DeserializerTest {
  @Test
  fun deserializeJson() {
    val nonStrictJson = Json {
      isLenient = true
      ignoreUnknownKeys = true
    }
    "/bedcon.json".asResource {
      nonStrictJson.decodeFromString(ListSerializer(Days.serializer()), it)
    }
  }

  private fun String.asResource(work: (String) -> Unit) {
    val content = this.javaClass::class.java.getResource(this).readText()
    work(content)
  }
}
