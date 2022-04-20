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
        fail("round")
        fail("floor")
        fail("ceil")
        
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
