package mathx

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestColor {
    @Test
    fun testToHex() {
        assertEquals("ff0000ff", Color.RED.toHex())
        assertEquals("00ff00ff", Color.GREEN.toHex())
        assertEquals("0000ffff", Color.BLUE.toHex())
        assertEquals("ffff00ff", Color.YELLOW.toHex())
        assertEquals("00ffffff", Color.CYAN.toHex())
        assertEquals("ff00ffff", Color.MAGENTA.toHex())
        assertEquals("00000000", Color.TRANSPARENT.toHex())
        assertEquals("000000ff", Color.BLACK.toHex())
        assertEquals("ffffffff", Color.WHITE.toHex())
        assertEquals("7f7f7fff", Color.GRAY.toHex())
        assertEquals("123456ff", Color(0x12u, 0x34u, 0x56u).toHex())
        assertEquals("12345678", Color(0x12u, 0x34u, 0x56u, 0x78u).toHex())
    }
    
    @Test
    fun testFromHex() {
        assertEquals(Color.RED, Color.fromHex("f00"))
        assertEquals(Color.GREEN, Color.fromHex("0f0f"))
        assertEquals(Color.BLUE, Color.fromHex("#00f"))
        assertEquals(Color.YELLOW, Color.fromHex("#ff0f"))
        assertEquals(Color.CYAN, Color.fromHex("0x0ff"))
        assertEquals(Color.MAGENTA, Color.fromHex("0xf0ff"))
        assertEquals(Color.TRANSPARENT, Color.fromHex("00000000"))
        assertEquals(Color.BLACK, Color.fromHex("000000"))
        assertEquals(Color.BLACK, Color.fromHex("000000ff"))
        assertEquals(Color.WHITE, Color.fromHex("#ffffff"))
        assertEquals(Color.GRAY, Color.fromHex("#7f7f7fff"))
        assertEquals(Color(0x12_34_56_FFu), Color.fromHex("0x123456"))
        assertEquals(Color(0x12_34_56_78u), Color.fromHex("0x12345678"))
    }
}
