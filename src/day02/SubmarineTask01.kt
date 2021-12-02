package day01.day02

class SubmarineTask01(override var x: Int, override var y: Int, override var aim: Int) : Submarine {
    override fun forward(movement: Int) {
        x += movement
    }

    override fun up(movement: Int) {
        y -= movement
    }

    override fun down(movement: Int) {
        y += movement
    }
}
