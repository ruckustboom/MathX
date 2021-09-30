@file:Suppress("NOTHING_TO_INLINE")

package mathx

import kotlin.math.abs

// Int

public inline fun nearest(x: Int, a: Int, b: Int): Int = if (abs(x - a) < abs(x - b)) a else b

public inline fun offsetInChunk(x: Int, size: Int, origin: Int = 0): Int =
    ((x - origin) % size + size) % size

public inline fun startOfChunk(x: Int, size: Int, origin: Int = 0): Int =
    x - offsetInChunk(x, size, origin)

public inline fun indexOfChunk(x: Int, size: Int, origin: Int = 0): Int =
    (startOfChunk(x, size, origin) - origin) / size

// Long

public inline fun nearest(x: Long, a: Long, b: Long): Long = if (abs(x - a) < abs(x - b)) a else b

public inline fun offsetInChunk(x: Long, size: Long, origin: Long = 0): Long =
    ((x - origin) % size + size) % size

public inline fun startOfChunk(x: Long, size: Long, origin: Long = 0): Long =
    x - offsetInChunk(x, size, origin)

public inline fun indexOfChunk(x: Long, size: Long, origin: Long = 0): Long =
    (startOfChunk(x, size, origin) - origin) / size
