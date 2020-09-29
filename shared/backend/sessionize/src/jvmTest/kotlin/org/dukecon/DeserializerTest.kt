package org.dukecon

import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.dukecon.sessionize.jsondata.Days
import org.junit.Test

class DeserializerTest {
    @Test
    fun deserializeJson() {
        Json.decodeFromString(ListSerializer(Days.serializer()), str)
    }


    val str = """
   []
""".trimIndent()
}