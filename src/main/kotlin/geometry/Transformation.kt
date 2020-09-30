@file:Suppress("NOTHING_TO_INLINE")

package mathx.geometry

public interface Transformation {
    public fun transform(point: Point2D): Point2D
    public fun transform(point: Point3D): Point3D
    public fun transform(basis: Basis): Basis
    public fun transform(affine: Affine): Affine

    public fun toPoint2D(): Point2D
    public fun toPoint3D(): Point3D
    public fun toBasis(): Basis
    public fun toAffine(): Affine
}

public inline operator fun Transformation.invoke(point: Point2D): Point2D = transform(point)
public inline operator fun Transformation.invoke(point: Point3D): Point3D = transform(point)
public inline operator fun Transformation.invoke(basis: Basis): Basis = transform(basis)
