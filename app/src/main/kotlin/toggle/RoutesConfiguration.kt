package toggle.toggle

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import toggle.ToggleRoute

@Configuration
class RoutesConfiguration() {

    @Bean
    fun routes(toggleRoute: ToggleRoute): RouterFunction<ServerResponse> = router {
        accept(APPLICATION_JSON).nest {
            GET("/feature-toggle/{name}", toggleRoute::resolveFeatureToggleValue)
        }
    }

}