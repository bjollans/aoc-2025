import helpers.Grid
import helpers.getLinesForDay

class Day4Solver: Runnable {
    override fun run() {
        val grid = Grid(getLinesForDay("04", false))
        val accessibleCount = grid.countNeighbours('@','@').count { it < 4 }
        println("Can access $accessibleCount rolls")
    }
}