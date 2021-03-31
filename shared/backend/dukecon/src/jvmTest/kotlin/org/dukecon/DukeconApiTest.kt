package org.dukecon

import kotlinx.coroutines.runBlocking
import org.dukecon.remote.api.DukeconApi
import kotlin.test.Test
import kotlin.test.assertTrue

class DukeconApiTest {

  @Test
  fun testJavaland() {
    val api = DukeconApi("https://programm.javaland.eu/2019/rest/", "javaland2019")
    runBlocking<Unit> {
      val sessions = api.getConference("javaland2019")
      sessions.events?.let { assertTrue { it.isNotEmpty() } }
    }
  }
}
