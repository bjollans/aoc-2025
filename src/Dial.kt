class Dial(val size: Int, val importantLocation: Int, val initialPos: Int) {
    var pos = initialPos;
    var importantLocationMeets = 0
    var importantLocationPasses = 0

    fun rotate(rotation: String) {
        val direction = rotation.first()
        val amount = rotation.drop(1).toInt()
        val prevPos = pos
        when (direction) {
            'L' -> pos = (pos-amount).mod(size)
            'R' -> pos = (pos+amount).mod(size)
        }
        if (pos == importantLocation) {
            importantLocationMeets++
        }
        if (prevPos != 0) {
            importantLocationPasses += when (direction) {
                'L' -> ((size - prevPos) + amount) / size
                'R' -> (prevPos + amount) / size
                else -> 0
            }
        }

//        println("${rotation} (pos: $pos): meets: $importantLocationMeets; passes: $importantLocationPasses")
    }

    fun rotateSlowly(rotation: String) {
        val direction = rotation.first()
        val amount = rotation.drop(1).toInt()
        for (i in 0..amount-1){
            when(direction) {
                'L' -> pos--
                'R' -> pos++
            }
            pos = (pos).mod(size)
            if (pos == importantLocation) {
                importantLocationPasses++
            }
        }
        if (pos == importantLocation) {
            importantLocationMeets++
        }
    }
}