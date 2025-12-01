import java.io.File

fun getLinesForDay(day: String, test: Boolean): List<String> {
    return if(test) {
        File("inputs/$day/test.txt").readLines()
    } else {
        File("inputs/$day/main.txt").readLines()
    }
}