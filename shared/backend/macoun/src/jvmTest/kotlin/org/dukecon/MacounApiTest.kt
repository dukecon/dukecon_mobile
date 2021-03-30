package org.dukecon

import kotlinx.coroutines.runBlocking
import org.dukecon.remote.api.MacounApi
import kotlin.test.Test
import kotlin.test.assertTrue

class MacounApiTest {

    @Test
    fun testJavaland() {
        val api = MacounApi("https://backend.macoun.de/fahrplan", "2020.json")
        runBlocking<Unit> {
            val fahrplan = api.getFahrplan()
            fahrplan.speakers?.let {
                assertTrue { it.isNotEmpty() }
            }
        }
    }
}
