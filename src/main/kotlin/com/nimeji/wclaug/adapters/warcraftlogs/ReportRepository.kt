package com.nimeji.wclaug.adapters.warcraftlogs

import com.apollographql.apollo3.api.Optional
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import com.fasterxml.jackson.module.kotlin.readValue
import com.nimeji.wclaug.adapters.warcraftlogs.graphql.FightIdsFromReportQuery
import com.nimeji.wclaug.adapters.warcraftlogs.graphql.SingleReportQuery

import com.nimeji.wclaug.model.Fight
import core.adapters.dto.ReportTable
import core.configuration.client
import kotlinx.coroutines.runBlocking
import java.lang.IllegalArgumentException
import java.nio.file.Files
import java.nio.file.Path

import java.nio.file.Paths
import java.util.regex.Pattern

class ReportRepository {
    private val urlPattern = Pattern.compile("https://www.warcraftlogs.com/reports/([a-zA-Z0-9]+)#fight=([0-9]+).*")

    private val mapper = jacksonMapperBuilder()
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .build()

    fun getFightsByWclUrl(vararg wclUrls: String): List<Fight> {
        return wclUrls.map { getFightByWclUrl(it) }
    }

    private fun getFightByWclUrl(wclUrl: String): Fight {
        val matcher = urlPattern.matcher(wclUrl)

        if (!matcher.matches()) {
            throw IllegalArgumentException("Provided url is not a warcraftlogs fight")
        }

        val report = matcher.group(1)
        val fightId = matcher.group(2)

        return Fight(report, fightId.toInt())
    }

    fun getFightByReportAndFightId(report: String, fightIds: List<Int>? = null, encounterId: Int? = null): List<Fight> {
        val response = runBlocking {
            client.query(
                FightIdsFromReportQuery(
                report,
                Optional.presentIfNotNull(encounterId),
                Optional.presentIfNotNull(fightIds)
            )
            ).execute()
        }

        return response.data?.reportData?.report?.fights
            ?.filter { it?.id != null }
            ?.map { Fight(report, it!!.id) } ?: listOf()
    }

    fun findFightReport(fight: Fight, timestampFrom: Int, timestampTo: Int): ReportTable {
        val cachedFight = findCachedFight(fight, timestampFrom, timestampTo)
        if (cachedFight != null) {
            return cachedFight
        }

        val wclFight = findWclFight(fight, timestampFrom, timestampTo)

        cacheFight(wclFight, fight, timestampFrom, timestampTo)

        return wclFight
    }

    private fun findWclFight(fight: Fight, timestampFrom: Int, timestampTo: Int): ReportTable {
        val response = runBlocking {
            client.query(
                SingleReportQuery(
                    fight.report,
                    fight.id,
                    "(timestamp > $timestampFrom) AND (timestamp < $timestampTo)"
                )
            ).execute()
        }

        if (response.errors != null) {
            println(response.errors)
        }

        val json = mapper.writeValueAsString(response.data?.reportData?.report?.table)

        return mapper.readValue(json)
    }

    private fun findCachedFight(fight: Fight, timestampFrom: Int, timestampTo: Int): ReportTable? {
        val path: Path = Paths.get("cache/${cacheFileName(fight, timestampFrom, timestampTo)}")

        if (!Files.exists(path)) {
            return null
        }

        val content = Files.readString(path)

        return try {
            mapper.readValue<ReportTable>(content)
        } catch (e: Exception) {
            null
        }
    }

    private fun cacheFight(report: ReportTable, fight: Fight, timestampFrom: Int, timestampTo: Int) {
        val dir = Paths.get("cache/")
        val file = dir.resolve(cacheFileName(fight, timestampFrom, timestampTo))

        if (!Files.exists(dir)) {
            Files.createDirectory(dir)
        }

        if (!Files.exists(file)) {
            Files.createFile(file)
        }

        Files.writeString(file, mapper.writeValueAsString(report))
    }

    private fun cacheFileName(fight: Fight, timestampFrom: Int, timestampTo: Int): String {
        return "${fight.report}-${fight.id}-$timestampFrom-$timestampTo.json"
    }
}
