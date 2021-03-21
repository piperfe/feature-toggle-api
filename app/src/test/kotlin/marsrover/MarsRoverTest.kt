package marsrover

import kotlin.test.Test
import kotlin.test.assertNotNull

class MarsRoverTest {
    @Test fun testAppHasAGreeting() {
        val classUnderTest = MarsRover()
        assertNotNull(classUnderTest.greeting, "app should have a greeting")
    }
}
