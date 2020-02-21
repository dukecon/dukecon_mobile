import org.dukecon.macoun.api.MacounApi
import kotlin.test.Test
import kotlin.test.assertTrue

expect fun <T> runTest(block: suspend () -> T)

class CommonMacounApiTest {
    @Test
    fun testMacoun() = runTest {
        val api = MacounApi("https://backend.macoun.de/fahrplan", "2019.json")
        val conferences = api.getConferenceStr("2019.json")
        assertTrue { conferences.isNotEmpty() }
    }
}
