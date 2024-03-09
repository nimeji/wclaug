package com.nimeji.wclaug.model

import java.lang.IllegalArgumentException
import java.util.regex.Pattern

class Timing(val time: Int, val phase: Int? = null) {
    companion object {
        private val timingPattern = Pattern.compile("([0-9]{1,2}):([0-9]{1,2})")

        fun parse(timing: String): Timing {
            val matcher = timingPattern.matcher(timing)

            if (!matcher.matches()) {
                throw IllegalArgumentException("$timing is not a valid time format")
            }

            val minutes = matcher.group(1).toInt()
            val seconds = matcher.group(2).toInt()

            return Timing((minutes * 60 + seconds) * 1000)
        }
    }

    fun toNote(): String {
        val minutes = String.format("%02d", time / 1000 / 60)
        val seconds = String.format("%02d", time / 1000 % 60)

        val timePart = "time:$minutes:$seconds"
        val phasePart = phase?.let { "p$phase" }

        val innerPart = listOfNotNull(timePart, phasePart)
            .joinToString(",")

        return "{$innerPart}"
    }
}