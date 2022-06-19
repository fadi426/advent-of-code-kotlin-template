package year2021.day01.day02

class SubmarineTask02(override var x: Int, override var y: Int, override var aim: Int) : Submarine {
    override fun forward(movement: Int) {
        x += movement
        y += aim * movement
    }

    override fun up(movement: Int) {
        aim -= movement
    }

    override fun down(movement: Int) {
        aim += movement
    }
}
