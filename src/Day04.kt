fun main() {
    fun String.toIntRange(): IntRange = this.substringBefore('-').toInt()..this.substringAfter('-').toInt()

    fun String.toIntRangePair(): Pair<IntRange, IntRange> =
        this.substringBefore(',').toIntRange() to this.substringAfter(',').toIntRange()

    infix fun IntRange.enclose(other: IntRange): Boolean = this.first <= other.first && this.last >= other.last

    infix fun IntRange.overlap(other: IntRange): Boolean = (this intersect other).isNotEmpty()

    fun part1(input: List<String>): Int = input.map { it.toIntRangePair() }.count { (assignments1, assignments2) ->
        (assignments1 enclose assignments2) || (assignments2 enclose assignments1)
    }

    fun part2(input: List<String>): Int = input.map { it.toIntRangePair() }.count { (assignments1, assignments2) ->
        assignments1 overlap assignments2
    }

    val testInput = readInput("Day04_test")
    val input = readInput("Day04")

    check(part1(testInput) == 2)
    println("Part 1 Answer: ${part1(input)}")

    check(part2(testInput) == 4)
    println("Part 2 Answer: ${part2(input)}")
}