fun main() {
    fun Char.toPriority(): Int = when {
        this.isLowerCase() -> this.code - 96 // UTF-16 codes for lowercase letters are 97..122
        this.isUpperCase() -> this.code - 38 // UTF-16 codes for uppercase letters are 65..90
        else -> error("Check your inputs!")
    }

    fun part1(input: List<String>) = input.map { rucksack ->
        rucksack.substring(0 until rucksack.length / 2) to rucksack.substring(rucksack.length / 2)
    }.flatMap { (first, second) ->
        first.toSet() intersect second.toSet()
    }.sumOf { it.toPriority() }

    fun part2(input: List<String>) = input.chunked(3).flatMap { (rucksack1, rucksack2, rucksack3) ->
            rucksack1.toSet() intersect rucksack2.toSet() intersect rucksack3.toSet()
        }.sumOf { it.toPriority() }


    val testInput = readInput("Day03_test")
    val input = readInput("Day03")

    check(part1(testInput) == 157)
    println("Part 1 Answer: ${part1(input)}")

    check(part2(testInput) == 70)
    println("Part 2 Answer: ${part2(input)}")
}