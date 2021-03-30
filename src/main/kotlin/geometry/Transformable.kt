package mathx.geometry

import mathx.Interpolated

public interface Transformable<T : Transformable<T>>: Interpolated<T> {
    public infix fun transformBy(t: Transformation<*>): T
}
