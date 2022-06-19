package year2021.day01.day02

interface Submarine {
    var x: Int
    var y: Int
    var aim: Int

    fun move(commands: List<String>) {
        commands.forEach { command ->
            val matchResult = "([a-z]*)(\\s)(\\d*)".toRegex().find(command)!!.groups
            val movement = matchResult[3]!!.value.toInt()
            when (matchResult[1]!!.value) {
                "forward" -> forward(movement)
                "down" -> down(movement)
                "up" -> up(movement)
            }
        }
    }

    fun forward(movement: Int)

    fun up(movement: Int)

    fun down(movement: Int)

    fun result(): Int {
        return x * y
    }
}
