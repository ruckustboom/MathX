package mathx.geometry

import mathx.*

public data class Transform3D(
    val xx: Double, val xy: Double, val xz: Double, val xw: Double,
    val yx: Double, val yy: Double, val yz: Double, val yw: Double,
    val zx: Double, val zy: Double, val zz: Double, val zw: Double,
    val tx: Double, val ty: Double, val tz: Double, val tw: Double,
) : Transformation3D<Transform3D, Transform2D> {
    public operator fun invoke(p: Vector3D): Vector3D = Vector3D(
        x = xx * p.x + yx * p.y + zx * p.z,
        y = xy * p.x + yy * p.y + zy * p.z,
        z = xz * p.x + yz * p.y + zz * p.z,
    )

    public operator fun invoke(p: Point3D): Point3D = Point3D(
        x = xx * p.x + yx * p.y + zx * p.z + tx,
        y = xy * p.x + yy * p.y + zy * p.z + ty,
        z = xz * p.x + yz * p.y + zz * p.z + tz,
    )

    public operator fun invoke(b: Basis3D): Basis3D = Basis3D(
        xx = xx * b.xx + yx * b.xy + zx * b.xz,
        xy = xy * b.xx + yy * b.xy + zy * b.xz,
        xz = xz * b.xx + yz * b.xy + zz * b.xz,

        yx = xx * b.yx + yx * b.yy + zx * b.yz,
        yy = xy * b.yx + yy * b.yy + zy * b.yz,
        yz = xz * b.yx + yz * b.yy + zz * b.yz,

        zx = xx * b.zx + yx * b.zy + zx * b.zz,
        zy = xy * b.zx + yy * b.zy + zy * b.zz,
        zz = xz * b.zx + yz * b.zy + zz * b.zz,
    )

    public operator fun invoke(a: Affine3D): Affine3D = Affine3D(
        xx = xx * a.xx + yx * a.xy + zx * a.xz,
        xy = xy * a.xx + yy * a.xy + zy * a.xz,
        xz = xz * a.xx + yz * a.xy + zz * a.xz,

        yx = xx * a.yx + yx * a.yy + zx * a.yz,
        yy = xy * a.yx + yy * a.yy + zy * a.yz,
        yz = xz * a.yx + yz * a.yy + zz * a.yz,

        zx = xx * a.zx + yx * a.zy + zx * a.zz,
        zy = xy * a.zx + yy * a.zy + zy * a.zz,
        zz = xz * a.zx + yz * a.zy + zz * a.zz,

        tx = xx * a.tx + yx * a.ty + zx * a.tz + tx,
        ty = xy * a.tx + yy * a.ty + zy * a.tz + ty,
        tz = xz * a.tx + yz * a.ty + zz * a.tz + tz,
    )

    public operator fun invoke(t: Transform3D): Transform3D = Transform3D(
        xx = xx * t.xx + yx * t.xy + zx * t.xz + tx * t.xw,
        xy = xy * t.xx + yy * t.xy + zy * t.xz + ty * t.xw,
        xz = xz * t.xx + yz * t.xy + zz * t.xz + tz * t.xw,
        xw = xw * t.xx + yw * t.xy + zw * t.xz + tw * t.xw,

        yx = xx * t.yx + yx * t.yy + zx * t.yz + tx * t.yw,
        yy = xy * t.yx + yy * t.yy + zy * t.yz + ty * t.yw,
        yz = xz * t.yx + yz * t.yy + zz * t.yz + tz * t.yw,
        yw = xw * t.yx + yw * t.yy + zw * t.yz + tw * t.yw,

        zx = xx * t.zx + yx * t.zy + zx * t.zz + tx * t.zw,
        zy = xy * t.zx + yy * t.zy + zy * t.zz + ty * t.zw,
        zz = xz * t.zx + yz * t.zy + zz * t.zz + tz * t.zw,
        zw = xw * t.zx + yw * t.zy + zw * t.zz + tw * t.zw,

        tx = xx * t.tx + yx * t.ty + zx * t.tz + tx * t.tw,
        ty = xy * t.tx + yy * t.ty + zy * t.tz + ty * t.tw,
        tz = xz * t.tx + yz * t.ty + zz * t.tz + tz * t.tw,
        tw = xw * t.tx + yw * t.ty + zw * t.tz + tw * t.tw,
    )

    public fun toVector3D(): Vector3D = Vector3D(x = tx, y = ty, z = tz)

    public fun toPoint3D(): Point3D = Point3D(x = tx, y = ty, z = tz)

    public fun toBasis3D(): Basis3D = Basis3D(
        xx = xx, xy = xy, xz = xz,
        yx = yx, yy = yy, yz = yz,
        zx = zx, zy = zy, zz = zz,
    )

    public fun toAffine3D(): Affine3D = Affine3D(
        xx = xx, xy = xy, xz = xz,
        yx = yx, yy = yy, yz = yz,
        zx = zx, zy = zy, zz = zz,
        tx = tx, ty = ty, tz = tz,
    )

    override fun toTransform(): Transform3D = this

    override fun to2D(): Transform2D = Transform2D(
        xx = xx, xy = xy, xw = xw,
        yx = yx, yy = yy, yw = yw,
        tx = tx, ty = ty, tw = tw,
    )

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
