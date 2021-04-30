package toggle

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ToggleController {

    @GetMapping("/feature-toggle/{name}")
    fun resolveFeatureToggleValue(@PathVariable("name") name: String): Message =
        Message(name, true)
}

data class Message(val name: String, val value: Boolean)