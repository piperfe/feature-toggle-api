package toggle

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals


class ToggleControllerShould {

    @MockK
    private var toggleService: ToggleService = mockk()

    private var toggleController: ToggleController = ToggleController(toggleService)

    @Test
    fun `should return 500 when unexpected error`() {
        every { toggleService.getValue("featureToggleName") } throws Exception()

        val response = toggleController.resolveFeatureToggleValue("featureToggleName")

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.statusCode)
    }

    @Test
    fun `should return 404 when requested a unknown feature toggle`() {
        every { toggleService.getValue("unknownFeatureToggle") } returns null

        val response = toggleController.resolveFeatureToggleValue("unknownFeatureToggle")

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }
}