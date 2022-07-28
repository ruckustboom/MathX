package mathx

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class TestFloats {
    @Test
    fun testTrig() {
        // Deg

        degToTurns(0F) shouldBe 0F
        turnsToDeg(0F) shouldBe 0F

        degToTurns(360F) shouldBe 1F
        turnsToDeg(1F) shouldBe 360F

        degToTurns(10F) shouldBe 0.027777F
        turnsToDeg(10F) shouldBe 3600F

        // Rad

        radToTurns(0F) shouldBe 0F
        turnsToRad(0F) shouldBe 0F

        radToTurns(6.283185F) shouldBe 1F
        turnsToRad(1F) shouldBe 6.283185F

        radToTurns(10F) shouldBe 1.591549F
        turnsToRad(10F) shouldBe 62.831853F

        // Grad

        gradToTurns(0F) shouldBe 0F
        turnsToGrad(0F) shouldBe 0F

        gradToTurns(400F) shouldBe 1F
        turnsToGrad(1F) shouldBe 400F

        gradToTurns(10F) shouldBe 0.025F
        turnsToGrad(10F) shouldBe 4000F

        // Deg Rad

        radToDeg(0F) shouldBe 0F
        degToRad(0F) shouldBe 0F

        radToDeg(6.2831855F) shouldBe 360F
        degToRad(360F) shouldBe 6.283185F

        radToDeg(3.1415927F) shouldBe 180F
        degToRad(180F) shouldBe 3.141592F

        radToDeg(10F) shouldBe 572.9578F
        degToRad(10F) shouldBe 0.174533F

        // Deg Grad

        degToGrad(0F) shouldBe 0F
        gradToDeg(0F) shouldBe 0F

        degToGrad(360F) shouldBe 400F
        gradToDeg(400F) shouldBe 360F

        degToGrad(10F) shouldBe 11.111111F
        gradToDeg(10F) shouldBe 9F

        // Rad Grad

        radToGrad(0F) shouldBe 0F
        gradToRad(0F) shouldBe 0F

        radToGrad(6.2831855F) shouldBe 400F
        gradToRad(400F) shouldBe 6.283185F

        radToGrad(10F) shouldBe 636.61975F
        gradToRad(10F) shouldBe 0.157080F
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
        // 2D

        length(0F, 0F) shouldBe 0F
        length(1F, 0F) shouldBe 1F
        length(0F, 1F) shouldBe 1F
        length(0.617F, 0F) shouldBe 0.617F
        length(0F, -9.124F) shouldBe 9.124F
        length(1F, 1F) shouldBe 1.414214F
        length(3F, 4F) shouldBe 5F
        length(17.3F, -23.996F) shouldBe 29.582056F

        // 3D

        length(0F, 0F, 0F) shouldBe 0F
        length(1F, 0F, 0F) shouldBe 1F
        length(0F, 1F, 0F) shouldBe 1F
        length(0F, 0F, 1F) shouldBe 1F
        length(0.617F, 0F, 0F) shouldBe 0.617F
        length(0F, -9.124F, 0F) shouldBe 9.124F
        length(0F, 0F, -0.001F) shouldBe 0.001F
        length(1F, 1F, 1F) shouldBe 1.732051F
        length(2F, 3F, 6F) shouldBe 7F
        length(17.3F, -23.996F, 3.14F) shouldBe 29.748238F

        // Other

        length(0F) shouldBe 0F
        length(1F) shouldBe 1F
        length(-1F) shouldBe 1F
        length(0.617F) shouldBe 0.617F
        length(-9.124F) shouldBe 9.124F
        length(floatArrayOf(17.3F, -23.996F, 3.14F)) shouldBe 29.748238F
        length(1F, 1F, 1F, 1F) shouldBe 2F
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
        fail("cerp")

        fail("lerp")

        fail("unlerp")

        fail("smooth step")

        fail("smoother step")

        fail("repeat")

        fail("reflect")
    }

    companion object {
        private infix fun Float.shouldBe(expected: Float) = shouldBe(expected, EPSILON)
        private fun Float.shouldBe(expected: Float, epsilon: Float) {
            assertEquals(expected, this, epsilon)
        }

        private const val EPSILON = 1e-4F
    }
}
