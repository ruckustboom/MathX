package mathx.geometry

import mathx.*

public data class Vector3D(val x: Double, val y: Double, val z: Double) : Transformation<Vector3D> {
    override val tx: Double get() = x
    override val ty: Double get() = y
    override val tz: Double get() = z
    override val tw: Double get() = 0.0

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
