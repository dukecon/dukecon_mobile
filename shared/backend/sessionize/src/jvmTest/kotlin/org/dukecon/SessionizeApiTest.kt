package org.dukecon

import kotlinx.coroutines.runBlocking
import org.dukecon.sessionize.api.SessionizeApiImpl

import kotlin.test.Test
import kotlin.test.assertTrue

class SessionizeApiTest {

    @Test
    fun testDroidconBerlin2021() {
        val api = SessionizeApiImpl("2wi6ppp2")
        runBlocking<Unit> {
            val sessions = api.getSessions()
            assertTrue { sessions.isNotEmpty() }
        }
    }
}
