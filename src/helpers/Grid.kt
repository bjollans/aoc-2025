package helpers


enum class Direction {
    UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
}

class Grid(inputLines: List<String>) {
    var gridElements: List<List<GridElement>> = inputLines
        .map { line ->
            line.toCharArray()
                .map { GridElement(content = it, neighbours = emptyMap()) }
        }

    init {
        for ((y, row) in gridElements.withIndex()) {
            for ((x, el) in row.withIndex()) {
                el.neighbours = mapOf<Direction, GridElement?>(
                    Direction.UP to getAtCoordinate(x, y - 1),
                    Direction.DOWN to getAtCoordinate(x, y + 1),
                    Direction.LEFT to getAtCoordinate(x - 1, y),
                    Direction.RIGHT to getAtCoordinate(x + 1, y),
                    Direction.UP_LEFT to getAtCoordinate(x - 1, y - 1),
                    Direction.UP_RIGHT to getAtCoordinate(x + 1, y - 1),
                    Direction.DOWN_LEFT to getAtCoordinate(x - 1, y + 1),
                    Direction.DOWN_RIGHT to getAtCoordinate(x + 1, y + 1)
                )
            }
        }
    }

    fun getAtCoordinate(x: Int, y: Int): GridElement? = gridElements.getOrNull(y)?.getOrNull(x)

    fun countNeighbours(contentCondition: Char, neighbourContentCondition: Char): List<Int> {
        return gridElements.flatten()
            .filter { it.content == contentCondition }
            .map { it.neighbours.values.count { n -> n?.content == neighbourContentCondition } }
    }
}

class GridElement(val content: Char, var neighbours: Map<Direction, GridElement?>)