package mathx

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class TestFloats {
    @Test
    fun testTrig() {
        radToDeg(0F) shouldBe 0F
        degToRad(0F) shouldBe 0F

        radToDeg(TAU_F) shouldBe 360F
        degToRad(360F) shouldBe TAU_F

        radToDeg(PI_F) shouldBe 180F
        degToRad(180F) shouldBe PI_F

        radToDeg(10F) shouldBe 572.9578F
        degToRad(10F) shouldBe 0.1745F
    }

    @Test
    fun testRounding() {
        round(0.0F, 1.0F) shouldBe 0.0F
        round(1.0F, 1.0F) shouldBe 1.0F
        round(0.1F, 1.0F) shouldBe 0.0F
        round(0.9F, 1.0F) shouldBe 1.0F
        round(1.1F, 1.0F) shouldBe 1.0F
        round(1.5F, 1.0F) shouldBe 2.0F
        round(2.5F, 1.0F) shouldBe 2.0F
        round(17.318F, 1.0F) shouldBe 17.0F
        round(17.318F, 2.0F) shouldBe 18.0F
        round(17.318F, 4.1F) shouldBe 16.4F
        round(-17.318F, 4.1F) shouldBe -16.4F

        floor(0.0F, 1.0F) shouldBe 0.0F
        floor(1.0F, 1.0F) shouldBe 1.0F
        floor(0.1F, 1.0F) shouldBe 0.0F
        floor(0.9F, 1.0F) shouldBe 0.0F
        floor(1.1F, 1.0F) shouldBe 1.0F
        floor(1.5F, 1.0F) shouldBe 1.0F
        floor(2.5F, 1.0F) shouldBe 2.0F
        floor(17.318F, 1.0F) shouldBe 17.0F
        floor(17.318F, 2.0F) shouldBe 16.0F
        floor(17.318F, 4.1F) shouldBe 16.4F
        floor(-17.318F, 4.1F) shouldBe -20.5F

        ceil(0.0F, 1.0F) shouldBe 0.0F
        ceil(1.0F, 1.0F) shouldBe 1.0F
        ceil(0.1F, 1.0F) shouldBe 1.0F
        ceil(0.9F, 1.0F) shouldBe 1.0F
        ceil(1.1F, 1.0F) shouldBe 2.0F
        ceil(1.5F, 1.0F) shouldBe 2.0F
        ceil(2.5F, 1.0F) shouldBe 3.0F
        ceil(17.318F, 1.0F) shouldBe 18.0F
        ceil(17.318F, 2.0F) shouldBe 18.0F
        ceil(17.318F, 4.1F) shouldBe 20.5F
        ceil(-17.318F, 4.1F) shouldBe -16.4F

        nearest(0F, 1F, 5F) shouldBe 1F
        nearest(1F, 1F, 5F) shouldBe 1F
        nearest(2F, 1F, 5F) shouldBe 1F
        nearest(3F, 1F, 5F) shouldBe 1F
        nearest(4F, 1F, 5F) shouldBe 5F
        nearest(5F, 1F, 5F) shouldBe 5F
        nearest(6F, 1F, 5F) shouldBe 5F
    }

    @Test
    fun testLength() {
        fail()
    }

    @Test
    fun testChunks() {
        // 0, 16, 0
        chunkOffset(0F, 16F) shouldBe 0F
        chunkStart(0F, 16F) shouldBe 0F
        chunkIndex(0F, 16F) shouldBe 0F

        // 1, 16, 0
        chunkOffset(1F, 16F) shouldBe 1F
        chunkStart(1F, 16F) shouldBe 0F
        chunkIndex(1F, 16F) shouldBe 0F

        // 15, 16, 0
        chunkOffset(15F, 16F) shouldBe 15F
        chunkStart(15F, 16F) shouldBe 0F
        chunkIndex(15F, 16F) shouldBe 0F

        // 16, 16, 0
        chunkOffset(16F, 16F) shouldBe 0F
        chunkStart(16F, 16F) shouldBe 16F
        chunkIndex(16F, 16F) shouldBe 1F

        // 17, 16, 0
        chunkOffset(17F, 16F) shouldBe 1F
        chunkStart(17F, 16F) shouldBe 16F
        chunkIndex(17F, 16F) shouldBe 1F

        // -1, 16, 0
        chunkOffset(-1F, 16F) shouldBe 15F
        chunkStart(-1F, 16F) shouldBe -16F
        chunkIndex(-1F, 16F) shouldBe -1F

        // -15, 16, 0
        chunkOffset(-15F, 16F) shouldBe 1F
        chunkStart(-15F, 16F) shouldBe -16F
        chunkIndex(-15F, 16F) shouldBe -1F

        // -16, 16, 0
        chunkOffset(-16F, 16F) shouldBe 0F
        chunkStart(-16F, 16F) shouldBe -16F
        chunkIndex(-16F, 16F) shouldBe -1F

        // -17, 16, 0
        chunkOffset(-17F, 16F) shouldBe 15F
        chunkStart(-17F, 16F) shouldBe -32F
        chunkIndex(-17F, 16F) shouldBe -2F
    }

    @Test
    fun testInterpolation() {
        fail()
    }

    companion object {
        private infix fun Float.shouldBe(expected: Float) = shouldBe(expected, EPSILON)
        private fun Float.shouldBe(expected: Float, epsilon: Float) {
            assertEquals(expected, this, epsilon)
        }

        private const val EPSILON = 1e-4F
    }
}
