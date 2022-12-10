data class ProcedureStep(val cratesToMove: Int, val originStack: Int, val destinationStack: Int) {
    constructor(cratesToMove: String, originStack: String, destinationStack: String) : this(
        cratesToMove.toInt(), originStack.toInt() - 1, destinationStack.toInt() - 1
    )
}

fun main() {
    fun numberOfStacks(input: List<String>) = input.first {
        it.startsWith(" 1")
    }.split("  ").maxOf { stackIndex ->
        stackIndex.trim().toInt()
    }

    fun parseStacks(input: List<String>): List<ArrayDeque<Char>> {
        val stacks = List(numberOfStacks(input)) { ArrayDeque<Char>() }
        input.takeWhile {
            it.contains('[')
        }.map { line -> line.chunked(4) }.forEach { chunks ->
            chunks.withIndex().forEach { (index, chunk) ->
                if (chunk[1].isLetter()) stacks[index].add(chunk[1])
            }
        }
        return stacks
    }

    fun parseProcedure(input: List<String>): List<ProcedureStep> = input.dropWhile {
        !it.startsWith("move")
    }.map { row ->
        row.split(" ").let { parts ->
            ProcedureStep(parts[1], parts[3], parts[5])
        }
    }

    fun List<ArrayDeque<Char>>.moveCrates(procedureStep: ProcedureStep) = repeat(procedureStep.cratesToMove) {
        val crate = this[procedureStep.originStack].removeFirst()
        this[procedureStep.destinationStack].addFirst(crate)
    }

    fun List<ArrayDeque<Char>>.getCratesOnTop() = this.map { it.first() }.joinToString(separator = "")

    fun part1(input: List<String>): String {
        val stacks: List<ArrayDeque<Char>> = parseStacks(input)
        val procedure: List<ProcedureStep> = parseProcedure(input)
        for (procedureStep in procedure) {
            stacks.moveCrates(procedureStep)
        }
        return stacks.getCratesOnTop()
    }

    val testInput = readInput("Day05_test")
    val input = readInput("Day05")

    check(part1(testInput) == "CMZ")
    println("Part 1 Answer: ${part1(input)}")

//    check(part2(testInput) == 4)
//    println("Part 2 Answer: ${part2(input)}")
}
