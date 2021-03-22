package mathx

import kotlin.math.abs

const val EPSILON = 1e-5

fun assertEquals(expected: Double, actual: Double, message: String, epsilon: Double = EPSILON) {
    assertEquals(expected, actual, epsilon) { "$message (expected $expected, actual $actual)" }
}

inline fun assertEquals(expected: Double, actual: Double, epsilon: Double = EPSILON, message: () -> String) {
    assert(abs(actual - expected) <= epsilon, message)
}

fun assertEquals(expected: Double, actual: Double, epsilon: Double = EPSILON) {
    assertEquals(expected, actual, epsilon) { "${abs(actual - expected)} > $epsilon" }
}
