package com.nimeji.wclaug.service

import com.nimeji.wclaug.adapters.warcraftlogs.ReportRepository
import com.nimeji.wclaug.model.*
import org.springframework.stereotype.Service

@Service
class AugPriorityService {
    private val reportRepository = ReportRepository()

    fun calculatePriority(ebonMightTiming: Timing, fights: Set<Fight>, specWeights: Map<ClassSpecialization, Float>): AugPriorityList {
        val stats = generateFightSliceStatistics(fights, ebonMightTiming)

        return AugPriorityList(stats.playersByWeightedAverage(specWeights))
    }

    private fun generateFightSliceStatistics(fights: Set<Fight>, timing: Timing, duration: Int = 27000): FightStatisticsContainer {
        val fightStatisticsContainer = FightStatisticsContainer()

        fights.forEach { fight ->
            val fightReport = reportRepository.findFightReport(fight, timing.time, timing.time + duration)
            fightStatisticsContainer.addFightReport(fight, fightReport.data)
        }

        return fightStatisticsContainer
    }
}