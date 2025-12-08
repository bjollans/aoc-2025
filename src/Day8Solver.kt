import helpers.JunctionBox
import helpers.JunctionBoxGroup
import helpers.getLinesForDay

class Day8Solver: Runnable {
    override fun run() {
        val isTest = false
        val lines = getLinesForDay("08", isTest)
        val boxes = lines
            .map { l->l.split(",").map { it.toDouble() } }
            .map { JunctionBox(it[0],it[1],it[2]) }
        val sortedDistanceTuples = getSortedDistanceTuples(boxes)
        val boxToGroup = mutableMapOf<JunctionBox, JunctionBoxGroup>()
        val amountOfBoxes = if(isTest) 10 else 1000
        for (i in 0 until amountOfBoxes) {
            val box1 = sortedDistanceTuples[i].first
            val box2 = sortedDistanceTuples[i].second
            val boxGroup1 = boxToGroup[box1]
            val boxGroup2 = boxToGroup[box2]
            when {
                (boxGroup1 != null && boxGroup2 != null && boxGroup1 == boxGroup2) -> {
                    continue
                }
                (boxGroup1 != null && boxGroup2 != null) -> {
                    boxGroup1 += boxGroup2
                    for (box in boxGroup2.boxes) {
                        boxToGroup[box] = boxGroup1
                    }
                }
                boxGroup1 != null -> {
                    boxGroup1 += box2
                    boxToGroup[box2] = boxGroup1
                }
                boxGroup2 != null -> {
                    boxGroup2 += box1
                    boxToGroup[box1] = boxGroup2
                }
                else -> {
                    val newGroup = JunctionBoxGroup(box1)
                    newGroup += box2
                    boxToGroup[box2] = newGroup
                    boxToGroup[box1] = newGroup
                }
            }
        }
        val boxGroups = boxToGroup.values.toSet().toList()
        val sizes = boxGroups.map { it.size.toLong() }.sorted().reversed()
        print(sizes[0]*sizes[1]*sizes[2])
    }

    fun getSortedDistanceTuples(boxes: List<JunctionBox>): List<Pair<JunctionBox, JunctionBox>> {
        val boxDistanceTriples = mutableListOf<Triple<Double, JunctionBox, JunctionBox>>()
        for (k in 0 until boxes.size) {
            for (l in k+1 until boxes.size) {
                val boxK = boxes[k]
                val boxL = boxes[l]
                if (boxL == boxK) continue
                boxDistanceTriples.add(Triple(boxK.distance(boxL), boxK, boxL))
            }
        }
        val sortedDistanceList = boxDistanceTriples.sortedBy { it.first }
        return sortedDistanceList.map { Pair(it.second, it.third) }
    }
}