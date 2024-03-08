package core.model

class Player (
    val name: String,
    val classSpec: ClassSpec,
) {
    override fun toString(): String {
        return "$name-$classSpec}"
    }
}
