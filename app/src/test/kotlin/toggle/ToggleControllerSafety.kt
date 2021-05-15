package toggle

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import kotlin.test.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToggleControllerSafety(
    @Autowired val restTemplate: TestRestTemplate,
    @Autowired val toggleRepository: ToggleRepository) {

    companion object {
        private const val FEATURE_ON = "featureOn"
        private const val FEATURE_OFF = "featureOff"
    }

    @BeforeEach
    internal fun setUp() {
        toggleRepository.addToggle(FEATURE_ON, true)
        toggleRepository.addToggle(FEATURE_OFF, false)
    }

    @AfterEach
    internal fun tearDown() {
        toggleRepository.deleteToggle(FEATURE_ON)
        toggleRepository.deleteToggle(FEATURE_OFF)
    }

    @Test
    fun `should return 200 and true when requested by feature1`() {
        val entity = restTemplate.getForEntity<String>("/feature-toggle/$FEATURE_ON")

        assertEquals(HttpStatus.OK, entity.statusCode)
        assertEquals("{\"name\":\"$FEATURE_ON\",\"value\":true}", entity.body)
    }

    @Test
    fun `should return 404 when requested a unknown path`() {
        val entity = restTemplate.getForEntity<String>("/unknown-path")

        assertEquals(HttpStatus.NOT_FOUND, entity.statusCode)
    }
}