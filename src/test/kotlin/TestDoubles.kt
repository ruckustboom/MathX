package mathx

import kotlin.math.nextUp
import kotlin.test.Test
import kotlin.test.assertEquals

class TestDoubles {
    @Test
    fun testTrig() {
        // Deg

        degToTurns(0.0) shouldBe 0.0
        turnsToDeg(0.0) shouldBe 0.0

        degToTurns(360.0) shouldBe 1.0
        turnsToDeg(1.0) shouldBe 360.0

        degToTurns(10.0) shouldBe 0.027777
        turnsToDeg(10.0) shouldBe 3600.0

        // Rad

        radToTurns(0.0) shouldBe 0.0
        turnsToRad(0.0) shouldBe 0.0

        radToTurns(6.283185) shouldBe 1.0
        turnsToRad(1.0) shouldBe 6.283185

        radToTurns(10.0) shouldBe 1.591549
        turnsToRad(10.0) shouldBe 62.831853

        // Deg Rad

        radToDeg(0.0) shouldBe 0.0
        degToRad(0.0) shouldBe 0.0

        radToDeg(6.283185307179546) shouldBe 360.0
        degToRad(360.0) shouldBe 6.283185

        radToDeg(3.141592653589793) shouldBe 180.0
        degToRad(180.0) shouldBe 3.141592

        radToDeg(10.0) shouldBe 572.957795
        degToRad(10.0) shouldBe 0.174533
    }

    @Test
    fun testRounding() {
        round(0.0, 1.0) shouldBe 0.0
        round(1.0, 1.0) shouldBe 1.0
        round(0.1, 1.0) shouldBe 0.0
        round(0.9, 1.0) shouldBe 1.0
        round(1.1, 1.0) shouldBe 1.0
        round(1.5, 1.0) shouldBe 2.0
        round(2.5, 1.0) shouldBe 2.0
        round(17.318, 1.0) shouldBe 17.0
        round(17.318, 2.0) shouldBe 18.0
        round(17.318, 4.1) shouldBe 16.4
        round(-17.318, 4.1) shouldBe -16.4

        floor(0.0, 1.0) shouldBe 0.0
        floor(1.0, 1.0) shouldBe 1.0
        floor(0.1, 1.0) shouldBe 0.0
        floor(0.9, 1.0) shouldBe 0.0
        floor(1.1, 1.0) shouldBe 1.0
        floor(1.5, 1.0) shouldBe 1.0
        floor(2.5, 1.0) shouldBe 2.0
        floor(17.318, 1.0) shouldBe 17.0
        floor(17.318, 2.0) shouldBe 16.0
        floor(17.318, 4.1) shouldBe 16.4
        floor(-17.318, 4.1) shouldBe -20.5

        ceil(0.0, 1.0) shouldBe 0.0
        ceil(1.0, 1.0) shouldBe 1.0
        ceil(0.1, 1.0) shouldBe 1.0
        ceil(0.9, 1.0) shouldBe 1.0
        ceil(1.1, 1.0) shouldBe 2.0
        ceil(1.5, 1.0) shouldBe 2.0
        ceil(2.5, 1.0) shouldBe 3.0
        ceil(17.318, 1.0) shouldBe 18.0
        ceil(17.318, 2.0) shouldBe 18.0
        ceil(17.318, 4.1) shouldBe 20.5
        ceil(-17.318, 4.1) shouldBe -16.4

        nearest(0.0, 1.0, 5.0) shouldBe 1.0
        nearest(1.0, 1.0, 5.0) shouldBe 1.0
        nearest(2.0, 1.0, 5.0) shouldBe 1.0
        nearest(3.0, 1.0, 5.0) shouldBe 1.0
        nearest(4.0, 1.0, 5.0) shouldBe 5.0
        nearest(5.0, 1.0, 5.0) shouldBe 5.0
        nearest(6.0, 1.0, 5.0) shouldBe 5.0

        nearest(0L, 1L, 5L) shouldBe 1L
        nearest(1L, 1L, 5L) shouldBe 1L
        nearest(2L, 1L, 5L) shouldBe 1L
        nearest(3L, 1L, 5L) shouldBe 1L
        nearest(4L, 1L, 5L) shouldBe 5L
        nearest(5L, 1L, 5L) shouldBe 5L
        nearest(6L, 1L, 5L) shouldBe 5L
    }

    @Test
    fun testLength() {
        // 2D

        length(0.0, 0.0) shouldBe 0.0
        length(1.0, 0.0) shouldBe 1.0
        length(0.0, 1.0) shouldBe 1.0
        length(0.617, 0.0) shouldBe 0.617
        length(0.0, -9.124) shouldBe 9.124
        length(1.0, 1.0) shouldBe 1.414214
        length(3.0, 4.0) shouldBe 5.0
        length(17.3, -23.996) shouldBe 29.582056

        // 3D

        length(0.0, 0.0, 0.0) shouldBe 0.0
        length(1.0, 0.0, 0.0) shouldBe 1.0
        length(0.0, 1.0, 0.0) shouldBe 1.0
        length(0.0, 0.0, 1.0) shouldBe 1.0
        length(0.617, 0.0, 0.0) shouldBe 0.617
        length(0.0, -9.124, 0.0) shouldBe 9.124
        length(0.0, 0.0, -0.001) shouldBe 0.001
        length(1.0, 1.0, 1.0) shouldBe 1.732051
        length(2.0, 3.0, 6.0) shouldBe 7.0
        length(17.3, -23.996, 3.14) shouldBe 29.748238

        // Other

        length(0.0) shouldBe 0.0
        length(1.0) shouldBe 1.0
        length(-1.0) shouldBe 1.0
        length(0.617) shouldBe 0.617
        length(-9.124) shouldBe 9.124
        length(doubleArrayOf(17.3, -23.996, 3.14)) shouldBe 29.748238
        length(1.0, 1.0, 1.0, 1.0) shouldBe 2.0
    }

    @Test
    fun testInterpolation() {
        cerp(0.0, 5.0, 7.0) shouldBe 5.0
        cerp(1.0, 5.0, 7.0) shouldBe 7.0
        cerp(0.5, 5.0, 7.0) shouldBe 5.0
        cerp(0.5, 5.0, 7.0, 0.5) shouldBe 7.0
        cerp(0.5, 5.0, 7.0, 0.5.nextUp()) shouldBe 5.0
        cerp(0.0, 5.0, 7.0, 0.0) shouldBe 7.0

        lerp(0.0, 5.0, 7.0) shouldBe 5.0
        lerp(1.0, 5.0, 7.0) shouldBe 7.0
        lerp(0.5, 5.0, 7.0) shouldBe 6.0
        lerp(2.0, 5.0, 7.0) shouldBe 9.0
        lerp(-0.5, 5.0, 7.0) shouldBe 4.0

        unlerp(5.0, 5.0, 7.0) shouldBe 0.0
        unlerp(7.0, 5.0, 7.0) shouldBe 1.0
        unlerp(6.0, 5.0, 7.0) shouldBe 0.5
        unlerp(9.0, 5.0, 7.0) shouldBe 2.0
        unlerp(4.0, 5.0, 7.0) shouldBe -0.5

        smoothStep(0.0) shouldBe 0.0
        smoothStep(1.0) shouldBe 1.0
        smoothStep(0.5) shouldBe 0.5
        smoothStep(0.25) shouldBe 0.15625
        smoothStep(0.75) shouldBe 0.84375

        smoothStep(5.0, 5.0, 7.0) shouldBe 0.0
        smoothStep(7.0, 5.0, 7.0) shouldBe 1.0
        smoothStep(6.0, 5.0, 7.0) shouldBe 0.5
        smoothStep(5.5, 5.0, 7.0) shouldBe 0.15625
        smoothStep(6.5, 5.0, 7.0) shouldBe 0.84375

        smootherStep(0.0) shouldBe 0.0
        smootherStep(1.0) shouldBe 1.0
        smootherStep(0.5) shouldBe 0.5
        smootherStep(0.25) shouldBe 0.103516
        smootherStep(0.75) shouldBe 0.896484

        smootherStep(5.0, 5.0, 7.0) shouldBe 0.0
        smootherStep(7.0, 5.0, 7.0) shouldBe 1.0
        smootherStep(6.0, 5.0, 7.0) shouldBe 0.5
        smootherStep(5.5, 5.0, 7.0) shouldBe 0.103516
        smootherStep(6.5, 5.0, 7.0) shouldBe 0.896484

        repeat(0.0) shouldBe 0.0
        repeat(1.0) shouldBe 0.0
        repeat(0.5) shouldBe 0.5
        repeat(1.1) shouldBe 0.1
        repeat(2.1) shouldBe 0.1
        repeat(-0.1) shouldBe 0.9
        repeat(-1.1) shouldBe 0.9

        repeat(5.0, 5.0, 7.0) shouldBe 5.0
        repeat(7.0, 5.0, 7.0) shouldBe 5.0
        repeat(6.0, 5.0, 7.0) shouldBe 6.0
        repeat(7.1, 5.0, 7.0) shouldBe 5.1
        repeat(8.1, 5.0, 7.0) shouldBe 6.1
        repeat(9.1, 5.0, 7.0) shouldBe 5.1
        repeat(4.9, 5.0, 7.0) shouldBe 6.9
        repeat(3.9, 5.0, 7.0) shouldBe 5.9
        repeat(2.9, 5.0, 7.0) shouldBe 6.9
        repeat(-0.1, 5.0, 7.0) shouldBe 5.9
        repeat(-1.1, 5.0, 7.0) shouldBe 6.9

        reflect(0.0) shouldBe 0.0
        reflect(1.0) shouldBe 1.0
        reflect(0.5) shouldBe 0.5
        reflect(1.1) shouldBe 0.9
        reflect(2.1) shouldBe 0.1
        reflect(-0.1) shouldBe 0.1
        reflect(-1.1) shouldBe 0.9

        reflect(5.0, 5.0, 7.0) shouldBe 5.0
        reflect(7.0, 5.0, 7.0) shouldBe 7.0
        reflect(6.0, 5.0, 7.0) shouldBe 6.0
        reflect(7.1, 5.0, 7.0) shouldBe 6.9
        reflect(8.1, 5.0, 7.0) shouldBe 5.9
        reflect(9.1, 5.0, 7.0) shouldBe 5.1
        reflect(4.9, 5.0, 7.0) shouldBe 5.1
        reflect(3.9, 5.0, 7.0) shouldBe 6.1
        reflect(2.9, 5.0, 7.0) shouldBe 6.9
        reflect(-0.1, 5.0, 7.0) shouldBe 6.1
        reflect(-1.1, 5.0, 7.0) shouldBe 6.9
    }

    companion object {
        private const val EPSILON = 1e-6
        private infix fun Double.shouldBe(expected: Double) = shouldBe(expected, EPSILON)
        private fun Double.shouldBe(expected: Double, epsilon: Double) {
            assertEquals(expected, this, epsilon)
        }

        private infix fun Long.shouldBe(expected: Long) = assertEquals(expected, this)
    }
}
