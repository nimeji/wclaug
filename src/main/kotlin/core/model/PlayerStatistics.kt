package core.model

class PlayerStatistics {
    private val damageDoneByFight: MutableMap<Fight, Long> = mutableMapOf()

    fun add(fight: Fight, damageDone: Long) {
        damageDoneByFight[fight] = damageDone
    }

    fun average(): Long {
        return damageDoneByFight.values.average().toLong()
    }
}
