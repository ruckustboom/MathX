package mathx.geometry

import mathx.*

public data class Basis2D(
    val xx: Double, val xy: Double,
    val yx: Double, val yy: Double,
) : Transformation2D<Basis2D> {
    override fun toTransform2D(): Transform2D = Transform2D(
        xx = xx, xy = xy, xw = 0.0,
        yx = yx, yy = yy, yw = 0.0,
        tx = 0.0, ty = 0.0, tw = 1.0,
    )

    override fun interpolate(b: Basis2D, t: Double): Basis2D = Basis2D(
        xx = lerp(xx, b.xx, t), xy = lerp(xy, b.xy, t),
        yx = lerp(yx, b.yx, t), yy = lerp(yy, b.yy, t),
    )
}
