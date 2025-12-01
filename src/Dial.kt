class Dial(val size: Int, val importantLocation: Int, val initialPos: Int) {
    var pos = initialPos;
    var importantLocationMeets = 0;

    fun rotate(rotation: String) {
        val direction = rotation.first()
        val amount = rotation.drop(1).toInt()
        when (direction) {
            'L' -> pos = (pos-amount).mod(size)
            'R' -> pos = (pos+amount).mod(size)
        }
        if (pos == importantLocation) {
            importantLocationMeets++
        }
    }
}