@file:Suppress("NOTHING_TO_INLINE")

package mathx.geometry

import mathx.*

public interface Transformation<T: Transformation<T>> : Interpolated<T>

public interface Transformation2D<T : Transformation2D<T>> : Transformation<T> {
    public fun toTransform2D(): Transform2D
}

public interface Transformation3D<T : Transformation3D<T>> : Transformation<T> {
    public fun toTransform3D(): Transform3D
}
