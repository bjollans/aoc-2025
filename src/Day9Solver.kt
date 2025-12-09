import helpers.getLinesForDay
import kotlin.math.abs

class Day9Solver: Runnable {
    override fun run() {
        val lines = getLinesForDay("09", false)
        val coords = lines
            .map { it.split(",") }
            .map { Pair(it.first().toLong(), it.last().toLong()) }
        val sortedRectangles = getSortedRectangles(coords)
        println("largest size ${sortedRectangles.first().size}")
    }

    fun getSortedRectangles(coords: List<Pair<Long,Long>>): List<Rectangle> {
        val rectangles = mutableListOf<Rectangle>()
        for (k in 0 until coords.size) {
            for (l in k+1 until coords.size) {
                val cK = coords[k]
                val cL = coords[l]
                if (cL == cK) continue
                rectangles.add(Rectangle(cK, cL))
            }
        }
        val sortedRectangles = rectangles.sortedBy { it.size }.reversed()
        return sortedRectangles
    }

    class Rectangle(val c1: Pair<Long,Long>, val c2: Pair<Long,Long>) {
        val size = (abs(c1.first-c2.first) + 1) * (abs(c1.second-c2.second) + 1)
    }
}