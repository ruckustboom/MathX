package mathx.geometry

import mathx.*

public data class Transform3D(
    override val xx: Double, override val xy: Double, override val xz: Double, override val xw: Double,
    override val yx: Double, override val yy: Double, override val yz: Double, override val yw: Double,
    override val zx: Double, override val zy: Double, override val zz: Double, override val zw: Double,
    override val tx: Double, override val ty: Double, override val tz: Double, override val tw: Double,
) : Transformation<Transform3D> {
    override fun interpolate(b: Transform3D, t: Double): Transform3D = Transform3D(
        xx = lerp(xx, b.xx, t), xy = lerp(xy, b.xy, t), xz = lerp(xz, b.xz, t), xw = lerp(xw, b.xw, t),
        yx = lerp(yx, b.yx, t), yy = lerp(yy, b.yy, t), yz = lerp(yz, b.yz, t), yw = lerp(yw, b.yw, t),
        zx = lerp(zx, b.zx, t), zy = lerp(zy, b.zy, t), zz = lerp(zz, b.zz, t), zw = lerp(zw, b.zw, t),
        tx = lerp(tx, b.tx, t), ty = lerp(ty, b.ty, t), tz = lerp(tz, b.tz, t), tw = lerp(tw, b.tw, t),
    )

    public companion object : Interpolator<Transform3D> {
        public val IDENTITY: Transform3D = Transform3D(
            xx = 1.0, xy = 0.0, xz = 0.0, xw = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0, yw = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0, zw = 0.0,
            tx = 0.0, ty = 0.0, tz = 0.0, tw = 1.0,
        )

        override fun interpolate(a: Transform3D, b: Transform3D, t: Double): Transform3D = a.interpolate(b, t)

        override fun toString(): String = "Transform3D"
    }
}
