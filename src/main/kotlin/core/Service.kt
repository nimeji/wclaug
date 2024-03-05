package core

import core.adapters.ReportRepository
import core.model.AugmentationBuffTargets
import core.model.Fight
import core.model.FightStatistics
import core.model.Timing

class Service {
    private val reportRepository = ReportRepository()

    fun findOptimalTargets(ebonMightTimings: List<Timing>, fights: List<Fight>): List<AugmentationBuffTargets> {
        return ebonMightTimings
            .associateWith { generateStatsForEMTiming(fights, it.time) }
            .map { AugmentationBuffTargets(it.key, it.value.playersByAverage()) }
            .filter { it.priorityList.isNotEmpty() }
    }

    private fun generateStatsForEMTiming(fights: List<Fight>, ebTiming: Int): FightStatistics {
        val fightStatistics = FightStatistics()

        fights.forEach { fight ->
            val fightReport = reportRepository.findFightReport(fight, ebTiming, ebTiming + 27000)
            fightStatistics.addFightReport(fight, fightReport.data)
        }

        return fightStatistics
    }
}