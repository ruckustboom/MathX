@file:Suppress("NOTHING_TO_INLINE")

package mathx.geometry

import mathx.*

public interface Transformation<T : Transformation<T>> : Interpolated<T>

public interface Transformation2D<T : Transformation2D<T, T3D>, T3D : Transformation3D<T3D, T>> : Transformation<T> {
    public fun toTransform(): Transform2D
    public fun to3D(): T3D
}

public interface Transformation3D<T : Transformation3D<T, T2D>, T2D : Transformation2D<T2D, T>> : Transformation<T> {
    public fun toTransform(): Transform3D
    public fun to2D(): T2D
}
