package mathx

public class Gradient private constructor(private val stops: List<Stop>) {
    public fun toList(): List<Stop> = stops.toList()

    public operator fun get(offset: Double): Stop {
        if (stops.isEmpty()) return Stop(offset, Color.TRANSPARENT)
        val index = stops.binarySearchBy(offset, selector = Stop::offset)
        if (index >= 0) return stops[index]
        val a = stops.getOrNull(-index - 2)
        val b = stops.getOrNull(-index - 1)
        return when {
            a != null && b != null ->
                Stop(offset, a.after.lerpPreMultiplied(b.before, unlerp(a.offset, b.offset, offset)))
            a != null && b == null -> Stop(offset, a.after)
            a == null && b != null -> Stop(offset, b.before)
            else -> Stop(offset, Color.TRANSPARENT)
        }
    }

    public fun lerp(target: Gradient, ratio: Double): Gradient = when {
        stops.isEmpty() -> target
        target.stops.isEmpty() -> this
        else -> {
            val offsets = mutableListOf<Double>()
            for (stop in stops) offsets.addSorted(stop.offset, false)
            for (stop in target.stops) offsets.addSorted(stop.offset, false)
            Gradient(offsets.map {
                val a = get(it)
                val b = target[it]
                Stop(it, a.before.lerpPreMultiplied(b.before, ratio), a.after.lerpPreMultiplied(b.after, ratio))
            })
        }
    }

    public companion object {
        public val TRANSPARENT: Gradient = Gradient(emptyList())

        public fun of(vararg stops: Stop): Gradient = Gradient(stops.distinctBy { it.offset }.sorted())
        public fun of(stops: List<Stop>): Gradient = Gradient(stops.distinctBy { it.offset }.sorted())
    }

    public data class Stop(val offset: Double, val before: Color, val after: Color = before) : Comparable<Stop> {
        override fun compareTo(other: Stop): Int = offset.compareTo(other.offset)
    }
}

public fun <T : Comparable<T>> MutableList<T>.addSorted(value: T, overwrite: Boolean = true) {
    val index = binarySearch(value)
    if (index < 0) add(-index - 1, value) else if (overwrite) set(index, value)
}
