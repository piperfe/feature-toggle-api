package toggle

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.lang.Exception

@Component
class ToggleRoute(@Autowired val toggleService: ToggleService) {

    fun resolveFeatureToggleValue(request: ServerRequest): Mono<ServerResponse> {
        try {
            val name = request.pathVariable("name")
            toggleService.getValue(name)?.let {
                return ServerResponse.ok().bodyValue(Message(name, it))
            } ?: run {
                return ServerResponse.notFound().build()
            }
        } catch (e: Exception) {
            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}

data class Message(val name: String, val value: Boolean)
