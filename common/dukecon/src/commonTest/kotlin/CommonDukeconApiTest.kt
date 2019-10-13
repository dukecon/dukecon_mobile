import org.dukecon.remote.api.DukeconApi
import kotlin.test.Test
import kotlin.test.assertTrue

expect fun <T> runTest(block: suspend () -> T)

class CommonDukeconApiTest {
    @Test
    fun testJavaland() = runTest {
        val api = DukeconApi("https://programm.javaland.eu/2019/rest", "javaland")
        val conferences = api.getConference("javaland2019")
        assertTrue { conferences.events.isNotEmpty() }
    }

    @Test
    fun testApachecon() = runTest {
        val api = DukeconApi("https://www.apachecon.com/acna19/s/rest/", "acna2019.json")
        val conferences = api.getConference("acna2019.json")
        assertTrue { conferences.events.isNotEmpty() }
    }
}
