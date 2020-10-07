@file:Suppress("NOTHING_TO_INLINE")

package mathx.geometry

import mathx.*

public interface Transformation<T : Transformation<T>> : Interpolated<T> {
    public val xx: Double get() = 1.0
    public val xy: Double get() = 0.0
    public val xz: Double get() = 0.0
    public val xw: Double get() = 0.0

    public val yx: Double get() = 0.0
    public val yy: Double get() = 1.0
    public val yz: Double get() = 0.0
    public val yw: Double get() = 0.0

    public val zx: Double get() = 0.0
    public val zy: Double get() = 0.0
    public val zz: Double get() = 1.0
    public val zw: Double get() = 0.0

    public val tx: Double get() = 0.0
    public val ty: Double get() = 0.0
    public val tz: Double get() = 0.0
    public val tw: Double get() = 1.0
}

// 3D

public fun Transformation<*>.toVector3D(): Vector3D = Vector3D(x = tx, y = ty, z = tz)

public fun Transformation<*>.toPoint3D(): Point3D = Point3D(x = tx, y = ty, z = tz)

public fun Transformation<*>.toBasis3D(): Basis3D = Basis3D(
    xx = xx, xy = xy, xz = xz,
    yx = yx, yy = yy, yz = yz,
    zx = zx, zy = zy, zz = zz,
)

public fun Transformation<*>.toAffine3D(): Affine3D = Affine3D(
    xx = xx, xy = xy, xz = xz,
    yx = yx, yy = yy, yz = yz,
    zx = zx, zy = zy, zz = zz,
    tx = tx, ty = ty, tz = tz,
)

public fun Transformation<*>.toTransform3D(): Transform3D = Transform3D(
    xx = xx, xy = xy, xz = xz, xw = xw,
    yx = yx, yy = yy, yz = yz, yw = yw,
    zx = zx, zy = zy, zz = zz, zw = zw,
    tx = tx, ty = ty, tz = tz, tw = tw,
)

public fun Transformation<*>.transform(p: Vector3D): Vector3D = Vector3D(
    x = xx * p.x + yx * p.y + zx * p.z,
    y = xy * p.x + yy * p.y + zy * p.z,
    z = xz * p.x + yz * p.y + zz * p.z,
)

public fun Transformation<*>.transform(p: Point3D): Point3D = Point3D(
    x = xx * p.x + yx * p.y + zx * p.z + tx,
    y = xy * p.x + yy * p.y + zy * p.z + ty,
    z = xz * p.x + yz * p.y + zz * p.z + tz,
)

public fun Transformation<*>.transform(b: Basis3D): Basis3D = Basis3D(
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

public fun Transformation<*>.transform(a: Affine3D): Affine3D = Affine3D(
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

public fun Transformation<*>.transform(t: Transform3D): Transform3D = Transform3D(
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

// 2D

public fun Transformation<*>.toVector2D(): Vector2D = Vector2D(x = tx, y = ty)

public fun Transformation<*>.toPoint2D(): Point2D = Point2D(x = tx, y = ty)

public fun Transformation<*>.toBasis2D(): Basis2D = Basis2D(
    xx = xx, xy = xy,
    yx = yx, yy = yy,
)

public fun Transformation<*>.toAffine2D(): Affine2D = Affine2D(
    xx = xx, xy = xy,
    yx = yx, yy = yy,
    tx = tx, ty = ty,
)

public fun Transformation<*>.toTransform2D(): Transform2D = Transform2D(
    xx = xx, xy = xy, xw = xw,
    yx = yx, yy = yy, yw = yw,
    tx = tx, ty = ty, tw = tw,
)

public fun Transformation<*>.transform(p: Vector2D): Vector2D = Vector2D(
    x = xx * p.x + yx * p.y,
    y = xy * p.x + yy * p.y,
)

public fun Transformation<*>.transform(p: Point2D): Point2D = Point2D(
    x = xx * p.x + yx * p.y + tx,
    y = xy * p.x + yy * p.y + ty,
)

public fun Transformation<*>.transform(b: Basis2D): Basis2D = Basis2D(
    xx = xx * b.xx + yx * b.xy,
    xy = xy * b.xx + yy * b.xy,

    yx = xx * b.yx + yx * b.yy,
    yy = xy * b.yx + yy * b.yy,
)

public fun Transformation<*>.transform(a: Affine2D): Affine2D = Affine2D(
    xx = xx * a.xx + yx * a.xy,
    xy = xy * a.xx + yy * a.xy,

    yx = xx * a.yx + yx * a.yy,
    yy = xy * a.yx + yy * a.yy,

    tx = xx * a.tx + yx * a.ty + tx,
    ty = xy * a.tx + yy * a.ty + ty,
)

public fun Transformation<*>.transform(t: Transform2D): Transform2D = Transform2D(
    xx = xx * t.xx + yx * t.xy + tx * t.xw,
    xy = xy * t.xx + yy * t.xy + ty * t.xw,
    xw = xw * t.xx + yw * t.xy + tw * t.xw,

    yx = xx * t.yx + yx * t.yy + tx * t.yw,
    yy = xy * t.yx + yy * t.yy + ty * t.yw,
    yw = xw * t.yx + yw * t.yy + tw * t.yw,

    tx = xx * t.tx + yx * t.ty + tx * t.tw,
    ty = xy * t.tx + yy * t.ty + ty * t.tw,
    tw = xw * t.tx + yw * t.ty + tw * t.tw,
)
