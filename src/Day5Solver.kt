import helpers.IdRanges
import helpers.getLinesForDay

class Day5Solver : Runnable {
    override fun run() {
        val lines = getLinesForDay("05", false)
        val ranges = IdRanges(
            inputRanges = lines.filter { it.contains("-") }
                .map { it.split("-") }
                .map { it[0].toLong()..it[1].toLong() }
                .toList())
        val ingredients = lines.filter { !it.contains("-") }.map { it.toLong() }
        val spoiledCount = ingredients.count { it in ranges }
        val freshAmount = ranges.ranges.sumOf { it.last - it.first + 1 }
        println("Found $spoiledCount fresh with total of $freshAmount possible fresh")
    }
}