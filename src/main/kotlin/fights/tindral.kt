package fights

import core.adapters.ReportRepository
import core.Service
import core.model.Timing


fun main() {
    val service = Service()
    val repository = ReportRepository()

    val fights = listOf(
        repository.getFightByReportAndFightId("KAyZN7CPhHJ3cwFT", fightIds = listOf(13)),
        repository.getFightByReportAndFightId("rVqkQzRYPbt2Z4wN", fightIds = listOf(12)),
        repository.getFightByReportAndFightId("TMjRkz7PG1tLFnDd", fightIds = listOf(11)),
    ).flatten()

    println("wclaugstart")
    service.findOptimalTargets(listOf(Timing(5000), Timing(32000), Timing(60000), Timing(89000), Timing(117000), Timing(145000), Timing(173000), Timing(201000), Timing(229000)), fights)
        .forEach {

            println(it.toNoteLine())

        }
    println("wclaugend")
}

