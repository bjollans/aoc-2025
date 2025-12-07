package helpers

class IndexRow {
    val indices: List<Int>
    val size: Int
    get() = indices.size

    constructor(inputLine: String, indexOf: Char) {
        indices = inputLine.indices.filter { i -> inputLine[i] == indexOf }
    }

    constructor(indices: List<Int> ) {
        this.indices = indices
    }

    fun intersect(other: IndexRow): IndexRow {
        return IndexRow(this.indices.intersect(other.indices.toSet()).toList())
    }

    fun union(other: IndexRow): IndexRow {
        return IndexRow(this.indices.union(other.indices).toList())
    }

    fun subtract(other: IndexRow): IndexRow {
        return IndexRow(this.indices.subtract(other.indices.toSet()).toList())
    }

    operator fun contains(x: Int): Boolean {
        return x in this.indices
    }

    fun splitIndices(): IndexRow {
        return IndexRow(this.indices.flatMap { i -> listOf(i-1,i+1) }.distinct())
    }
}