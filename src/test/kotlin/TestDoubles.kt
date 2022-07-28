package mathx

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

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

        // Grad

        gradToTurns(0.0) shouldBe 0.0
        turnsToGrad(0.0) shouldBe 0.0

        gradToTurns(400.0) shouldBe 1.0
        turnsToGrad(1.0) shouldBe 400.0

        gradToTurns(10.0) shouldBe 0.025
        turnsToGrad(10.0) shouldBe 4000.0

        // Deg Rad

        radToDeg(0.0) shouldBe 0.0
        degToRad(0.0) shouldBe 0.0

        radToDeg(6.283185307179546) shouldBe 360.0
        degToRad(360.0) shouldBe 6.283185

        radToDeg(3.141592653589793) shouldBe 180.0
        degToRad(180.0) shouldBe 3.141592

        radToDeg(10.0) shouldBe 572.957795
        degToRad(10.0) shouldBe 0.174533

        // Deg Grad

        degToGrad(0.0) shouldBe 0.0
        gradToDeg(0.0) shouldBe 0.0

        degToGrad(360.0) shouldBe 400.0
        gradToDeg(400.0) shouldBe 360.0

        degToGrad(10.0) shouldBe 11.111111
        gradToDeg(10.0) shouldBe 9.0

        // Rad Grad

        radToGrad(0.0) shouldBe 0.0
        gradToRad(0.0) shouldBe 0.0

        radToGrad(6.283185307179546) shouldBe 400.0
        gradToRad(400.0) shouldBe 6.283185

        radToGrad(10.0) shouldBe 636.619772
        gradToRad(10.0) shouldBe 0.157080
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
    fun testChunks() {
        // 0, 16, 0
        chunkOffset(0.0, 16.0) shouldBe 0.0
        chunkStart(0.0, 16.0) shouldBe 0.0
        chunkIndex(0.0, 16.0) shouldBe 0.0

        // 1, 16, 0
        chunkOffset(1.0, 16.0) shouldBe 1.0
        chunkStart(1.0, 16.0) shouldBe 0.0
        chunkIndex(1.0, 16.0) shouldBe 0.0

        // 15, 16, 0
        chunkOffset(15.0, 16.0) shouldBe 15.0
        chunkStart(15.0, 16.0) shouldBe 0.0
        chunkIndex(15.0, 16.0) shouldBe 0.0

        // 16, 16, 0
        chunkOffset(16.0, 16.0) shouldBe 0.0
        chunkStart(16.0, 16.0) shouldBe 16.0
        chunkIndex(16.0, 16.0) shouldBe 1.0

        // 17, 16, 0
        chunkOffset(17.0, 16.0) shouldBe 1.0
        chunkStart(17.0, 16.0) shouldBe 16.0
        chunkIndex(17.0, 16.0) shouldBe 1.0

        // -1, 16, 0
        chunkOffset(-1.0, 16.0) shouldBe 15.0
        chunkStart(-1.0, 16.0) shouldBe -16.0
        chunkIndex(-1.0, 16.0) shouldBe -1.0

        // -15, 16, 0
        chunkOffset(-15.0, 16.0) shouldBe 1.0
        chunkStart(-15.0, 16.0) shouldBe -16.0
        chunkIndex(-15.0, 16.0) shouldBe -1.0

        // -16, 16, 0
        chunkOffset(-16.0, 16.0) shouldBe 0.0
        chunkStart(-16.0, 16.0) shouldBe -16.0
        chunkIndex(-16.0, 16.0) shouldBe -1.0

        // -17, 16, 0
        chunkOffset(-17.0, 16.0) shouldBe 15.0
        chunkStart(-17.0, 16.0) shouldBe -32.0
        chunkIndex(-17.0, 16.0) shouldBe -2.0
    }

    @Test
    fun testInterpolation() {
        fail("cerp")

        fail("lerp")

        fail("unlerp")

        fail("smooth step")

        fail("smoother step")

        fail("repeat")

        fail("reflect")
    }

    companion object {
        private infix fun Double.shouldBe(expected: Double) = shouldBe(expected, EPSILON)
        private fun Double.shouldBe(expected: Double, epsilon: Double) {
            assertEquals(expected, this, epsilon)
        }

        private const val EPSILON = 1e-6
    }
}
