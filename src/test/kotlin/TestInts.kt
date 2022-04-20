package mathx

import kotlin.test.Test
import kotlin.test.assertEquals

class TestInts {
    @Test
    fun testRounding() {
        nearest(0, 1, 5) shouldBe 1
        nearest(1, 1, 5) shouldBe 1
        nearest(2, 1, 5) shouldBe 1
        nearest(3, 1, 5) shouldBe 1
        nearest(4, 1, 5) shouldBe 5
        nearest(5, 1, 5) shouldBe 5
        nearest(6, 1, 5) shouldBe 5

        nearest(0L, 1L, 5L) shouldBe 1L
        nearest(1L, 1L, 5L) shouldBe 1L
        nearest(2L, 1L, 5L) shouldBe 1L
        nearest(3L, 1L, 5L) shouldBe 1L
        nearest(4L, 1L, 5L) shouldBe 5L
        nearest(5L, 1L, 5L) shouldBe 5L
        nearest(6L, 1L, 5L) shouldBe 5L
    }

    @Test
    fun testIntChunks() {
        // 0, 16, 0
        chunkOffset(0, 16) shouldBe 0
        chunkStart(0, 16) shouldBe 0
        chunkIndex(0, 16) shouldBe 0

        // 1, 16, 0
        chunkOffset(1, 16) shouldBe 1
        chunkStart(1, 16) shouldBe 0
        chunkIndex(1, 16) shouldBe 0

        // 15, 16, 0
        chunkOffset(15, 16) shouldBe 15
        chunkStart(15, 16) shouldBe 0
        chunkIndex(15, 16) shouldBe 0

        // 16, 16, 0
        chunkOffset(16, 16) shouldBe 0
        chunkStart(16, 16) shouldBe 16
        chunkIndex(16, 16) shouldBe 1

        // 17, 16, 0
        chunkOffset(17, 16) shouldBe 1
        chunkStart(17, 16) shouldBe 16
        chunkIndex(17, 16) shouldBe 1

        // -1, 16, 0
        chunkOffset(-1, 16) shouldBe 15
        chunkStart(-1, 16) shouldBe -16
        chunkIndex(-1, 16) shouldBe -1

        // -15, 16, 0
        chunkOffset(-15, 16) shouldBe 1
        chunkStart(-15, 16) shouldBe -16
        chunkIndex(-15, 16) shouldBe -1

        // -16, 16, 0
        chunkOffset(-16, 16) shouldBe 0
        chunkStart(-16, 16) shouldBe -16
        chunkIndex(-16, 16) shouldBe -1

        // -17, 16, 0
        chunkOffset(-17, 16) shouldBe 15
        chunkStart(-17, 16) shouldBe -32
        chunkIndex(-17, 16) shouldBe -2
    }

    @Test
    fun testLongChunks() {
        // 0, 16, 0
        chunkOffset(0L, 16L) shouldBe 0L
        chunkStart(0L, 16L) shouldBe 0L
        chunkIndex(0L, 16L) shouldBe 0L

        // 1, 16, 0
        chunkOffset(1L, 16L) shouldBe 1L
        chunkStart(1L, 16L) shouldBe 0L
        chunkIndex(1L, 16L) shouldBe 0L

        // 15, 16, 0
        chunkOffset(15L, 16L) shouldBe 15L
        chunkStart(15L, 16L) shouldBe 0L
        chunkIndex(15L, 16L) shouldBe 0L

        // 16, 16, 0
        chunkOffset(16L, 16L) shouldBe 0L
        chunkStart(16L, 16L) shouldBe 16L
        chunkIndex(16L, 16L) shouldBe 1L

        // 17, 16, 0
        chunkOffset(17L, 16L) shouldBe 1L
        chunkStart(17L, 16L) shouldBe 16L
        chunkIndex(17L, 16L) shouldBe 1L

        // -1, 16, 0
        chunkOffset(-1L, 16L) shouldBe 15L
        chunkStart(-1L, 16L) shouldBe -16L
        chunkIndex(-1L, 16L) shouldBe -1L

        // -15, 16, 0
        chunkOffset(-15L, 16L) shouldBe 1L
        chunkStart(-15L, 16L) shouldBe -16L
        chunkIndex(-15L, 16L) shouldBe -1L

        // -16, 16, 0
        chunkOffset(-16L, 16L) shouldBe 0L
        chunkStart(-16L, 16L) shouldBe -16L
        chunkIndex(-16L, 16L) shouldBe -1L

        // -17, 16, 0
        chunkOffset(-17L, 16L) shouldBe 15L
        chunkStart(-17L, 16L) shouldBe -32L
        chunkIndex(-17L, 16L) shouldBe -2L
    }

    companion object {
        private infix fun Int.shouldBe(expected: Int) = assertEquals(expected, this)
        private infix fun Long.shouldBe(expected: Long) = assertEquals(expected, this)
    }
}
