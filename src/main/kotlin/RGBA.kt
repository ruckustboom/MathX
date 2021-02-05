@file:Suppress("NOTHING_TO_INLINE")

package mathx

import kotlin.math.nextDown
import kotlin.math.pow

public object RGBA {
    @JvmStatic
    public inline operator fun invoke(red: Int, green: Int, blue: Int, alpha: Int = 0xFF): Int =
        rgba(red, green, blue, alpha)

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
        toRatio(getRed(rgba)) * 0.2126 +
                toRatio(getGreen(rgba)) * 0.7125 +
                toRatio(getBlue(rgba)) * 0.0722

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
    public fun alphaBlend(source: Int, dest: Int): Int {
        val sa = getAlpha(source) / 255.0
        val da = getAlpha(dest) / 255.0 * (1.0 - sa)
        val a = sa + da
        return if (a == 0.0) 0 else rgba(
            alphaBlendChannel(getRed(source), getRed(dest), sa, da, a),
            alphaBlendChannel(getGreen(source), getGreen(dest), sa, da, a),
            alphaBlendChannel(getBlue(source), getBlue(dest), sa, da, a),
            toChannel(a),
        )
    }

    @JvmStatic
    private inline fun alphaBlendChannel(s: Int, d: Int, oa: Double, da: Double, a: Double): Int =
        toChannel((toRatio(s) * oa + toRatio(d) * da) / a)

    @JvmStatic
    public fun alphaBlendCorrected(source: Int, dest: Int, gamma: Double = 2.2): Int {
        if (gamma == 0.0) return -1  // White
        val ig = 1.0 / gamma
        val sa = getAlpha(source) / 255.0
        val da = getAlpha(dest) / 255.0 * (1.0 - sa)
        val a = sa + da
        return if (a == 0.0) 0 else rgba(
            alphaBlendChannelCorrected(getRed(source), getRed(dest), sa, da, a, gamma, ig),
            alphaBlendChannelCorrected(getGreen(source), getGreen(dest), sa, da, a, gamma, ig),
            alphaBlendChannelCorrected(getBlue(source), getBlue(dest), sa, da, a, gamma, ig),
            toChannel(a),
        )
    }

    @JvmStatic
    private inline fun alphaBlendChannelCorrected(
        s: Int,
        d: Int,
        sa: Double,
        da: Double,
        ra: Double,
        g: Double,
        ig: Double,
    ): Int = toChannel(((toRatio(s).pow(g) * sa + toRatio(d).pow(g) * da) / ra).pow(ig))

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

    @JvmStatic
    public inline fun toChannel(ratio: Double): Int = (ratio.coerceIn(0.0, 1.0.nextDown()) * 256.0).toInt()

    @JvmStatic
    public inline fun toRatio(channel: Int): Double = channel.coerceIn(0, 0xFF) / 255.0

    public object PreMultipliedInterpolator : Interpolator<Int> {
        override fun interpolate(a: Int, b: Int, t: Double): Int {
            val aa = toRatio(getAlpha(a))
            val ba = toRatio(getAlpha(b))
            val ta = lerp(aa, ba, t)
            return if (ta == 0.0) 0 else rgba(
                toChannel(lerp(toRatio(getRed(a)) * aa, toRatio(getRed(b)) * ba, t) / ta),
                toChannel(lerp(toRatio(getGreen(a)) * aa, toRatio(getGreen(b)) * ba, t) / ta),
                toChannel(lerp(toRatio(getBlue(a)) * aa, toRatio(getBlue(b)) * ba, t) / ta),
                toChannel(ta)
            )
        }

        override fun toString(): String = "RGBA.PreMultipliedInterpolator"
    }

    public object StraightAlphaInterpolator : Interpolator<Int> {
        override fun interpolate(a: Int, b: Int, t: Double): Int = rgba(
            lerp(getRed(a), getRed(b), t),
            lerp(getGreen(a), getGreen(b), t),
            lerp(getBlue(a), getBlue(b), t),
            lerp(getAlpha(a), getAlpha(b), t),
        )

        override fun toString(): String = "RGBA.StraightAlphaInterpolator"
    }
}
