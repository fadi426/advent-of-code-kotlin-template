package util

fun assertTrue(bool: Boolean) {
    if (!bool) throw Exception("Objects don't match!")
}
