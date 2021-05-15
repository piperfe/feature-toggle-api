package toggle

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController
class ToggleController(@Autowired val toggleService: ToggleService) {

    @GetMapping("/feature-toggle/{name}")
    fun resolveFeatureToggleValue(@PathVariable("name") name: String): ResponseEntity<Message> {
        try {
            toggleService.getValue(name)?.let {
                return ResponseEntity.ok().body(Message(name, it))
            } ?: run {
                return ResponseEntity.notFound().build()
            }
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}

data class Message(val name: String, val value: Boolean)
