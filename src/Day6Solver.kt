import helpers.getLinesForDay

class Day6Solver: Runnable {
    override fun run() {
        part2()
    }

    fun part1 () {
        val lines = getLinesForDay("06", false)
        val rows = lines.map { it.trim().split(Regex("\\s+")) }
        val numberRows = rows.dropLast(1).map { list -> list.map { it.toLong() } }
        val signRow = rows.last()
        val numberColumns = numberRows.first().indices.map { i -> numberRows.map { it[i] } }
        val results = numberColumns.map {
            it.reduce { a, b ->
                val sign = signRow[numberColumns.indexOf(it)]
                if (sign == "+") a + b else a * b
            }
        }
        val result = results.sum()
        println("Final result is $result")

    }

    fun part2 () {
        // !!!!!!MAKE SURE SPACES ARE NOT TRIMMED IN YOUR FILES
        // !!!!!!MAKE SURE SPACES ARE NOT TRIMMED IN YOUR FILES
        // !!!!!!MAKE SURE SPACES ARE NOT TRIMMED IN YOUR FILES
        val lines = getLinesForDay("06", false)
        val numberLines = lines.dropLast(1)
        val signLine = lines.last()
        val startIndices = signLine.toCharArray().zip(signLine.toCharArray().indices).filter { it.first != ' ' }.map { it.second }
        val startAndEndIndices = startIndices.zipWithNext().map { Pair(it.first, it.second-2) }.toMutableList()
        startAndEndIndices.add(Pair(startIndices.last(), signLine.length-1))
        var finalSum: Long = 0
        for ((start, end) in startAndEndIndices) {
            val numberCharArray = numberLines.map { line -> line.toCharArray().slice(start..end) }
            val transposed = numberCharArray.first().indices.map { i -> numberCharArray.map { it[i] } }
            val numbers = transposed.map { it.joinToString("").trim().toLong() }
            val sign = signLine.toCharArray()[start]
            val result = numbers.reduce { a,b -> if (sign == '+') a + b else a * b}
            finalSum += result
        }
        println("Final result is $finalSum")
    }
}