import helpers.IndexRow
import helpers.getLinesForDay

class Day7Solver: Runnable {
    override fun run() {
        part2()
    }

    fun part2() {
        val lines = getLinesForDay("07", false)
        val initialBeamIndexRow = IndexRow(lines[0], 'S')
        val splitterIndexRows = lines.filter { '^' in it }.map { IndexRow(it, '^') }.reversed()
        var scoreRow = lines.last().map { 1L }
        for (splitterIndexRow in splitterIndexRows) {
            scoreRow = scoreRow.indices.map { i ->
                if (i in splitterIndexRow)
                    scoreRow[i-1] + scoreRow[i+1]
                else
                    scoreRow[i] }
        }
        val finalScore = scoreRow[initialBeamIndexRow.indices.first()]
        println("Counted $finalScore timelines")
    }

    fun part1() {
        val lines = getLinesForDay("07", true)
        val initialBeamIndexRow = IndexRow(lines[0], 'S')
        val splitterIndexRows = lines.filter { '^' in it }.map { IndexRow(it, '^') }
        var currentBeamIndexRow = initialBeamIndexRow
        var splitCount = 0
        for (splitterIndexRow in splitterIndexRows) {
            val collisions = currentBeamIndexRow.intersect(splitterIndexRow)
            splitCount += collisions.size
            val beamsThatMissedAllSplitters = currentBeamIndexRow.subtract(collisions)
            val beamsAfterSplitters = collisions.splitIndices()
            currentBeamIndexRow = beamsAfterSplitters.union(beamsThatMissedAllSplitters)
        }
        println("Counted $splitCount splits")
    }
}