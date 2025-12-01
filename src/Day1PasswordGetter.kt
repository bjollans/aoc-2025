class Day1PasswordGetter : Runnable {
    override fun run() {
        val dial = Dial(size = 100, importantLocation = 0, initialPos = 50)
        val rotations = getLinesForDay("01", false)
        for (rotation in rotations) {
            dial.rotateSlowly(rotation)
        }
        println("${dial.importantLocation} was hit ${dial.importantLocationMeets} times.")
        println("${dial.importantLocation} was passed ${dial.importantLocationPasses} times.")
    }
}