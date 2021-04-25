package toggle

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class ToggleShould {

    @MockK
    var toggleRepository: ToggleRepository = mockk<ToggleRepository>()

    private var toggle = Toggle(toggleRepository)

    @Test fun `should return true when toggle is on`() {
        every { toggleRepository.getValue("toggle1") } returns true

        assertTrue(toggle.getValue("toggle1"))
    }

    @Test fun `should return false when toggle is off`() {
        every { toggleRepository.getValue("toggle2") } returns false

        assertFalse(toggle.getValue("toggle2"))
    }

}
