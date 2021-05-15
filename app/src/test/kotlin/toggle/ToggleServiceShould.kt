package toggle

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class ToggleServiceShould {

    @MockK
    var toggleRepository: ToggleRepository = mockk()

    private var toggle = ToggleService(toggleRepository)

    @Test fun `should return true when toggle is on`() {
        every { toggleRepository.getValue("toggle1") } returns true

        toggle.getValue("toggle1")?.let { assertTrue(it) }
    }

    @Test fun `should return false when toggle is off`() {
        every { toggleRepository.getValue("toggle2") } returns false

        toggle.getValue("toggle2")?.let { assertFalse(it) }
    }

}
