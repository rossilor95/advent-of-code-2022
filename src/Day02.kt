fun main() {
    fun part1(input: List<String>): Int {
        fun calculateShapeScore(myShape: Char) = when (myShape) {
            'X' -> 1
            'Y' -> 2
            'Z' -> 3
            else -> error("Check your inputs!")
        }

        fun calculateRoundScore(shapes: String): Int = when (shapes) {
            "A Y", "B Z", "C X" -> 6
            "A X", "B Y", "C Z" -> 3
            "A Z", "B X", "C Y" -> 0
            else -> error("Check your inputs!")
        }

        return input.sumOf { round ->
            calculateShapeScore(myShape = round[2]) + calculateRoundScore(round)
        }
    }

    val testInput = readInput("Day02_test")
    val input = readInput("Day02")

    check(part1(testInput) == 15)
    println(part1(input))

//    check(part2(testInput) == 45000)
//    println(part2(input))
}