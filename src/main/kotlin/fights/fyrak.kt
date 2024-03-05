package fights

import core.adapters.ReportRepository
import core.Service
import core.model.Timing


fun main() {
    val service = Service()
    val repository = ReportRepository()

    val fights = repository.getFightsByWclUrl(
        "https://www.warcraftlogs.com/reports/XNLmRh2PKtryd1Mz#fight=5&type=casts&source=13",
        "https://www.warcraftlogs.com/reports/QdBp4CvPmNhbf8Kt#fight=80&type=damage-done",
        "https://www.warcraftlogs.com/reports/rXmV9jhB3gPwW87t#fight=8&type=damage-done&phase=1"
    )
    
    service.findOptimalTargets(Timing.parse(
        "00:05",
        "00:35",
        "01:03",
        "01:32",
        "02:07",
        "02:48",
        "03:17",
        "03:47",
        "04:16",
        "04:49",
        "05:21",
        "05:50",
        "06:19",
        "06:48",
        "07:17",
        "07:46",
        "08:15",
        "08:44",
    ), fights)
        .forEach {
            println(it.toNoteLine())
        }
}

