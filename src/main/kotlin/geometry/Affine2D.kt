package mathx.geometry

import mathx.*

public data class Affine2D(
    val xx: Double, val xy: Double,
    val yx: Double, val yy: Double,
    val tx: Double, val ty: Double,
) : Transformation2D<Affine2D, Affine3D> {
    override fun toTransform(): Transform2D = Transform2D(
        xx = xx, xy = xy, xw = 0.0,
        yx = yx, yy = yy, yw = 0.0,
        tx = tx, ty = ty, tw = 1.0,
    )

    override fun to3D(): Affine3D = Affine3D(
        xx = xx, xy = xy, xz = 0.0,
        yx = yx, yy = yy, yz = 0.0,
        zx = 0.0, zy = 0.0, zz = 1.0,
        tx = tx, ty = ty, tz = 0.0,
    )

    override fun interpolate(b: Affine2D, t: Double): Affine2D = Affine2D(
        xx = lerp(xx, b.xx, t), xy = lerp(xy, b.xy, t),
        yx = lerp(yx, b.yx, t), yy = lerp(yy, b.yy, t),
        tx = lerp(tx, b.tx, t), ty = lerp(ty, b.ty, t),
    )

    public companion object : Interpolator<Affine2D> {
        override fun interpolate(a: Affine2D, b: Affine2D, t: Double): Affine2D = a.interpolate(b, t)

        override fun toString(): String = "Affine2D"
    }
}
