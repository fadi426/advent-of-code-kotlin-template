package day01.day02

class SubmarineTask02(override var x: Int, override var y: Int, override var z: Int) : Submarine {
    override fun forward(movement: Int) {
        x += movement
        y += z * movement
    }

    override fun up(movement: Int) {
        z -= movement
    }

    override fun down(movement: Int) {
        z += movement
    }
}
