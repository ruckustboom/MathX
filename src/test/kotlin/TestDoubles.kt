package mathx

import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class TestDoubles {
    @Test
    fun testTrig() {
        radToDeg(0.0) shouldBe 0.0
        degToRad(0.0) shouldBe 0.0

        radToDeg(TAU) shouldBe 360.0
        degToRad(360.0) shouldBe TAU

        radToDeg(PI) shouldBe 180.0
        degToRad(180.0) shouldBe PI

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
    }

    @Test
    fun testLength() {
        fail()
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
        fail()
    }

    companion object {
        private infix fun Double.shouldBe(expected: Double) = shouldBe(expected, EPSILON)
        private fun Double.shouldBe(expected: Double, epsilon: Double) {
            assertEquals(expected, this, epsilon)
        }

        private const val EPSILON = 1e-6
    }
}
