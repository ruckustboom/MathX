@file:Suppress("NOTHING_TO_INLINE")

package mathx.geometry

import mathx.Interpolated

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

// 2D

public fun Transformation<*>.toVector2D(): Vector2D = Vector2D(x = tx, y = ty, w = tw)

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

public inline infix fun Vector2D.transformBy(t: Transformation<*>): Vector2D = t transform this
public infix fun Transformation<*>.transform(v: Vector2D): Vector2D = Vector2D(
    x = tx(v), y = ty(v), w = tw(v),
)

public inline infix fun Basis2D.transformBy(t: Transformation<*>): Basis2D = t transform this
public infix fun Transformation<*>.transform(b: Basis2D): Basis2D = Basis2D(
    xx = xx(b), xy = xy(b),
    yx = yx(b), yy = yy(b),
)

public inline infix fun Affine2D.transformBy(t: Transformation<*>): Affine2D = t transform this
public infix fun Transformation<*>.transform(a: Affine2D): Affine2D = Affine2D(
    xx = xx(a), xy = xy(a),
    yx = yx(a), yy = yy(a),
    tx = tx(a), ty = ty(a),
)

public inline infix fun Transform2D.transformBy(t: Transformation<*>): Transform2D = t transform this
public infix fun Transformation<*>.transform(t: Transform2D): Transform2D = Transform2D(
    xx = xx(t), xy = xy(t), xw = xw(t),
    yx = yx(t), yy = yy(t), yw = yw(t),
    tx = tx(t), ty = ty(t), tw = tw(t),
)

public inline infix fun Bounds2D.transformBy(t: Transformation<*>): Bounds2D = t transform this
public infix fun Transformation<*>.transform(b: Bounds2D): Bounds2D = Bounds2D.of(
    transform(Vector2D(b.xMin, b.yMin)),
    transform(Vector2D(b.xMin, b.yMax)),
    transform(Vector2D(b.xMax, b.yMin)),
    transform(Vector2D(b.xMax, b.yMax)),
)

// 3D

public fun Transformation<*>.toVector3D(): Vector3D = Vector3D(x = tx, y = ty, z = tz, w = tw)

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

public inline infix fun Vector3D.transformBy(t: Transformation<*>): Vector3D = t transform this
public infix fun Transformation<*>.transform(v: Vector3D): Vector3D = Vector3D(
    x = tx(v), y = ty(v), z = tz(v), w = tw(v),
)

public inline infix fun Basis3D.transformBy(t: Transformation<*>): Basis3D = t transform this
public infix fun Transformation<*>.transform(b: Basis3D): Basis3D = Basis3D(
    xx = xx(b), xy = xy(b), xz = xz(b),
    yx = yx(b), yy = yy(b), yz = yz(b),
    zx = zx(b), zy = zy(b), zz = zz(b),
)

public inline infix fun Affine3D.transformBy(t: Transformation<*>): Affine3D = t transform this
public infix fun Transformation<*>.transform(a: Affine3D): Affine3D = Affine3D(
    xx = xx(a), xy = xy(a), xz = xz(a),
    yx = yx(a), yy = yy(a), yz = yz(a),
    zx = zx(a), zy = zy(a), zz = zz(a),
    tx = tx(a), ty = ty(a), tz = tz(a),
)

public inline infix fun Transform3D.transformBy(t: Transformation<*>): Transform3D = t transform this
public infix fun Transformation<*>.transform(t: Transform3D): Transform3D = Transform3D(
    xx = xx(t), xy = xy(t), xz = xz(t), xw = xw(t),
    yx = yx(t), yy = yy(t), yz = yz(t), yw = yw(t),
    zx = zx(t), zy = zy(t), zz = zz(t), zw = zw(t),
    tx = tx(t), ty = ty(t), tz = tz(t), tw = tw(t),
)

public inline infix fun Bounds3D.transformBy(t: Transformation<*>): Bounds3D = t transform this
public infix fun Transformation<*>.transform(b: Bounds3D): Bounds3D = Bounds3D.of(
    transform(Vector3D(b.xMin, b.yMin, b.zMin)),
    transform(Vector3D(b.xMin, b.yMin, b.zMax)),
    transform(Vector3D(b.xMin, b.yMax, b.zMin)),
    transform(Vector3D(b.xMin, b.yMax, b.zMax)),
    transform(Vector3D(b.xMax, b.yMin, b.zMin)),
    transform(Vector3D(b.xMax, b.yMin, b.zMax)),
    transform(Vector3D(b.xMax, b.yMax, b.zMin)),
    transform(Vector3D(b.xMax, b.yMax, b.zMax)),
)

// Components

private inline fun Transformation<*>.xx(t: Transformation<*>) = xx * t.xx + yx * t.xy + zx * t.xz + tx * t.xw
private inline fun Transformation<*>.xy(t: Transformation<*>) = xy * t.xx + yy * t.xy + zy * t.xz + ty * t.xw
private inline fun Transformation<*>.xz(t: Transformation<*>) = xz * t.xx + yz * t.xy + zz * t.xz + tz * t.xw
private inline fun Transformation<*>.xw(t: Transformation<*>) = xw * t.xx + yw * t.xy + zw * t.xz + tw * t.xw

private inline fun Transformation<*>.yx(t: Transformation<*>) = xx * t.yx + yx * t.yy + zx * t.yz + tx * t.yw
private inline fun Transformation<*>.yy(t: Transformation<*>) = xy * t.yx + yy * t.yy + zy * t.yz + ty * t.yw
private inline fun Transformation<*>.yz(t: Transformation<*>) = xz * t.yx + yz * t.yy + zz * t.yz + tz * t.yw
private inline fun Transformation<*>.yw(t: Transformation<*>) = xw * t.yx + yw * t.yy + zw * t.yz + tw * t.yw

private inline fun Transformation<*>.zx(t: Transformation<*>) = xx * t.zx + yx * t.zy + zx * t.zz + tx * t.zw
private inline fun Transformation<*>.zy(t: Transformation<*>) = xy * t.zx + yy * t.zy + zy * t.zz + ty * t.zw
private inline fun Transformation<*>.zz(t: Transformation<*>) = xz * t.zx + yz * t.zy + zz * t.zz + tz * t.zw
private inline fun Transformation<*>.zw(t: Transformation<*>) = xw * t.zx + yw * t.zy + zw * t.zz + tw * t.zw

private inline fun Transformation<*>.tx(t: Transformation<*>) = xx * t.tx + yx * t.ty + zx * t.tz + tx * t.tw
private inline fun Transformation<*>.ty(t: Transformation<*>) = xy * t.tx + yy * t.ty + zy * t.tz + ty * t.tw
private inline fun Transformation<*>.tz(t: Transformation<*>) = xz * t.tx + yz * t.ty + zz * t.tz + tz * t.tw
private inline fun Transformation<*>.tw(t: Transformation<*>) = xw * t.tx + yw * t.ty + zw * t.tz + tw * t.tw
