package mathx.geometry

import mathx.Interpolator
import mathx.lerp

public data class Affine2D(
    override val xx: Double, override val xy: Double,
    override val yx: Double, override val yy: Double,
    override val tx: Double, override val ty: Double,
) : Transformation<Affine2D> {
    inline val x: Vector2D get() = Vector2D(x = xx, y = xy)
    inline val y: Vector2D get() = Vector2D(x = yx, y = yy)
    inline val t: Point2D get() = Point2D(x = tx, y = ty)

    override fun interpolate(b: Affine2D, t: Double): Affine2D = Affine2D(
        xx = lerp(xx, b.xx, t), xy = lerp(xy, b.xy, t),
        yx = lerp(yx, b.yx, t), yy = lerp(yy, b.yy, t),
        tx = lerp(tx, b.tx, t), ty = lerp(ty, b.ty, t),
    )

    public companion object : Interpolator<Affine2D> {
        public val IDENTITY: Affine2D = Affine2D(
            xx = 1.0, xy = 0.0,
            yx = 0.0, yy = 1.0,
            tx = 0.0, ty = 0.0,
        )

        override fun interpolate(a: Affine2D, b: Affine2D, t: Double): Affine2D = a.interpolate(b, t)

        override fun toString(): String = "Affine2D"
    }
}
