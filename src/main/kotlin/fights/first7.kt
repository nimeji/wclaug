package fights

import core.adapters.ReportRepository
import core.Service
import core.model.Timing


fun main() {
    val service = Service()
    val repository = ReportRepository()

    val gnarlroot = repository.getFightsByWclUrl(
        "https://www.warcraftlogs.com/reports/TMjRkz7PG1tLFnDd#fight=6",
        "https://www.warcraftlogs.com/reports/rVqkQzRYPbt2Z4wN#fight=7",
        "https://www.warcraftlogs.com/reports/KAyZN7CPhHJ3cwFT#fight=8",
    )

    val igira = repository.getFightsByWclUrl(
        "https://www.warcraftlogs.com/reports/TMjRkz7PG1tLFnDd#fight=11",
        "https://www.warcraftlogs.com/reports/rVqkQzRYPbt2Z4wN#fight=12",
        "https://www.warcraftlogs.com/reports/KAyZN7CPhHJ3cwFT#fight=13",
    )

    val volcoross = repository.getFightsByWclUrl(
        "https://www.warcraftlogs.com/reports/TMjRkz7PG1tLFnDd#fight=23",
        "https://www.warcraftlogs.com/reports/rVqkQzRYPbt2Z4wN#fight=22",
        "https://www.warcraftlogs.com/reports/KAyZN7CPhHJ3cwFT#fight=18",
    )

    val larodar = repository.getFightsByWclUrl(
        "https://www.warcraftlogs.com/reports/TMjRkz7PG1tLFnDd#fight=31",
        "https://www.warcraftlogs.com/reports/rVqkQzRYPbt2Z4wN#fight=28",
        "https://www.warcraftlogs.com/reports/KAyZN7CPhHJ3cwFT#fight=25",
    )

    val council = repository.getFightsByWclUrl(
        "https://www.warcraftlogs.com/reports/TMjRkz7PG1tLFnDd#fight=43",
        "https://www.warcraftlogs.com/reports/rVqkQzRYPbt2Z4wN#fight=36",
        "https://www.warcraftlogs.com/reports/KAyZN7CPhHJ3cwFT#fight=31",
    )

    val nyume = repository.getFightsByWclUrl(
        "https://www.warcraftlogs.com/reports/TMjRkz7PG1tLFnDd#fight=48",
        "https://www.warcraftlogs.com/reports/rVqkQzRYPbt2Z4wN#fight=41",
        "https://www.warcraftlogs.com/reports/KAyZN7CPhHJ3cwFT#fight=35",
    )

    val smolderon = repository.getFightsByWclUrl(
        "https://www.warcraftlogs.com/reports/TMjRkz7PG1tLFnDd#fight=52",
        "https://www.warcraftlogs.com/reports/rVqkQzRYPbt2Z4wN#fight=54",
        "https://www.warcraftlogs.com/reports/KAyZN7CPhHJ3cwFT#fight=47",
    )

    println("wclaugstart")
    service.findOptimalTargets(
        listOf(
            Timing(5000),
            Timing(32000),
            Timing(60000),
            Timing(89000),
            Timing(118000),
            Timing(147000),
            Timing(176000),
            Timing(205000),
            Timing(234000),
            Timing(263000),
            Timing(292000),
            Timing(321000),
            Timing(350000),
            Timing(379000)
        ),
        gnarlroot
    )
        .forEach {
            println(it.toNoteLine())
        }
    println("wclaugend")
}

