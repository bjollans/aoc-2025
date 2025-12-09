import helpers.getLinesForDay
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Day9Solver : Runnable {
    override fun run() {
        val lines = getLinesForDay("09", false)
        val redCoords = lines.map { it.split(",") }.map { Coord(it.first().toLong(), it.last().toLong()) }
        val sortedRectangles = getSortedRectangles(redCoords)
        val greenCoords = drawGreenLineFromRedCoords(redCoords).toMutableList()
        val allCoords = greenCoords + redCoords
        val gridConfig = GridConfig(allCoords)
        val largest = sortedRectangles.first { it.isInCoords(gridConfig) }
        println("largest for part 2 has size ${largest.size}")
    }

    fun drawGreenLineFromRedCoords(redCoords: List<Coord>): List<Coord> {
        val greenCoords = mutableListOf<Coord>()
        for ((rc1, rc2) in redCoords.zipWithNext() + listOf(Pair(redCoords.last(), redCoords.first()))) {
            when {
                (rc1.x == rc2.x && rc2.y > rc1.y) -> greenCoords.addAll(
                    (rc1.y..rc2.y).map { Coord(rc1.x, it) })

                (rc1.x == rc2.x && rc2.y < rc1.y) -> greenCoords.addAll(
                    (rc2.y..rc1.y).map { Coord(rc1.x, it) })

                (rc1.y == rc2.y && rc2.x > rc1.x) -> greenCoords.addAll(
                    (rc1.x..rc2.x).map { Coord(it, rc1.y) })

                (rc1.y == rc2.y && rc2.x < rc1.x) -> greenCoords.addAll(
                    (rc2.x..rc1.x).map { Coord(it, rc1.y) })
            }
        }
        return greenCoords.distinct()
    }

    fun getSortedRectangles(coords: List<Coord>): List<Rectangle> {
        val rectangles = mutableListOf<Rectangle>()
        for (k in 0 until coords.size) {
            for (l in k + 1 until coords.size) {
                val cK = coords[k]
                val cL = coords[l]
                if (cL == cK) continue
                rectangles.add(Rectangle(cK, cL))
            }
        }
        val sortedRectangles = rectangles.sortedBy { it.size }.reversed()
        return sortedRectangles
    }

    data class Coord(val x: Long, val y: Long) {
        operator fun plus(other: Coord): Coord {
            return Coord(x + other.x, y + other.y)
        }
    }

    class Rectangle(val c1: Coord, val c2: Coord) {
        val size = (abs(c1.x - c2.x) + 1) * (abs(c1.y - c2.y) + 1)

        fun isInCoords(gridConfig: GridConfig): Boolean {
            println("Checking is in coords")
            for (y in min(c1.y, c2.y)..max(c1.y, c2.y)) {
                for (x in listOf(min(c1.x, c2.x), max(c1.x, c2.x))) {
                    if (!gridConfig.withinCoords(Coord(x, y))) return false
                }
            }
            for (x in min(c1.x, c2.x)..max(c1.x, c2.x)) {
                for (y in listOf(min(c1.y, c2.y), max(c1.y, c2.y))) {
                    if (!gridConfig.withinCoords(Coord(x, y))) return false
                }
            }
            return true
        }
    }

    class GridConfig(coords: List<Coord>) {
        private val columns = mutableMapOf<Long, Pair<Long, Long>>()
        private val rows = mutableMapOf<Long, Pair<Long, Long>>()

        init {
            for (it in coords) {
                if (columns[it.x] == null) {
                    columns[it.x] = Pair(it.y, it.y)
                } else {
                    columns[it.x] = Pair(
                        min(it.y, columns.getValue(it.x).first),
                        max(it.y, columns.getValue(it.x).second)
                    )
                }

                if (rows[it.y] == null) {
                    rows[it.y] = Pair(it.x, it.x)
                } else {
                    rows[it.y] = Pair(
                        min(it.x, rows.getValue(it.y).first),
                        max(it.x, rows.getValue(it.y).second)
                    )
                }
            }
        }

        fun withinCoords(c: Coord): Boolean {
            return c.x in columns && c.y in rows
                    && c.y in columns.getValue(c.x).first..columns.getValue(c.x).second
                    && c.x in rows.getValue(c.y).first..rows.getValue(c.y).second
        }
    }
}