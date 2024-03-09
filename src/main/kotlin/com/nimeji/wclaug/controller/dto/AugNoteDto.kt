package com.nimeji.wclaug.controller.dto

import com.nimeji.wclaug.model.Timing
import com.nimeji.wclaug.model.AugPriorityList

class AugNoteDto(val note: String) {
    companion object {
        fun fromPriorityLists(priorityLists: Map<Timing, AugPriorityList>): AugNoteDto {
            val note = "wclaugstart\n%s\nwclaugend"

            val lines = priorityLists.entries
                .joinToString { (timing, priority) -> "${timing.toNote()}${priority.toNoteLine()}" }

            return AugNoteDto(note.format(lines))
        }
    }
}