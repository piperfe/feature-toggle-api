package toggle

import kotlin.test.*

class ToggleRepositorySafety {

    private val toggleRepository: ToggleRepository = ToggleRepository()

    @Test
    fun `should get toggle value from db`() {
        toggleRepository.addToggle("feature1", true)
        toggleRepository.addToggle("feature2", false)

        toggleRepository.getValue("feature1")?.let { assertTrue(it) }
        toggleRepository.getValue("feature2")?.let { assertFalse(it) }

        toggleRepository.deleteToggle("feature1")
        toggleRepository.deleteToggle("feature2")
        assertEquals(toggleRepository.getSize(), 0)
    }

    @Test
    fun `should return null when toggle does not exists`() {
        assertNull(toggleRepository.getValue("unknown_feature2"))
    }
}


