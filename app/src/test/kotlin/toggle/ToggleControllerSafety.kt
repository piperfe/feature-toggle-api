package toggle

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import kotlin.test.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToggleControllerSafety(@Autowired val restTemplate: TestRestTemplate) {

    private val toggleRepository: ToggleRepository = ToggleRepository()

    @Test
    fun `should return 200 and true when requested by feature1`() {
        val featureName = "feature1"
        toggleRepository.addToggle(featureName, true)
        val entity = restTemplate.getForEntity<String>("/feature-toggle/$featureName")

        assertEquals(entity.statusCode, HttpStatus.OK)
        assertEquals(entity.body, "{\"name\":\"feature1\",\"value\":true}")
        toggleRepository.deleteToggle(featureName)
    }

    @Test
    fun `should return 404 when requested a unknown path`() {
        val entity = restTemplate.getForEntity<String>("/unknown-path")

        assertEquals(entity.statusCode, HttpStatus.NOT_FOUND)
    }
}