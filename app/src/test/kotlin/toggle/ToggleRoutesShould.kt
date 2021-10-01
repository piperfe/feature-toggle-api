package toggle

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.mock.web.reactive.function.server.MockServerRequest.builder
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.test.StepVerifier


class ToggleRouteShould {

    @MockK
    private var toggleService: ToggleService = mockk()

    private var toggleRoute: ToggleRoute = ToggleRoute(toggleService)

    @Test
    fun `should return 500 when unexpected error`() {
        every { toggleService.getValue("fakeFeatureToggleName") } throws Exception()
        val request = builder().pathVariable("name", "fakeFeatureToggleName").build()

        val response = toggleRoute.resolveFeatureToggleValue(request)

        assertHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR, response)
    }

    @Test
    fun `should return 404 when requested an unknown feature toggle`() {
        every { toggleService.getValue("unknownFeatureToggle") } returns null
        val request = builder().pathVariable("name", "unknownFeatureToggle").build()

        val response = toggleRoute.resolveFeatureToggleValue(request)

        assertHttpStatus(HttpStatus.NOT_FOUND, response)
    }

    private fun assertHttpStatus(expected: HttpStatus, value: Mono<ServerResponse>) {
        StepVerifier.create(value)
            .expectNextMatches { r: ServerResponse ->
                expected == r.statusCode() && r.rawStatusCode() == expected.value()
            }
            .expectComplete()
            .verify()
    }
}
