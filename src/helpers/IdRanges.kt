package helpers

import kotlin.math.max
import kotlin.math.min

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

    private fun rangesOverlap(range1: LongRange, range2: LongRange): Boolean {
        val overlapsWithRange1Larger = range1.first <= range2.last && range1.first >= range2.first
        val overlapsWithRange2Larger = range2.first <= range1.last && range2.first >= range1.first
        return overlapsWithRange1Larger || overlapsWithRange2Larger
    }

    private fun reduceRanges(ranges: List<LongRange>): List<LongRange> {
        val reducedRanges = mutableListOf<LongRange>()
        val combinedRanges = mutableListOf<LongRange>()
        outer@ for (rangeI in ranges) {
            if (rangeI in combinedRanges) continue
            for (rangeK in ranges) {
                if (rangeI === rangeK) continue
                if (rangesOverlap(rangeI, rangeK)) {
                    val newRange = min(rangeI.first, rangeK.first)..max(rangeI.last, rangeK.last)
                    if (newRange !in reducedRanges) {
                        combinedRanges.add(rangeI)
                        combinedRanges.add(rangeK)
                        reducedRanges.add(newRange)
                        break
                    }
                }
            }
            if (rangeI !in combinedRanges) reducedRanges.add(rangeI)
        }
        return reducedRanges.toSet().toList()
    }
}