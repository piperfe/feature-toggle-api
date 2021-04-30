package toggle

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ToggleService(@Autowired val toggleRepository: ToggleRepository) {

    fun getValue(name: String): Boolean {
        return toggleRepository.getValue(name)
    }
}
