class IdRange (var start: Long, var end: Long) {
    val range = start..end

    fun combine(other: IdRange): Boolean {
        when {
            other.end in range && other.start !in range -> this.start = other.start
            other.start in range && other.end !in range -> this.end = other.end
            other.start in range && other.end in range -> Unit
            this.start in other.range && this.end in other.range -> {this.end = other.end; this.start = other.start}
            else -> return false
        }
        return true
    }

    fun findInvalidIds(): List<Long> {
        val invalidIds = mutableListOf<Long>()
        for (id in range) {
            val idStr = id.toString()
            if (idStr.length % 2 != 0) continue
            val firstHalf = idStr.take(idStr.length/2)
            val secondHalf = idStr.drop(idStr.length/2)
            if (firstHalf == secondHalf) invalidIds.add(id)
        }
        return invalidIds.toList()
    }
}