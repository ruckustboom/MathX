package mathx

public class Gradient<T> private constructor(
    private val stops: List<Stop<T>>,
    private val interpolator: Interpolator<T>,
) : Interpolated<Gradient<T>> {
    public fun toList(): List<Stop<T>> = stops.toList()

    public operator fun get(offset: Double): Stop<T>? {
        if (stops.isEmpty()) return null
        val index = stops.binarySearchBy(offset, selector = Stop<T>::offset)
        if (index >= 0) return stops[index]
        val a = stops.getOrNull(-index - 2)
        val b = stops.getOrNull(-index - 1)
        return when {
            a != null && b != null ->
                Stop(offset, interpolator.interpolate(a.after, b.before, unlerp(a.offset, b.offset, offset)))
            a != null && b == null -> Stop(offset, a.after)
            a == null && b != null -> Stop(offset, b.before)
            else -> null
        }
    }

    override fun interpolate(b: Gradient<T>, t: Double): Gradient<T> {
        return when {
            stops.isEmpty() -> b
            b.stops.isEmpty() -> this
            else -> {
                val offsets = sortedSetOf<Double>()
                for ((offset, _, _) in stops) offsets += offset
                for ((offset, _, _) in b.stops) offsets += offset
                Gradient(offsets.map {
                    val sa = get(it) as Stop<T>
                    val sb = b[it] as Stop<T>
                    Stop(
                        it,
                        interpolator.interpolate(sa.before, sb.before, t),
                        interpolator.interpolate(sa.after, sb.after, t),
                    )
                }, interpolator)
            }
        }
    }

    public companion object {
        public fun <T> of(interpolator: Interpolator<T>, vararg stops: Stop<T>): Gradient<T> =
            Gradient(stops.distinctBy { it.offset }.sorted(), interpolator)

        public fun <T> of(vararg stops: Stop<T>, interpolator: Interpolator<T>): Gradient<T> =
            of(interpolator, *stops)

        public fun <T : Interpolated<T>> of(vararg stops: Stop<T>): Gradient<T> =
            of(InterpolatedInterpolator(), *stops)

        public fun <T> of(interpolator: Interpolator<T>, stops: Iterable<Stop<T>>): Gradient<T> =
            Gradient(stops.distinctBy { it.offset }.sorted(), interpolator)

        public fun <T> of(stops: Iterable<Stop<T>>, interpolator: Interpolator<T>): Gradient<T> =
            of(interpolator, stops)

        public fun <T : Interpolated<T>> of(stops: Iterable<Stop<T>>): Gradient<T> =
            of(InterpolatedInterpolator(), stops)
    }

    public data class Stop<T>(
        public val offset: Double,
        public val before: T,
        public val after: T = before,
    ) : Comparable<Stop<T>> {
        override fun compareTo(other: Stop<T>): Int = offset.compareTo(other.offset)
    }
}

public inline fun <T> Gradient<T>.getOr(offset: Double, default: () -> T): Gradient.Stop<T> =
    get(offset) ?: Gradient.Stop(offset, default())
