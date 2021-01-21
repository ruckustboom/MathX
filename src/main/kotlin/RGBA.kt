@file:Suppress("NOTHING_TO_INLINE")

package mathx

import kotlin.math.nextDown

public object RGBA {
    @JvmStatic
    public inline fun rgba(red: Int, green: Int, blue: Int, alpha: Int = 0xFF): Int =
        (red.coerceIn(0x00, 0xFF) shl 24) or
                (green.coerceIn(0x00, 0xFF) shl 16) or
                (blue.coerceIn(0x00, 0xFF) shl 8) or
                alpha.coerceIn(0x00, 0xFF)

    @JvmStatic
    public inline fun gray(gray: Int = 0x80, alpha: Int = 0xFF): Int = rgba(gray, gray, gray, alpha)

    @JvmStatic
    public inline fun getRed(rgba: Int): Int = rgba ushr 24 and 0xFF

    @JvmStatic
    public inline fun getGreen(rgba: Int): Int = rgba ushr 16 and 0xFF

    @JvmStatic
    public inline fun getBlue(rgba: Int): Int = rgba ushr 8 and 0xFF

    @JvmStatic
    public inline fun getAlpha(rgba: Int): Int = rgba and 0xFF

    @JvmStatic
    public inline fun getGray(rgba: Int): Double =
        getRed(rgba) / 255.0 * 0.2126 +
                getGreen(rgba) / 255.0 * 0.7125 +
                getBlue(rgba) / 255.0 * 0.0722

    @JvmStatic
    public inline fun copy(
        rgba: Int,
        red: Int = getRed(rgba),
        green: Int = getGreen(rgba),
        blue: Int = getBlue(rgba),
        alpha: Int = getAlpha(rgba),
    ): Int = rgba(red, green, blue, alpha)

    @JvmStatic
    public fun toGrayscale(rgba: Int): Int = gray(toChannel(getGray(rgba)), getAlpha(rgba))

    @JvmStatic
    public inline fun invert(rgba: Int): Int = rgba(
        red = 255 - getRed(rgba),
        green = 255 - getGreen(rgba),
        blue = 255 - getBlue(rgba),
        alpha = getAlpha(rgba),
    )

    @JvmStatic
    public fun toHex(rgba: Int): String = buildString {
        val r = getRed(rgba).toString(16)
        if (r.length == 1) append('0')
        append(r)
        val g = getGreen(rgba).toString(16)
        if (g.length == 1) append('0')
        append(g)
        val b = getBlue(rgba).toString(16)
        if (b.length == 1) append('0')
        append(b)
        val a = getAlpha(rgba).toString(16)
        if (a.length == 1) append('0')
        append(a)
    }

    @JvmStatic
    public fun fromHex(hex: String): Int {
        if (hex.startsWith("#")) return fromHex(hex.substring(1))
        if (hex.startsWith("0x") || hex.startsWith("0X")) return fromHex(hex.substring(2))
        val red: Int
        val green: Int
        val blue: Int
        val alpha: Int
        if (hex.length == 3 || hex.length == 4) {
            red = hex.substring(0, 1).repeat(2).toInt(16)
            green = hex.substring(1, 2).repeat(2).toInt(16)
            blue = hex.substring(2, 3).repeat(2).toInt(16)
            alpha = if (hex.length == 4) hex.substring(3, 4).repeat(2).toInt(16) else 0xFF
        } else if (hex.length == 6 || hex.length == 8) {
            red = hex.substring(0, 2).toInt(16)
            green = hex.substring(2, 4).toInt(16)
            blue = hex.substring(4, 6).toInt(16)
            alpha = if (hex.length == 8) hex.substring(6, 8).toInt(16) else 0xFF
        } else {
            error("Invalid color: $hex")
        }
        return rgba(red, green, blue, alpha)
    }
}

public object PreMultipliedInterpolator : Interpolator<Int> {
    override fun interpolate(a: Int, b: Int, t: Double): Int {
        val aa = RGBA.getAlpha(a) / 255.0
        val ba = RGBA.getAlpha(b) / 255.0
        val ta = lerp(aa, ba, t)
        return if (ta == 0.0) 0 else RGBA.rgba(
            red = toChannel(lerp(RGBA.getRed(a) / 255.0 * aa, RGBA.getRed(b) / 255.0 * ba, t) / ta),
            green = toChannel(lerp(RGBA.getGreen(a) / 255.0 * aa, RGBA.getGreen(b) / 255.0 * ba, t) / ta),
            blue = toChannel(lerp(RGBA.getBlue(a) / 255.0 * aa, RGBA.getBlue(b) / 255.0 * ba, t) / ta),
            alpha = toChannel(ta)
        )
    }
}

public object StraightAlphaInterpolator : Interpolator<Int> {
    override fun interpolate(a: Int, b: Int, t: Double): Int = RGBA.rgba(
        red = lerp(RGBA.getRed(a), RGBA.getRed(b), t),
        green = lerp(RGBA.getGreen(a), RGBA.getGreen(b), t),
        blue = lerp(RGBA.getBlue(a), RGBA.getBlue(b), t),
        alpha = lerp(RGBA.getAlpha(a), RGBA.getAlpha(b), t),
    )
}

// Impl

private inline fun toChannel(ratio: Double): Int = (ratio.coerceIn(0.0, 1.0.nextDown()) * 256.0).toInt()
