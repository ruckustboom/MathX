package mathx.geometry

import mathx.Interpolator
import mathx.lerp

public data class Affine2D(
    override val xx: Double, override val xy: Double,
    override val yx: Double, override val yy: Double,
    override val tx: Double, override val ty: Double,
) : Transformation<Affine2D> {
    inline val x: Vector2D get() = Vector2D(x = xx, y = xy, w = xw)
    inline val y: Vector2D get() = Vector2D(x = yx, y = yy, w = yw)
    inline val t: Vector2D get() = Vector2D(x = tx, y = ty, w = tw)

    override fun interpolate(b: Affine2D, t: Double): Affine2D = Affine2D(
        xx = lerp(xx, b.xx, t), xy = lerp(xy, b.xy, t),
        yx = lerp(yx, b.yx, t), yy = lerp(yy, b.yy, t),
        tx = lerp(tx, b.tx, t), ty = lerp(ty, b.ty, t),
    )

    override fun transformBy(t: Transformation<*>): Affine2D = Affine2D(
        xx = t xx this, xy = t xy this,
        yx = t yx this, yy = t yy this,
        tx = t tx this, ty = t ty this,
    )

    public companion object : Transformation.Builder<Affine2D>, Interpolator<Affine2D> {
        public val IDENTITY: Affine2D = Affine2D(
            xx = 1.0, xy = 0.0,
            yx = 0.0, yy = 1.0,
            tx = 0.0, ty = 0.0,
        )

        override fun interpolate(a: Affine2D, b: Affine2D, t: Double): Affine2D = a.interpolate(b, t)

        override fun build(
            xx: Double, xy: Double, xz: Double, xw: Double,
            yx: Double, yy: Double, yz: Double, yw: Double,
            zx: Double, zy: Double, zz: Double, zw: Double,
            tx: Double, ty: Double, tz: Double, tw: Double,
        ): Affine2D = Affine2D(
            xx = xx, xy = xy,
            yx = yx, yy = yy,
            tx = tx, ty = ty,
        )

        override fun toString(): String = "Affine2D"
    }
}
