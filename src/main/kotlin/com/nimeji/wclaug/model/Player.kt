package com.nimeji.wclaug.model

class Player (
    val name: String,
    val classSpecialization: ClassSpecialization,
) {
    override fun toString(): String {
        return "$name-$classSpecialization}"
    }
}
