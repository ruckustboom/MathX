package mathx

// TODO: Move to Palette project?

/* TODO
 * - Add HSB conversion (and maybe others?)
 */
public inline class Color(public val rgba: UInt) {
    public constructor(
        red: UByte,
        green: UByte,
        blue: UByte,
        alpha: UByte = 0xFFu
    ) : this((red.toUInt() shl 24) or (green.toUInt() shl 16) or (blue.toUInt() shl 8) or alpha.toUInt())

    public val red: UByte get() = (rgba shr 24).toUByte()
    public val green: UByte get() = (rgba shr 16).toUByte()
    public val blue: UByte get() = (rgba shr 8).toUByte()
    public val alpha: UByte get() = rgba.toUByte()

    public val gray: Double
        get() = uByteToRatio(red) * RED_WEIGHT +
                uByteToRatio(green) * GREEN_WEIGHT +
                uByteToRatio(blue) * BLUE_WEIGHT

    public fun grayscale(): Color = gray(clampRatioToUByte(gray), alpha)
    public fun invert(alpha: UByte = this.alpha): Color = Color(red.inv(), green.inv(), blue.inv(), alpha)

    public fun copy(
        red: UByte = this.red,
        green: UByte = this.green,
        blue: UByte = this.blue,
        alpha: UByte = this.alpha,
    ): Color = Color(red, green, blue, alpha)

    public fun lerpPreMultiplied(target: Color, t: Double): Color {
        val aa = uByteToRatio(alpha)
        val ba = uByteToRatio(target.alpha)
        val a = lerp(aa, ba, t)
        return if (a == 0.0) TRANSPARENT else Color(
            red = clampRatioToUByte(lerp(uByteToRatio(red) * aa, uByteToRatio(target.red) * ba, t) / a),
            green = clampRatioToUByte(lerp(uByteToRatio(green) * aa, uByteToRatio(target.green) * ba, t) / a),
            blue = clampRatioToUByte(lerp(uByteToRatio(blue) * aa, uByteToRatio(target.blue) * ba, t) / a),
            alpha = clampRatioToUByte(a)
        )
    }

    public fun lerpStraightAlpha(target: Color, t: Double): Color = Color(
        red = lerp(red, target.red, t),
        green = lerp(green, target.green, t),
        blue = lerp(blue, target.blue, t),
        alpha = lerp(alpha, target.alpha, t),
    )

    public fun toHex(): String = buildString {
        val r = red.toString(16)
        if (r.length == 1) append('0')
        append(r)
        val g = green.toString(16)
        if (g.length == 1) append('0')
        append(g)
        val b = blue.toString(16)
        if (b.length == 1) append('0')
        append(b)
        val a = alpha.toString(16)
        if (a.length == 1) append('0')
        append(a)
    }

    override fun toString(): String = "#${toHex()}"

    public companion object {
        public val TRANSPARENT: Color = Color(0x00_00_00_00u)

        public val WHITE: Color = Color(0xFF_FF_FF_FFu)
        public val BLACK: Color = Color(0x00_00_00_FFu)
        public val GRAY: Color = Color(0x7F_7F_7F_FFu)

        public val RED: Color = Color(0xFF_00_00_FFu)
        public val GREEN: Color = Color(0x00_FF_00_FFu)
        public val BLUE: Color = Color(0x00_00_FF_FFu)

        public val YELLOW: Color = Color(0xFF_FF_00_FFu)
        public val CYAN: Color = Color(0x00_FF_FF_FFu)
        public val MAGENTA: Color = Color(0xFF_00_FF_FFu)

        public fun gray(gray: UByte, alpha: UByte = 0xFFu): Color = Color(gray, gray, gray, alpha)

        public fun fromHex(color: String): Color {
            if (color.startsWith("0x", true)) return fromHex(color.substring(2))
            if (color.startsWith('#')) return fromHex(color.substring(1))
            require(color.length == 3 || color.length == 4 || color.length == 6 || color.length == 8) {
                "Invalid color $color"
            }
            val r: UByte
            val g: UByte
            val b: UByte
            val a: UByte
            if (color.length < 5) {
                r = color.substring(0, 1).repeat(2).toUByte(16)
                g = color.substring(1, 2).repeat(2).toUByte(16)
                b = color.substring(2, 3).repeat(2).toUByte(16)
                a = if (color.length == 4) color.substring(3, 4).repeat(2).toUByte(16) else 0xFFu
            } else {
                r = color.substring(0, 2).toUByte(16)
                g = color.substring(2, 4).toUByte(16)
                b = color.substring(4, 6).toUByte(16)
                a = if (color.length == 8) color.substring(6, 8).toUByte(16) else 0xFFu
            }
            return Color(r, g, b, a)
        }

        // IMPL

        private const val RED_WEIGHT = 0.2126
        private const val GREEN_WEIGHT = 0.7125
        private const val BLUE_WEIGHT = 0.0722
    }
}

public operator fun Color.component1(): UByte = red
public operator fun Color.component2(): UByte = green
public operator fun Color.component3(): UByte = blue
public operator fun Color.component4(): UByte = alpha
