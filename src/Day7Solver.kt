import helpers.IndexRow
import helpers.getLinesForDay

class Day7Solver: Runnable {
    override fun run() {
        val lines = getLinesForDay("07", false)
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