package toggle

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ToggleController(@Autowired val toggleService: ToggleService) {

    @GetMapping("/feature-toggle/{name}")
    fun resolveFeatureToggleValue(@PathVariable("name") name: String): Message =
        Message(name, toggleService.getValue(name))
}

data class Message(val name: String, val value: Boolean)