package toggle

import org.springframework.stereotype.Component
import java.util.NoSuchElementException

@Component
class ToggleRepository {
    private val toggleDB = mutableMapOf<String, Boolean>()

    fun getValue(name: String): Boolean? {
        return try {
            toggleDB.getValue(name)
        } catch (e: NoSuchElementException) {
            null
        }
    }

    fun addToggle(name: String, value: Boolean): Boolean? {
        return toggleDB.put(name, value)
    }

    fun deleteToggle(name: String): Boolean? {
        return toggleDB.remove(name)
    }

    fun getSize(): Int {
        return toggleDB.size
    }

}
