import java.io.File

fun main() {
    fun part1(input: String): Int {
        val data = input.split("\n\n").map { elf ->
                elf.split("\n").map { calory -> calory.toInt() }
            }
        return data.maxOf { it.sum() }
    }

    fun part2(input: String): Int {
        val data = input.split("\n\n").map { elf ->
                elf.split("\n").map { calory -> calory.toInt() }
            }.map { it.sum() }
        return data.sortedDescending().subList(0, 3).sum()
    }

    val testInput = File("src/Day01_test.txt").readText()
    val input = File("src/Day01.txt").readText()

    check(part1(testInput) == 24000)
    println(part1(input))

    check(part2(testInput) == 45000)
    println(part2(input))
}
