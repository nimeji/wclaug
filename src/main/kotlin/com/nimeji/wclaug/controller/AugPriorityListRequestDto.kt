package com.nimeji.wclaug.controller

import com.nimeji.wclaug.model.ClassSpecialization
import com.nimeji.wclaug.model.Fight

class AugPriorityListRequestDto (
    val ebonMightTimings: Set<String>,
    val fights: Set<Fight>,
    val specWeights: Map<ClassSpecialization, Float>
)
