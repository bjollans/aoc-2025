import helpers.Grid
import helpers.GridElement
import helpers.getLinesForDay

class Day4Solver: Runnable {
    override fun run() {
        val grid = Grid(getLinesForDay("04", false))
        val accessibleElements = mutableListOf<GridElement>()
        while (true) {
            val nextElements = grid.getWithNeighbourCount('@','@', 4)
            nextElements.forEach { it.content='.' }
            if (nextElements.isEmpty()) break
            accessibleElements.addAll(nextElements)
        }
        println("Can access ${accessibleElements.size} rolls")
    }
}