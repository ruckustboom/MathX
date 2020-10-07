package mathx.geometry

import mathx.*

public data class Basis2D(
    val xx: Double, val xy: Double,
    val yx: Double, val yy: Double,
) : Transformation2D<Basis2D, Basis3D> {
    override fun toTransform(): Transform2D = Transform2D(
        xx = xx, xy = xy, xw = 0.0,
        yx = yx, yy = yy, yw = 0.0,
        tx = 0.0, ty = 0.0, tw = 1.0,
    )

    override fun to3D(): Basis3D = Basis3D(
        xx = xx, xy = xy, xz = 0.0,
        yx = yx, yy = yy, yz = 0.0,
        zx = 0.0, zy = 0.0, zz = 1.0,
    )

    override fun interpolate(b: Basis2D, t: Double): Basis2D = Basis2D(
        xx = lerp(xx, b.xx, t), xy = lerp(xy, b.xy, t),
        yx = lerp(yx, b.yx, t), yy = lerp(yy, b.yy, t),
    )

    public companion object :  Interpolator<Basis2D> {
        public val IDENTITY: Basis2D = Basis2D(
            xx = 1.0, xy = 0.0,
            yx = 0.0, yy = 1.0,
        )

        override fun interpolate(a: Basis2D, b: Basis2D, t: Double): Basis2D = a.interpolate(b, t)

        override fun toString(): String = "Basis2D"
    }
}
