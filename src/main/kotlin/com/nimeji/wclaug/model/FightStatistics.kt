package core.model

import core.adapters.dto.ReportTableDataDto

class FightStatistics {
    private val playerStatistics: MutableMap<Player, PlayerStatistics> = mutableMapOf()

    fun addFightReport(fight: Fight, reportData: ReportTableDataDto) {
        reportData.entries.forEach {
            val classSpec = ClassSpec.fromIcon(it.icon)
            val player = playerStatistics.keys.find { p -> p.name == it.name && p.classSpec == classSpec }
                ?: Player(it.name, classSpec)

            val playerStatistics = playerStatistics.getOrPut(player) { PlayerStatistics() }

            playerStatistics.add(fight, it.total)
        }
    }

    fun playersByAverage(): Map<Player, Long>  {
        return playerStatistics
            .mapValues { it.value.average() }
            .toList()
            .sortedByDescending { (_, v) -> v }
            .toMap()
    }
}