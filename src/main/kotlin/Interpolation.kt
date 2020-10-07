@file:Suppress("NOTHING_TO_INLINE")

package mathx

public fun interface Interpolated<T> {
    public fun interpolate(b: T, t: Double): T
}

public fun interface Interpolator<T> {
    public fun interpolate(a: T, b: T, t: Double): T
}

public class InterpolatedInterpolator<T : Interpolated<T>> : Interpolator<T> {
    override fun interpolate(a: T, b: T, t: Double): T = a.interpolate(b, t)
}

public fun interface Easing {
    public fun ease(t: Double): Double
}

public inline fun <T> Interpolated<T>.interpolate(b: T, t: Double, easing: Easing): T =
    interpolate(b, easing.ease(t))

public inline fun <T> Interpolator<T>.interpolate(a: T, b: T, t: Double, easing: Easing): T =
    interpolate(a, b, easing.ease(t))
