package com.nimeji.wclaug.model

class AugPriorityList (
    val priorityList: Map<Player, Long>
) {
    override fun toString(): String {
        return priorityList.entries
            .map { String.format("| %30s | %8d |", it.key, it.value) }
            .joinToString("\n") { it }
    }

    fun toNoteLine(): String {
        return priorityList.keys
            .filter { it.classSpecialization.role == Role.DPS }
            .filter { it.classSpecialization != ClassSpecialization.EVOKER_AUGMENTATION }
            .joinToString(",") { "${it.name}:${it.classSpecialization.classId}:${it.classSpecialization.specIndex}" }
    }
}
