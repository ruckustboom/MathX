package mathx.geometry

import mathx.Interpolator
import mathx.lerp

public data class Basis2D(
    override val xx: Double, override val xy: Double,
    override val yx: Double, override val yy: Double,
) : Transformation<Basis2D> {
    inline val x: Vector2D get() = Vector2D(x = xx, y = xy, w = 0.0)
    inline val y: Vector2D get() = Vector2D(x = yx, y = yy, w = 0.0)

    override fun interpolate(b: Basis2D, t: Double): Basis2D = Basis2D(
        xx = lerp(xx, b.xx, t), xy = lerp(xy, b.xy, t),
        yx = lerp(yx, b.yx, t), yy = lerp(yy, b.yy, t),
    )

    public companion object : Interpolator<Basis2D> {
        public val IDENTITY: Basis2D = Basis2D(
            xx = 1.0, xy = 0.0,
            yx = 0.0, yy = 1.0,
        )

        override fun interpolate(a: Basis2D, b: Basis2D, t: Double): Basis2D = a.interpolate(b, t)

        override fun toString(): String = "Basis2D"
    }
}
