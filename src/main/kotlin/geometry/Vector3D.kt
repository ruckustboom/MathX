package mathx.geometry

import mathx.*

public data class Vector3D(val x: Double, val y: Double, val z: Double) : Transformation3D<Vector3D, Vector2D> {
    override fun toTransform(): Transform3D = Transform3D(
        xx = 1.0, xy = 0.0, xz = 0.0, xw = 0.0,
        yx = 0.0, yy = 1.0, yz = 0.0, yw = 0.0,
        zx = 0.0, zy = 0.0, zz = 1.0, zw = 0.0,
        tx = x, ty = y, tz = z, tw = 0.0,
    )

    override fun to2D(): Vector2D = Vector2D(x = x, y = y)

    override fun interpolate(b: Vector3D, t: Double): Vector3D = Vector3D(
        x = lerp(x, b.x, t),
        y = lerp(y, b.y, t),
        z = lerp(z, b.z, t),
    )

    public companion object : Interpolator<Vector3D> {
        override fun interpolate(a: Vector3D, b: Vector3D, t: Double): Vector3D = a.interpolate(b, t)

        override fun toString(): String = "Vector3D"
    }
}
