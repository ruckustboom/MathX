package mathx

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestRGBA {
    @Test
    fun testCreation() {
        RGBA(0, 0, 0, 0) shouldBe 0x00_00_00_00
        RGBA(0, 0, 0) shouldBe 0x00_00_00_FF
        RGBA(0xFF, 0, 0) shouldBe 0xFF_00_00_FF
        RGBA(0xFF, 0xFF, 0xFF) shouldBe 0xFF_FF_FF_FF
        RGBA(0x12, 0x34, 0x56, 0x78) shouldBe 0x12_34_56_78
        RGBA.gray(0xCC) shouldBe 0xCC_CC_CC_FF
    }

    @Test
    fun testBreak() {
        val color = RGBA(0x12, 0x34, 0x56, 0x78)
        RGBA.getRed(color) shouldBe 0x12
        RGBA.getGreen(color) shouldBe 0x34
        RGBA.getBlue(color) shouldBe 0x56
        RGBA.getAlpha(color) shouldBe 0x78
        assertEquals(0.18465, RGBA.getGray(color), EPSILON)
    }

    @Test
    fun testCopy() {
        val color = RGBA(0x12, 0x34, 0x56, 0x78)
        RGBA.copy(color, red = 0xFF) shouldBe 0xFF_34_56_78
        RGBA.copy(color, green = 0xFF) shouldBe 0x12_FF_56_78
        RGBA.copy(color, blue = 0xFF) shouldBe 0x12_34_FF_78
        RGBA.copy(color, alpha = 0xFF) shouldBe 0x12_34_56_FF
    }

    @Test
    fun testAlphaBlend() {
        val af = RGBA(0x12, 0x34, 0x45, 0xFF)
        val a0 = RGBA(0x12, 0x34, 0x45, 0x00)
        val a8 = RGBA(0x12, 0x34, 0x45, 0x78)
        val bf = RGBA.gray()
        val b0 = RGBA.gray(alpha = 0x00)
        val b8 = RGBA.gray(alpha = 0x80)

        // a over b

        RGBA.alphaBlend(af, bf) shouldBe af
        RGBA.alphaBlend(af, b0) shouldBe af
        RGBA.alphaBlend(af, b8) shouldBe af

        RGBA.alphaBlend(a0, bf) shouldBe bf
        RGBA.alphaBlend(a0, b0) shouldBe 0
        RGBA.alphaBlend(a0, b8) shouldBe b8

        RGBA.alphaBlend(a8, bf) shouldBe 0x4c_5C_64_FF
        RGBA.alphaBlend(a8, b0) shouldBe a8
        RGBA.alphaBlend(a8, b8) shouldBe 0x39_4F_5A_BC

        // b over a

        RGBA.alphaBlend(bf, af) shouldBe bf
        RGBA.alphaBlend(bf, a0) shouldBe bf
        RGBA.alphaBlend(bf, a8) shouldBe bf

        RGBA.alphaBlend(b0, af) shouldBe af
        RGBA.alphaBlend(b0, a0) shouldBe 0
        RGBA.alphaBlend(b0, a8) shouldBe a8

        RGBA.alphaBlend(b8, af) shouldBe 0x49_5A_63_FF
        RGBA.alphaBlend(b8, a0) shouldBe b8
        RGBA.alphaBlend(b8, a8) shouldBe 0x5D_68_6D_BC
    }

    @Test
    fun testAlphaBlendGammaCorrected() {
        val af = RGBA(0x12, 0x34, 0x45, 0xFF)
        val a0 = RGBA(0x12, 0x34, 0x45, 0x00)
        val a8 = RGBA(0x12, 0x34, 0x45, 0x78)
        val bf = RGBA.gray()
        val b0 = RGBA.gray(alpha = 0x00)
        val b8 = RGBA.gray(alpha = 0x80)

        // a over b

        RGBA.alphaBlendGammaCorrected(af, bf) shouldBe af
        RGBA.alphaBlendGammaCorrected(af, b0) shouldBe af
        RGBA.alphaBlendGammaCorrected(af, b8) shouldBe af

        RGBA.alphaBlendGammaCorrected(a0, bf) shouldBe bf
        RGBA.alphaBlendGammaCorrected(a0, b0) shouldBe 0
        RGBA.alphaBlendGammaCorrected(a0, b8) shouldBe b8

        RGBA.alphaBlendGammaCorrected(a8, bf) shouldBe 0x60_65_69_FF
        RGBA.alphaBlendGammaCorrected(a8, b0) shouldBe a8
        RGBA.alphaBlendGammaCorrected(a8, b8) shouldBe 0x51_59_5F_BC

        // b over a

        RGBA.alphaBlendGammaCorrected(bf, af) shouldBe bf
        RGBA.alphaBlendGammaCorrected(bf, a0) shouldBe bf
        RGBA.alphaBlendGammaCorrected(bf, a8) shouldBe bf

        RGBA.alphaBlendGammaCorrected(b0, af) shouldBe af
        RGBA.alphaBlendGammaCorrected(b0, a0) shouldBe 0
        RGBA.alphaBlendGammaCorrected(b0, a8) shouldBe a8

        RGBA.alphaBlendGammaCorrected(b8, af) shouldBe 0x5E_63_68_FF
        RGBA.alphaBlendGammaCorrected(b8, a0) shouldBe b8
        RGBA.alphaBlendGammaCorrected(b8, a8) shouldBe 0x6C_6F_71_BC
    }

    @Test
    fun testHex() {
        assertEquals("00000000", RGBA.toHex(0))
        assertEquals("ffffffff", RGBA.toHex(-1))
        assertEquals("808080ff", RGBA.toHex(RGBA.gray()))
        assertEquals("12345678", RGBA.toHex(RGBA(0x12, 0x34, 0x56, 0x78)))

        RGBA.fromHex("00000000") shouldBe 0
        RGBA.fromHex("ffffffff") shouldBe -1
        RGBA.fromHex("808080ff") shouldBe RGBA.gray()
        RGBA.fromHex("12345678") shouldBe RGBA(0x12, 0x34, 0x56, 0x78)
    }

    @Test
    fun testInterpolation() {
        val a = RGBA(0x12, 0x34, 0x56, 0x78)
        val b = RGBA.gray()

        RGBA.StraightAlphaInterpolator.interpolate(a, b, 0.0) shouldBe a
        RGBA.StraightAlphaInterpolator.interpolate(a, b, 1.0) shouldBe b
        RGBA.StraightAlphaInterpolator.interpolate(a, b, 0.5) shouldBe 0x49_5A_6B_BB
        RGBA.StraightAlphaInterpolator.interpolate(a, b, 0.25) shouldBe 0x2D_47_60_99
        RGBA.StraightAlphaInterpolator.interpolate(a, b, 0.75) shouldBe 0x64_6D75_DD
        repeat(101) {
            val forward = RGBA.StraightAlphaInterpolator.interpolate(a, b, it / 100.0)
            val backward = RGBA.StraightAlphaInterpolator.interpolate(b, a, 1.0 - it / 100.0)
            forward shouldBe backward
        }

        RGBA.PreMultipliedInterpolator.interpolate(a, b, 0.0) shouldBe a
        RGBA.PreMultipliedInterpolator.interpolate(a, b, 1.0) shouldBe b
        RGBA.PreMultipliedInterpolator.interpolate(a, b, 0.5) shouldBe 0x5D_68_73_BC
        RGBA.PreMultipliedInterpolator.interpolate(a, b, 0.25) shouldBe 0x3F53_67_9A
        RGBA.PreMultipliedInterpolator.interpolate(a, b, 0.75) shouldBe 0x71_76_7A_DE
        repeat(101) {
            val forward = RGBA.PreMultipliedInterpolator.interpolate(a, b, it / 100.0)
            val backward = RGBA.PreMultipliedInterpolator.interpolate(b, a, 1.0 - it / 100.0)
            forward shouldBe backward
        }
    }

    @Test
    fun testOther() {
        val color = RGBA(0x12, 0x34, 0x56, 0x78)
        RGBA.toGrayscale(color) shouldBe 0x2F_2F_2F_78
        RGBA.invert(color) shouldBe 0xED_CB_A9_78

        RGBA.toChannel(1.0) shouldBe 0xFF
        RGBA.toChannel(0.0) shouldBe 0x00
        RGBA.toChannel(0.5) shouldBe 0x80
        RGBA.toChannel(0.25) shouldBe 0x40
        RGBA.toChannel(0.75) shouldBe 0xC0
        repeat(256) {
            RGBA.toChannel(it / 256.0) shouldBe it
        }

        assertEquals(RGBA.toRatio(0xFF), 1.0, CHANNEL_EPSILON)
        assertEquals(RGBA.toRatio(0x00), 0.0, CHANNEL_EPSILON)
        assertEquals(RGBA.toRatio(0x80), 0.5, CHANNEL_EPSILON)
        assertEquals(RGBA.toRatio(0x40), 0.25, CHANNEL_EPSILON)
        assertEquals(RGBA.toRatio(0xC0), 0.75, CHANNEL_EPSILON)
        repeat(256) {
            assertEquals(it / 256.0, RGBA.toRatio(it), CHANNEL_EPSILON)
        }
    }

    companion object {
        const val CHANNEL_EPSILON = 1.0 / 256.0

        infix fun Int.shouldBe(expected: Long) = shouldBe(expected.toInt())
        infix fun Int.shouldBe(expected: Int) = assertEquals(expected, this) {
            "expected: ${RGBA.toHex(expected)} | actual: ${RGBA.toHex(this)}"
        }
    }
}
