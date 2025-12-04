import helpers.IdRange
import helpers.getLinesForDay

class Day2Solver : Runnable {
    override fun run() {
        val rangeStrs = getLinesForDay("02", false)
        val ranges = rangeStrs[0].split(",")
            .map { it.split("-") }
            .map { IdRange(start = it[0].toLong(), end = it[1].toLong()) }
            .toList()
        val combinedRanges = combineRanges(ranges)
        val invalidIds = combinedRanges.flatMap { it.range.filter { num -> isInvalid(num) } }.toSet().toList()
        val invalidIdSum = invalidIds.sum()
        println("${invalidIds.size} invalid IDs have sum of $invalidIdSum")
    }

    fun combineRanges(ranges: List<IdRange>): List<IdRange> {
        val result = mutableListOf<IdRange>()
        for (rangeI in ranges) {
            var combined = false
            for (rangeK in ranges) {
                if (rangeK in result) continue
                if (rangeK == rangeI) continue
                if (rangeK.combine(rangeI)) {
                    combined = true
                    break
                }
            }
            if (!combined) {
                result.add(rangeI)
            }
        }
        return result.toList()
    }

    fun isInvalid(num: Long): Boolean {
        val numStr = num.toString()
        for (i in 1 .. (numStr.length/2)) {
            val amountOfCopies: Int = numStr.length/i
            if (amountOfCopies * i != numStr.length) continue
            val first = numStr.take(i)
            if (numStr.replace(first, "").isEmpty()){
                return true
            }
        }
        return false
    }
}