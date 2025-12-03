class Day3JoltageProcessor: Runnable {
    override fun run() {
        val batteryPacks = getLinesForDay("03", false)
        val joltageSum = batteryPacks.map { maxJoltage(it) }.sum()
        println("Got sum of max joltages $joltageSum")
    }

    fun maxJoltage(batteryPack: String): Int {
        val batteryPackList = batteryPackStrToIntList(batteryPack)
        val firstDigit = batteryPackList.dropLast(1).max()
        val secondDigit = batteryPackList.drop(batteryPackList.indexOf(firstDigit) + 1).max()
        return 10 * firstDigit + secondDigit
    }

    fun batteryPackStrToIntList(batteryPack: String): List<Int> {
        return batteryPack.toList().map { it.toString().toInt() }
    }

}