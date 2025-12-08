package helpers

import kotlin.math.pow
import kotlin.math.sqrt

class JunctionBox(val x: Double, val y: Double, val z: Double) {
    fun distance(other: JunctionBox): Double {
        return sqrt(
            (other.x - x).pow(2) + (other.y - y).pow(2) + (other.z - z).pow(2)
        )
    }
}

class JunctionBoxGroup {
    val boxes = mutableListOf<JunctionBox>()

    val size
        get() = boxes.distinct().size

    constructor(initialBox: JunctionBox) {
        this.boxes.add(initialBox)
    }

    constructor(inputBoxes: List<JunctionBox>) {
        boxes.addAll(inputBoxes)
    }

    operator fun contains(x: JunctionBox): Boolean {
        return x in boxes
    }

    operator fun plus(other: JunctionBoxGroup): JunctionBoxGroup {
        return JunctionBoxGroup(this.boxes + other.boxes)
    }

    operator fun plus(other: JunctionBox): JunctionBoxGroup {
        val newList = mutableListOf<JunctionBox>()
        newList.addAll(this.boxes)
        newList.add(other)
        return JunctionBoxGroup(newList.distinct())
    }

    operator fun plusAssign(other: JunctionBox) {
        this.boxes.add(other)
    }

    operator fun plusAssign(other: JunctionBoxGroup) {
        this.boxes.addAll(other.boxes)
    }
}