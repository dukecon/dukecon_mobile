package org.dukecon

import kotlinx.coroutines.runBlocking
import org.dukecon.remote.sessionize.api.SessionizeApiImpl

import kotlin.test.Test
import kotlin.test.assertTrue

class SessionizeApiTest {

    @Test
    fun testBedcon() {
        val api = SessionizeApiImpl("zs3eoop8")
        runBlocking<Unit> {
            val sessions = api.getSessions()
            assertTrue { sessions.isNotEmpty() }
        }
    }
}
