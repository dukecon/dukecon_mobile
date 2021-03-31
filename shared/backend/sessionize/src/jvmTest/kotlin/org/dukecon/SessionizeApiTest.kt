package org.dukecon

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlinx.coroutines.runBlocking
import org.dukecon.sessionize.api.SessionizeApiImpl

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
