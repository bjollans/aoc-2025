package helpers

import kotlin.collections.zipWithNext

class IdRanges(var inputRanges: List<LongRange>) {
    val ranges: List<LongRange>

    init {
        val sortedByStart = inputRanges.sortedBy { it.first }
        var reducedRanges = sortedByStart
        do {
            var size = reducedRanges.size
            reducedRanges = reduceRanges(reducedRanges)
        } while (size > reducedRanges.size)
        ranges = reducedRanges
    }

    operator fun contains(x: Long): Boolean {
        for (range in ranges) {
            if (x in range) return true
        }
        return false
    }

    private fun reduceRanges(ranges: List<LongRange>): List<LongRange> {
        val reducedRanges = mutableListOf<LongRange>()
        for ((range, next) in ranges.zipWithNext()) {
            when {
                range.first >= next.first && range.last <= next.last -> reducedRanges.add(next)
                next.first >= range.first && next.last <= range.last -> reducedRanges.add(range)
                range.first >= next.first && range.first <= next.last && range.last >= next.last -> reducedRanges.add(next.first..range.last)
                next.first >= range.first && next.first <= range.last && next.last >= range.last -> reducedRanges.add(range.first..next.last)
                else -> {
                    reducedRanges.add(range)
                    reducedRanges.add(next)
                }
            }
        }
        return reducedRanges
    }
}