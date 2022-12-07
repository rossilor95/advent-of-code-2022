import java.io.File

enum class Marker(val size: Int) {
    START_OF_PACKET_MARKER(4), START_OF_MESSAGE_MARKER(14)
}

fun main() {
    fun String.charsBeforeFirstMarker(markerSize: Int): Int = withIndex().windowed(markerSize, 1).first { window ->
        window.map { it.value }.toSet().size == markerSize
    }.last().index + 1

    fun part1(input: String) = input.charsBeforeFirstMarker(Marker.START_OF_PACKET_MARKER.size)

    fun part2(input: String) = input.charsBeforeFirstMarker(Marker.START_OF_MESSAGE_MARKER.size)

    val testInput = File("src/Day06_test.txt").readText()
    val input = File("src/Day06.txt").readText()

    check(part1(testInput) == 7)
    println("Part 1 Answer: ${part1(input)}")

    check(part2(testInput) == 19)
    println("Part 2 Answer: ${part2(input)}")
}