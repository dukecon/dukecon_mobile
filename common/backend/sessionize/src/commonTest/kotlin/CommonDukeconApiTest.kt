import kotlinx.coroutines.runBlocking
import org.dukecon.sessionize.api.SessionizeApiImpl

import kotlin.test.Test
import kotlin.test.assertTrue

class CommonDukeconApiTest {
    @Test
    fun testBedcon() {
        val api = SessionizeApiImpl("zs3eoop8")
        runBlocking<Unit> {
            val sessions = api.getSessions()
            assertTrue { sessions.isNotEmpty() }
        }
    }

}
