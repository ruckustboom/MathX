@file:Suppress("NOTHING_TO_INLINE")

package mathx.geometry

import mathx.Interpolated
import mathx.Interpolator
import kotlin.math.cos
import kotlin.math.sin

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

public inline fun Transformation<*>.toVector2D(): Vector2D = Vector2D from this
public inline fun Transformation<*>.toBasis2D(): Basis2D = Basis2D from this
public inline fun Transformation<*>.toAffine2D(): Affine2D = Affine2D from this
public inline fun Transformation<*>.toTransform2D(): Transform2D = Transform2D from this

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

public fun Transformation<*>.toVector3D(): Vector3D = Vector3D from this
public fun Transformation<*>.toBasis3D(): Basis3D = Basis3D from this
public fun Transformation<*>.toAffine3D(): Affine3D = Affine3D from this
public fun Transformation<*>.toTransform3D(): Transform3D = Transform3D from this

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

// Companion

public interface TransformationCompanion<T : Transformation<T>> : Interpolator<T> {
    public fun from(
        xx: Double = 1.0, xy: Double = 0.0, xz: Double = 0.0, xw: Double = 0.0,
        yx: Double = 0.0, yy: Double = 1.0, yz: Double = 0.0, yw: Double = 0.0,
        zx: Double = 0.0, zy: Double = 0.0, zz: Double = 1.0, zw: Double = 0.0,
        tx: Double = 0.0, ty: Double = 0.0, tz: Double = 0.0, tw: Double = 1.0,
    ): T
}

public infix fun <T : Transformation<T>> TransformationCompanion<T>.from(t: Transformation<*>): T = from(
    xx = t.xx, xy = t.xy, xz = t.xz, xw = t.xw,
    yx = t.yx, yy = t.yy, yz = t.yz, yw = t.yw,
    zx = t.zx, zy = t.zy, zz = t.zz, zw = t.zw,
    tx = t.tx, ty = t.ty, tz = t.tz, tw = t.tw,
)

public fun <T : Transformation<T>> TransformationCompanion<T>.ypr(euler: Vector3D): T =
    ypr(yaw = euler.y, pitch = euler.x, roll = euler.z)

public fun <T : Transformation<T>> TransformationCompanion<T>.yaw(r: Double): T =
    ypr(yaw = r, pitch = 0.0, roll = 0.0)

public fun <T : Transformation<T>> TransformationCompanion<T>.pitch(r: Double): T =
    ypr(yaw = 0.0, pitch = r, roll = 0.0)

public fun <T : Transformation<T>> TransformationCompanion<T>.roll(r: Double): T =
    ypr(yaw = 0.0, pitch = 0.0, roll = r)

public fun <T : Transformation<T>> TransformationCompanion<T>.ypr(yaw: Double, pitch: Double, roll: Double): T =
    ypr(yaw, pitch, roll, 0.0, 0.0, 0.0)

public fun <T : Transformation<T>> TransformationCompanion<T>.ypr(euler: Vector3D, t: Vector3D): T =
    ypr(yaw = euler.y, pitch = euler.x, roll = euler.z, x = t.x, y = t.y, z = t.z)

public fun <T : Transformation<T>> TransformationCompanion<T>.ypr(
    yaw: Double, pitch: Double, roll: Double,
    x: Double, y: Double, z: Double,
): T {
    val cy = cos(yaw)
    val sy = sin(yaw)
    val cp = cos(pitch)
    val sp = sin(pitch)
    val cr = cos(roll)
    val sr = sin(roll)

    return from(
        xx = cy * cr + sy * sp * sr,
        xy = cp * sr,
        xz = -sy * cr + cy * sp * sr,

        yx = cy * -sr + sy * sp * cr,
        yy = cp * cr,
        yz = -sy * -sr + cy * sp * cr,

        zx = sy * cp,
        zy = -sp,
        zz = cy * cp,

        tx = x,
        ty = y,
        tz = z,
    )
}
