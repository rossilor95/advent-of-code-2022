fun main() {
    // Opponent shapes: A Rock, B Paper, C Scissors
    // My shapes: X Rock, Y Paper, Z Scissors
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

    // Opponent shapes: A Rock, B Paper, C Scissors
    // Round outcome: X Lose, Y Draw, Z Win
    fun part2(input: List<String>): Int {
        fun calculateShapeScore(shapes: String): Int = when (shapes) {
            "A Y", "B X", "C Z" -> 1 // Rock 1 pt = Draw vs Rock, Lose vs Paper, Win vs Scissors,
            "A Z", "B Y", "C X" -> 2 // Paper 2 pts = Win vs Rock, Draw vs Paper, Lose vs Scissors
            "A X", "B Z", "C Y" -> 3 // Scissors 3 pts = Lose vs Rock, Win vs Paper, Draw vs Scissors
            else -> error("Check your inputs!")
        }

        fun calculateRoundScore(roundOutcome: Char): Int = when (roundOutcome) {
            'X' -> 0
            'Y' -> 3
            'Z' -> 6
            else -> error("Check your inputs!")
        }

        return input.sumOf { round ->
            calculateShapeScore(round) + calculateRoundScore(roundOutcome = round[2])
        }
    }

    val testInput = readInput("Day02_test")
    val input = readInput("Day02")

    check(part1(testInput) == 15)
    println("Part 1 Answer: ${part1(input)}")

    check(part2(testInput) == 12)
    println("Part 2 Answer: ${part2(input)}")
}