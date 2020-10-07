package mathx.geometry

import mathx.*

public data class Transform2D(
    val xx: Double, val xy: Double, val xw: Double,
    val yx: Double, val yy: Double, val yw: Double,
    val tx: Double, val ty: Double, val tw: Double,
) : Transformation2D<Transform2D, Transform3D> {
    public operator fun invoke(p: Vector2D): Vector2D = Vector2D(
        x = xx * p.x + yx * p.y,
        y = xy * p.x + yy * p.y,
    )

    public operator fun invoke(p: Point2D): Point2D = Point2D(
        x = xx * p.x + yx * p.y + tx,
        y = xy * p.x + yy * p.y + ty,
    )

    public operator fun invoke(b: Basis2D): Basis2D = Basis2D(
        xx = xx * b.xx + yx * b.xy,
        xy = xy * b.xx + yy * b.xy,

        yx = xx * b.yx + yx * b.yy,
        yy = xy * b.yx + yy * b.yy,
    )

    public operator fun invoke(a: Affine2D): Affine2D = Affine2D(
        xx = xx * a.xx + yx * a.xy,
        xy = xy * a.xx + yy * a.xy,

        yx = xx * a.yx + yx * a.yy,
        yy = xy * a.yx + yy * a.yy,

        tx = xx * a.tx + yx * a.ty + tx,
        ty = xy * a.tx + yy * a.ty + ty,
    )

    public operator fun invoke(t: Transform2D): Transform2D = Transform2D(
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

    public fun toVector2D(): Vector2D = Vector2D(x = tx, y = ty)

    public fun toPoint2D(): Point2D = Point2D(x = tx, y = ty)

    public fun toBasis2D(): Basis2D = Basis2D(
        xx = xx, xy = xy,
        yx = yx, yy = yy,
    )

    public fun toAffine2D(): Affine2D = Affine2D(
        xx = xx, xy = xy,
        yx = yx, yy = yy,
        tx = tx, ty = ty,
    )

    override fun toTransform(): Transform2D = this

    override fun to3D(): Transform3D = Transform3D(
        xx = xx, xy = xy, xz = 0.0, xw = xw,
        yx = yx, yy = yy, yz = 0.0, yw = yw,
        zx = 0.0, zy = 0.0, zz = 1.0, zw = 0.0,
        tx = tx, ty = ty, tz = 0.0, tw = tw,
    )

    override fun interpolate(b: Transform2D, t: Double): Transform2D = Transform2D(
        xx = lerp(xx, b.xx, t), xy = lerp(xy, b.xy, t), xw = lerp(xw, b.xw, t),
        yx = lerp(yx, b.yx, t), yy = lerp(yy, b.yy, t), yw = lerp(yw, b.yw, t),
        tx = lerp(tx, b.tx, t), ty = lerp(ty, b.ty, t), tw = lerp(tw, b.tw, t),
    )

    public companion object : Interpolator<Transform2D> {
        public val IDENTITY: Transform2D = Transform2D(
            xx = 1.0, xy = 0.0, xw = 0.0,
            yx = 0.0, yy = 1.0, yw = 0.0,
            tx = 0.0, ty = 0.0, tw = 1.0,
        )

        override fun interpolate(a: Transform2D, b: Transform2D, t: Double): Transform2D = a.interpolate(b, t)

        override fun toString(): String = "Transform2D"
    }
}
