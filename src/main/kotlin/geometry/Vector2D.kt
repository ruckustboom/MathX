package mathx.geometry

import mathx.*

public data class Vector2D(val x: Double, val y: Double) : Transformation2D<Vector2D> {
    override fun toTransform2D(): Transform2D = Transform2D(
        xx = 1.0, xy = 0.0, xw = 0.0,
        yx = 0.0, yy = 1.0, yw = 0.0,
        tx = x, ty = y, tw = 0.0,
    )

    override fun interpolate(b: Vector2D, t: Double): Vector2D = Vector2D(
        x = lerp(x, b.x, t),
        y = lerp(y, b.y, t),
    )
}
