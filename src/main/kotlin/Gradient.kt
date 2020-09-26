package mathx

// TODO: Move to Palette project?

public data class ValueStop<T>(val offset: Double, val before: T, val after: T = before) : Comparable<ValueStop<T>> {
    override fun compareTo(other: ValueStop<T>): Int = offset.compareTo(other.offset)
}

public fun <T : Comparable<T>> MutableList<T>.addSorted(value: T, overwrite: Boolean = true) {
    val index = binarySearch(value)
    if (index < 0) add(-index - 1, value) else if (overwrite) set(index, value)
}

public inline fun <T> List<ValueStop<T>>.resolve(
    offset: Double,
    interpolateValue: (a: T, b: T, t: Double) -> T
): ValueStop<T>? {
    if (isEmpty()) return null
    val index = binarySearchBy(offset, selector = ValueStop<T>::offset)
    if (index >= 0) return get(index)
    val head = getOrNull(-index - 2)
    val tail = getOrNull(-index - 1)
    return when {
        head != null && offset == head.offset -> head
        tail != null && offset == tail.offset -> tail
        head != null && tail == null -> ValueStop(offset, head.after)
        head == null && tail != null -> ValueStop(offset, tail.before)
        head != null && tail != null ->
            ValueStop(offset, interpolateValue(head.after, tail.before, unlerp(head.offset, tail.offset, offset)))
        else -> null
    }
}

public inline fun <T> lerp(
    a: List<ValueStop<T>>,
    b: List<ValueStop<T>>,
    t: Double,
    interpolateValue: (a: T, b: T, t: Double) -> T,
): List<ValueStop<T>> = when {
    a.isEmpty() && b.isEmpty() -> emptyList()
    a.isEmpty() -> b.toList()
    b.isEmpty() -> a.toList()
    else -> {
        val offsets = mutableListOf<Double>()
        for (stop in a) offsets.addSorted(stop.offset, false)
        for (stop in b) offsets.addSorted(stop.offset, false)
        offsets.map {
            val av = a.resolve(it, interpolateValue)!!
            val bv = b.resolve(it, interpolateValue)!!
            ValueStop(it, interpolateValue(av.before, bv.before, t), interpolateValue(av.after, bv.after, t))
        }
    }
}
