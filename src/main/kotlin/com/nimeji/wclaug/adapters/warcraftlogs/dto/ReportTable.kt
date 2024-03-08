package core.adapters.dto

class ReportTable (val data: ReportTableDataDto)

class ReportTableDataDto(
    val entries: List<ReportTableEntryDto>
) {
    override fun toString(): String {
        return entries
            .sortedByDescending { it.total }
            .joinToString("\n") { it.toString() }
    }
}

class ReportTableEntryDto(
    val name: String,
    val icon: String,
    val total: Long,
) {
    override fun toString(): String {
        return String.format("| %30s | %8d |", name, total)
    }
}