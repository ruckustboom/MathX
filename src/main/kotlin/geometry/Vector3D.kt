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
        public val ZERO: Vector3D = Vector3D(0.0, 0.0, 0.0)
        public val X: Vector3D = x(1.0)
        public val Y: Vector3D = y(1.0)
        public val Z: Vector3D = z(1.0)

        public val NEGATIVE_INFINITY: Vector3D = Vector3D(
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
        )
        public val POSITIVE_INFINITY: Vector3D = Vector3D(
            Double.POSITIVE_INFINITY,
            Double.POSITIVE_INFINITY,
            Double.POSITIVE_INFINITY,
        )
        public val NaN: Vector3D = Vector3D(Double.NaN, Double.NaN, Double.NaN)

        public fun x(x: Double): Vector3D = Vector3D(x, 0.0, 0.0)
        public fun y(y: Double): Vector3D = Vector3D(0.0, y, 0.0)
        public fun z(z: Double): Vector3D = Vector3D(0.0, 0.0, z)

        override fun interpolate(a: Vector3D, b: Vector3D, t: Double): Vector3D = a.interpolate(b, t)

        override fun toString(): String = "Vector3D"
    }
}
