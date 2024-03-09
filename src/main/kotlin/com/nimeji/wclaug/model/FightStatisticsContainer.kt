package com.nimeji.wclaug.model

import core.adapters.dto.ReportTableDataDto

class FightStatisticsContainer {
    private val playerStatistics: MutableMap<Player, PlayerStatistics> = mutableMapOf()

    fun addFightReport(fight: Fight, reportData: ReportTableDataDto) {
        reportData.entries.forEach {
            val classSpecialization = ClassSpecialization.fromIcon(it.icon)
            val player = playerStatistics.keys.find { p -> p.name == it.name && p.classSpecialization == classSpecialization }
                ?: Player(it.name, classSpecialization)

            val playerStatistics = playerStatistics.getOrPut(player) { PlayerStatistics() }

            playerStatistics.add(fight, it.total)
        }
    }

    fun playersByWeightedAverage(weights: Map<ClassSpecialization, Float>): Map<Player, Long>  {
        return playerStatistics
            .mapValues { it.value.average() }
            .toList()
            .sortedByDescending { (player, damage) -> damage * (weights[player.classSpecialization]?: 1.0f)  }
            .toMap()
    }
}