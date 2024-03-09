package com.nimeji.wclaug.controller

import com.nimeji.wclaug.controller.dto.AugNoteDto
import com.nimeji.wclaug.model.Timing
import com.nimeji.wclaug.service.AugPriorityService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AugNoteController(
    private val augPriorityService: AugPriorityService
) {
    @PostMapping("/generateNote")
    fun generateNote(@RequestBody requestDto: AugPriorityListRequestDto): AugNoteDto {
        val timings = requestDto.ebonMightTimings
            .map { Timing.parse(it) }
            .toSet()

        val priorityLists = timings.associateWith {
            augPriorityService.calculatePriority(it, requestDto.fights, requestDto.specWeights)
        }

        return AugNoteDto.fromPriorityLists(priorityLists)
    }
}
