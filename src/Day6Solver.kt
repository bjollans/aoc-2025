import helpers.getLinesForDay

class Day6Solver: Runnable {
    override fun run() {
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
}