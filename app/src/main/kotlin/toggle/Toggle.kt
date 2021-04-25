package toggle

class Toggle(toggleRepository: ToggleRepository) {
    private val toggleRepository = toggleRepository

    fun getValue(name: String): Boolean {
        return toggleRepository.getValue(name)
    }
}
