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
        println("Found $spoiledCount fresh")
    }
}