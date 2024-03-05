package core.model

class AugmentationBuffTargets (
    val timing: Timing,
    val priorityList: Map<Player, Long>
) {
    override fun toString(): String {
        return priorityList.entries
            .map { String.format("| %30s | %8d |", it.key, it.value) }
            .joinToString("\n") { it }
    }

    fun toNoteLine(): String {
        return timing.toNote() + priorityList.keys
            .filter { it.classSpec.role == Role.DPS }
            .filter { it.classSpec != ClassSpec.EVOKER_AUGMENTATION }
            .joinToString(",") { "${it.name}:${it.classSpec.classId}:${it.classSpec.specIndex}" }
    }
}
