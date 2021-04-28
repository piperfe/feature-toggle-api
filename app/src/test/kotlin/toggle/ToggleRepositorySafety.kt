package toggle

import kotlin.test.*

class ToggleRepositorySafety {

    private val toggleRepository: ToggleRepository = ToggleRepository()

    @Test
    fun `should get toggle value from db`() {
        toggleRepository.addToggle("feature1", true)
        toggleRepository.addToggle("feature2", false)

        assertTrue(toggleRepository.getValue("feature1"))
        assertFalse(toggleRepository.getValue("feature2"))

        toggleRepository.deleteToggle("feature1")
        toggleRepository.deleteToggle("feature2")
        assertEquals(toggleRepository.getSize(), 0)
    }

    @Test
    fun `should throws exception when toggle does not exists`() {
        assertFailsWith<NoSuchElementException> {
            toggleRepository.getValue("unknown_feature2")
        }
    }

}


