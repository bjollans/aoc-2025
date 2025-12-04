import helpers.getLinesForDay

class Day3Solver: Runnable {
    override fun run() {
        val batteryPacks = getLinesForDay("03", false)
        val joltageSum = batteryPacks.sumOf { maxJoltage(it, 12) }
        println("Got sum of max joltages $joltageSum")
    }

    fun maxJoltage(batteryPack: String, batteryAmount: Int): Long {
        var batteryPackList = batteryPackStrToIntList(batteryPack)
        val digits = mutableListOf<Int>()
        for (i in batteryAmount-1 downTo 0) {
            val nextDigit = batteryPackList.dropLast(i).max()
            digits.add(nextDigit)
            batteryPackList = batteryPackList.drop(batteryPackList.indexOf(digits.last()) + 1)
        }
        return digits.joinToString("") { it.toString() }.toLong()
    }

    fun batteryPackStrToIntList(batteryPack: String): List<Int> {
        return batteryPack.toList().map { it.toString().toInt() }
    }

}